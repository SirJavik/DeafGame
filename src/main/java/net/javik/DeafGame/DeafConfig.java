package net.javik.DeafGame;

import java.io.File;
import org.ini4j.*;

public class DeafConfig {
    private Wini iniHandler;


    /**
     * Initializes DeafConfig class and loads configuration file
     * @param file configuration file path
     */
    public DeafConfig( String file ) {
        this.openFile(file);
    }

    /**
     * Opens a configuration file
     * @param file configuration file path
     */
    public void openFile( String file ) {
        try {
            this.iniHandler = new Wini( new File( file ) );
        } catch ( Exception e ) {
            System.err.println( e.getMessage() );
        }
    }

    /**
     * Reads a string value from configuration file
     * @param section Configuration file section
     * @param key Configuration file key
     * @return String Configuration value
     */
    public String readString(String section, String key) {
        return this.iniHandler.get(section, key, String.class);
    }

    /**
     * Reads a double value from configuration file
     * @param section Configuration file section
     * @param key Configuration file key
     * @return double Configuration value
     */
    public double readDouble(String section, String key) {
        return this.iniHandler.get(section, key, double.class);
    }

    /**
     * Reads a float value from configuration file
     * @param section Configuration file section
     * @param key Configuration file key
     * @return float Configuration value
     */
    public float readFloat(String section, String key) {
        return this.iniHandler.get(section, key, float.class);
    }

    /**
     * Reads a int value from configuration file
     * @param section Configuration file section
     * @param key Configuration file key
     * @return int Configuration value
     */
    public int readInt(String section, String key) {
        return this.iniHandler.get(section, key, int.class);
    }

    /**
     * Reads a bool value from configuration file
     * @param section Configuration file section
     * @param key Configuration file key
     * @return bool Configuration value
     */
    public boolean readBool(String section, String key) {
        return this.iniHandler.get(section, key, boolean.class);
    }
}
