package net.javik.DeafGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class DeafAudioPlayer {
    private AdvancedPlayer player;

    public DeafAudioPlayer()
    {
        player = null;
    }

    public void playAudio(final String audioFile)
    {
        try {
            playerInitialization(audioFile);
            Thread playerThread = new Thread() {
                public void run()
                {
                    try {
                        player.play(5000);
                    }
                    catch(JavaLayerException e) {
                        reportProblem(audioFile);
                    }
                    finally {
                        killPlayer();
                    }
                }
            };
            playerThread.start();
        }
        catch (Exception ex) {
            reportProblem(audioFile);
        }
    }

    public void stopPlayer()
    {
        killPlayer();
    }


    private void playerInitialization(String audioFile)
    {
        try {
            InputStream is = openFile(audioFile);
            player = new AdvancedPlayer(is, createAudioDevice());
        }
        catch (IOException e) {
            reportProblem(audioFile);
            killPlayer();
        }
        catch(JavaLayerException e) {
            reportProblem(audioFile);
            killPlayer();
        }
    }


    private InputStream openFile(String dateiname)
            throws IOException
    {
        return new BufferedInputStream(
                new FileInputStream(dateiname));
    }


    private AudioDevice createAudioDevice()
            throws JavaLayerException
    {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }


    private void killPlayer()
    {
        synchronized(this) {
            if(player != null) {
                player.stop();
                player = null;
            }
        }
    }


    private void reportProblem(String audioFile)
    {
        System.out.println("Es gab ein Problem beim Abspielen von: " + audioFile);
    }
}
