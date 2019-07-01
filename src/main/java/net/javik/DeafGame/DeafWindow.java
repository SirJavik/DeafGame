package net.javik.DeafGame;

import java.awt.*;
import java.awt.event.*;
import java.applet.AudioClip;
import java.applet.*;
import java.util.*;
import java.net.URL;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DeafWindow extends JFrame {
    /**
     * Window width
     */
    private int windowWidth     = 0;

    /**
     * Window height
     */
    private int windowHeight    = 0;

    public DeafWindow( int width, int height, String title ) {
        /**
         * Call JFrame constructor with super
         * Like PHP's parent::__construct();
         * Why the fuck call they it super?!
         */
        super(title);

        /**
         * Signal handling
         * Closes window on command
         */
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt) { dispose(); }
        });

        this.windowHeight = height;
        this.windowWidth = width;

        this.setSize(this.windowWidth, this.windowHeight);
        this.setResizable(false);
        this.setLayout(null);

        this.setVisible(true);

    }

}