package net.javik.DeafGame;

public class DeafMain {

    public static void main(String[] args) {
        /* ------------------------------------------------------------------------ *
         * Load configuration file
         * ------------------------------------------------------------------------ */
        DeafConfig deafConfig = new DeafConfig("deafconfig.ini");

        String appTitle = deafConfig.readString("app","title");
        int appHeight = deafConfig.readInt("app","height");
        int appWidth  = deafConfig.readInt("app", "width");

        System.out.println("DeafGame");
        DeafWindow gameWindow = new DeafWindow(appWidth,appHeight, appTitle);
    }

}
