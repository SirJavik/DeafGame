package net.javik.DeafGame;

public class DeafMain {

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
        String updateRepo       = deafConfig.readString("updater","repo");
        String updateProtocol   = deafConfig.readString("updater","protocol");
        String updateBranch     = deafConfig.readString("updater","branch");
        boolean updateAuto      = deafConfig.readBool("updater","autoupdate");

        /* ------------------------------------------------------------------------ *
         * DeafGame initialization
         * ------------------------------------------------------------------------ */

        DeafConsole.writeLine("DeafGame " + verString);

        /* ------------------------------------------------------------------------ *
         * Run update check
         * ------------------------------------------------------------------------ */
        if(updateAuto) {
            DeafConsole.writeLine("DeafUpdater goes on");

            DeafUpdater deafUpdater = new DeafUpdater(updateBranch);

            if(deafUpdater.updateCheck()) {
                DeafConsole.writeLine("Update requied");
            } else {
                DeafConsole.writeLine("No update needed: Project already up-to-date");
                DeafConsole.writeLine("DeafUpdater goes off");
            }
        }

        System.out.println("DeafGame");

        DeafWindow gameWindow = new DeafWindow(appWidth,
                                               appHeight,
                                               appTitle,
                                               verString);
    }

}
