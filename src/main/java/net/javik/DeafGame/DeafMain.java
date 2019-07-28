package net.javik.DeafGame;

import net.javik.DeafGame.DeafBasics.DeafConfig;
import net.javik.DeafGame.DeafBasics.DeafConsole;
import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafUpdater.DeafUpdater;

import java.io.IOException;

public class DeafMain {
    private static final int PLAY_BTN = 1000,
                             OPTIONS_BTN = 1200,
                             CREDITS_BTN = 1400,
                             CLOSE_BTN = 1600;


    public static void main(String[] args) {
        /* ------------------------------------------------------------------------ *
         * Load configuration file
         * ------------------------------------------------------------------------ */
        DeafConfig deafConfig = new DeafConfig("deafconfig.ini");

        /*
         * Window height, width and title
         */
        String appTitle = deafConfig.readString("app","title");
        int appHeight   = deafConfig.readInt("app","height");
        int appWidth    = deafConfig.readInt("app", "width");

        /*
         * Version major, minor and patch level
         */
        int verMajor = deafConfig.readInt("version", "major");
        int verMinor = deafConfig.readInt("version", "minor");
        int verPatch = deafConfig.readInt("version", "patch");

        String verString = "v" + verMajor + "." + verMinor + "." + verPatch;

        /*
         * Update repository, protocol, branch and autoupdate
         */
        String updateServer         = deafConfig.readString("updater","server");
        int updatePort              = deafConfig.readInt("updater","port");
        String updateProtocol       = deafConfig.readString("updater","protocol");
        String updateVersionFile    = deafConfig.readString("updater","versionfile");
        boolean updateAuto          = deafConfig.readBool("updater","autoupdate");

        /* ------------------------------------------------------------------------ *
         * DeafGame initialization
         * ------------------------------------------------------------------------ */

        DeafConsole.writeLine("DeafGame " + verString);

        /* ------------------------------------------------------------------------ *
         * Run update check
         * ------------------------------------------------------------------------ */

        if(updateAuto) {
            DeafConsole.writeLine("DeafUpdater goes on");

            try {
                DeafUpdater deafUpdater = new DeafUpdater(updateServer, updatePort, updateProtocol, updateVersionFile, verString.replace("v", ""));

                if(deafUpdater.updateCheck()) {
                    DeafConsole.writeLine("Update required");
                    DeafConsole.writeLine("Old version: " +verString);
                    DeafConsole.writeLine("New version: "  + deafUpdater.newVersion());

                    deafUpdater.downloadNew();

                    DeafConsole.writeLine("Extracting...");
                    deafUpdater.extractFile();
                    DeafConsole.writeLine("Finished");

                } else {
                    DeafConsole.writeLine("No update needed: Project already up-to-date");
                    DeafConsole.writeLine("DeafUpdater goes off");
                }
            }  catch (IOException ex) {
                DeafConsole.Error.writeLine(ex.getMessage());
                ex.printStackTrace();
            }
        }

        DeafWindow gameWindow = new DeafWindow(appWidth,
                                               appHeight,
                                               appTitle,
                                               verString);
    }

}
