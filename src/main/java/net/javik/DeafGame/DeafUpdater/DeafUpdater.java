package net.javik.DeafGame.DeafUpdater;

import net.javik.DeafGame.DeafBasics.DeafConsole;
import net.javik.DeafGame.DeafExceptions.InvalidFileSizeException;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class DeafUpdater {
    private String  server,
                    protocol,
                    versionFile,
                    version,
                    newVersion,
                    packagePath,
                    dataUnit = "B";
    private int     port;
    private double  fileSize;


    /**
     * Checks if a server is available
     * @param server Address of server
     * @param port Port of server
     * @return Returns false on failure otherwise true
     */
    private boolean checkConnection(String server, int port) {
        try (Socket socket = new Socket(server, port)) {
            return true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateCheck()
            throws IOException {
        URL url = new URL(this.protocol+"://"+this.server+"/"+this.versionFile);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.indexOf("deafgame") > 1) {
                String[] lineArray = inputLine.split(":");
                this.packagePath = lineArray[2];
                this.newVersion = lineArray[1];

                if(lineArray[1].equals(this.version)) {
                    return false;
                }
            }
        }
        in.close();
        return true;
    }

    public String newVersion() {
        return this.newVersion;
    }

    /**
     * Calculates file size of remote file
     * @param url URL to the remote file
     * @return File size in byte
     * @throws InvalidFileSizeException If file is not available or invalid.
     */
    private double getFileSize(@NotNull URL url)
            throws InvalidFileSizeException
    {
        URLConnection urlConnection = null;
        try {
            urlConnection = url.openConnection();

            if(urlConnection instanceof HttpURLConnection) {
                ((HttpURLConnection)urlConnection).setRequestMethod("HEAD");
            }
            urlConnection.getInputStream();
            return urlConnection.getContentLength();
        } catch (IOException ex) {
            throw new InvalidFileSizeException("Unable to determine file size");
        } finally {
            if(urlConnection instanceof HttpURLConnection) {
                ((HttpURLConnection)urlConnection).disconnect();
            }
        }
    }

    public void downloadNew()
            throws IOException {
        URL url = new URL(this.protocol+"://"+this.server+"/"+this.packagePath+"/v"+this.newVersion+".7z");
        this.fileSize       = this.getFileSize(url);

        while( this.fileSize > 1024 ) {
            this.fileSize = this.fileSize/1024;
            if(this.dataUnit.equals("B")) {
                this.dataUnit = "kB";
            } else if(this.dataUnit.equals("kB")) {
                this.dataUnit = "MB";
            } else if(this.dataUnit.equals("MB")) {
                this.dataUnit = "GB";
            }
        }

        DeafConsole.writeLine("File size: " + new DecimalFormat("###.##").format(this.fileSize) + " " + this.dataUnit);


        DeafConsole.write("Downloading");

        Thread downloadBar = new Thread(() -> {
            int pointCount = 0;

            while(true) {
                while (pointCount < 3) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.print(".");
                    pointCount++;
                }

                while (pointCount > -1) {
                    if (pointCount == 2) {
                        System.out.print(".");
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    System.out.print("\b");
                    pointCount--;
                }
                pointCount = 0;
            }
        });
        downloadBar.start();

        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("tmp/v"+this.newVersion+".7z");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        downloadBar.interrupt();
        System.out.println();
        DeafConsole.writeLine("Finished");
    }

    public void extractFile()
            throws IOException {
        SevenZFile sevenZFile = new SevenZFile( new File("tmp/v"+this.newVersion+".7z") );
        SevenZArchiveEntry entry = sevenZFile.getNextEntry();

        while(entry != null){
            System.out.println(entry.getName());

            Path outPath = Paths.get(entry.getName());
            if( Files.isDirectory(outPath) ) {
                try {
                    System.out.println("Dir!");
                    throw new IOException("Dir!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            FileOutputStream out = new FileOutputStream(entry.getName());
            byte[] content = new byte[(int) entry.getSize()];
            sevenZFile.read(content, 0, content.length);
            out.write(content);
            out.close();
            entry = sevenZFile.getNextEntry();
        }
        sevenZFile.close();
    }

    /**
     *
     * @param server
     * @param port
     * @param protocol
     * @param versionFile
     * @throws IOException
     */
    public DeafUpdater(String server, int port, String protocol, String versionFile, String version) throws IOException {
        /* ------------------------------------------------------------------------ *
         * Setting parameters
         * ------------------------------------------------------------------------ */
        this.server         = server;
        this.port           = port;
        this.protocol       = protocol;
        this.versionFile    = versionFile;
        this.version        = version;

        /* ------------------------------------------------------------------------ *
         * Check connection
         * ------------------------------------------------------------------------ */
        if(!this.checkConnection(
                this.server,
                this.port
        )) {
            throw new IOException("Update server is not available");
        }

        /* ------------------------------------------------------------------------ *
         * Check if temp dir is writable
         * ------------------------------------------------------------------------ */
        Path tmpDir = Paths.get("tmp");

        if( !Files.exists(tmpDir) ) {
            try {
                Files.createDirectories(tmpDir);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
