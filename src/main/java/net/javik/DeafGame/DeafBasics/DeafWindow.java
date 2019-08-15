package net.javik.DeafGame.DeafBasics;

import net.javik.DeafGame.DeafAudioPlayer;
import net.javik.DeafGame.DeafMenus.MainMenu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeafWindow extends JFrame implements ActionListener {
    /**
     * Window width
     */
    private int windowWidth     = 0;

    /**
     * Window height
     */
    private int windowHeight    = 0;


    /* ------------------------------------------------------------------------ *
     * DeafWindow functions
     * ------------------------------------------------------------------------ */

    /*static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"foo", "bar", "baz"};
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(120,140,200,40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
        g.drawString("Loading "+comps[(frame/5)%3]+"...", 120, 150);
    }*/

    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }

    private static WindowListener closeWindow = new WindowAdapter(){
        public void windowClosing(WindowEvent e){
            e.getWindow().dispose();
        }
    };

    public DeafWindow( int width, int height, String title, String version ) {
        /**
         * Call JFrame constructor with super
         * Like PHP's parent::__construct();
         */
        super(title);

        DeafAudioPlayer audioPlayer = new DeafAudioPlayer();
        audioPlayer.playAudio("assets/sounds/mainmenuTheme.mp3");

        /**
         * Signal handling
         * Closes window on command
         */
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt) {
                dispose();
                System.exit(0);
            }
        });


        this.windowHeight = height;
        this.windowWidth = width;

        this.setSize(this.windowWidth, this.windowHeight);
        this.setResizable(false);

        this.setLayout(null);

        /**
         * Main menu
         */
        MainMenu menu = new MainMenu(this, this.windowHeight, this.windowWidth, "assets/img/nasa-Q1p7bh3SHj8-unsplash.jpg", version);
        menu.setBounds(0,0,this.windowWidth,this.windowHeight);

        this.add(menu);


        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }


}