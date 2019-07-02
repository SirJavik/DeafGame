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
         * Update repository, protocol, branch and autoupdate
         */
        String updateRepo       = deafConfig.readString("updater","repo");
        String updateProtocol   = deafConfig.readString("updater","protocol");
        String updateBranch     = deafConfig.readString("updater","branch");
        boolean updateAuto      = deafConfig.readBool("updater","autoupdate");

        /* ------------------------------------------------------------------------ *
         * Run update check
         * ------------------------------------------------------------------------ */
        if(updateAuto) {
            System.out.println("Init Updater");

            DeafUpdater deafUpdater = new DeafUpdater();

            deafUpdater.updateCheck();
        }

        System.out.println("DeafGame");
        DeafWindow gameWindow = new DeafWindow(appWidth,appHeight, appTitle);
    }

}
