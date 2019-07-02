package net.javik.DeafGame;

import javax.swing.*;
import java.awt.*;

public class DeafMainmenu extends DeafImagePanel {
    public DeafMainmenu(int height, int width, String backgroundImage, String version) {

        /**
         * Child calls parent (constructor)
         */
        super(backgroundImage, height, width);

        this.setLayout(null);

        /**
         * Elements
         */
        Font deafHeadings = new Font("Arial",Font.BOLD,60);
        Font deafMenuEntitys = new Font("Arial",Font.BOLD,30);
        Font deafMenuVersion = new Font("Arial",Font.BOLD,15);

        JLabel menuTitle  = new JLabel("DeafGame");
        menuTitle.setFont(deafHeadings);
        menuTitle.setForeground(Color.white);

        JLabel playGame = new JLabel("Spielen");
        playGame.setFont(deafMenuEntitys);
        playGame.setForeground(Color.white);

        JLabel optionsGame = new JLabel("Optionen");
        optionsGame.setFont(deafMenuEntitys);
        optionsGame.setForeground(Color.white);

        JLabel creditGame = new JLabel("Credits");
        creditGame.setFont(deafMenuEntitys);
        creditGame.setForeground(Color.white);

        JLabel exitGame = new JLabel("Beenden");
        exitGame.setFont(deafMenuEntitys);
        exitGame.setForeground(Color.white);

        JLabel versionLabel = new JLabel(version);
        versionLabel.setFont(deafMenuVersion);
        versionLabel.setForeground(Color.white);

        /**
         * Positioning elements
         */
        int availableSpace = (height - menuTitle.getPreferredSize().height - playGame.getPreferredSize().height - optionsGame.getPreferredSize().height - creditGame.getPreferredSize().height - exitGame.getPreferredSize().height) / 5;

        menuTitle.setBounds(    10, 5, menuTitle.getPreferredSize().width,      menuTitle.getPreferredSize().height);
        playGame.setBounds(     10, menuTitle.getY() + availableSpace,     playGame.getPreferredSize().width,      playGame.getPreferredSize().height);
        optionsGame.setBounds(  10, playGame.getY() + availableSpace,       optionsGame.getPreferredSize().width,   optionsGame.getPreferredSize().height);
        creditGame.setBounds(   10, optionsGame.getY() + availableSpace,   creditGame.getPreferredSize().width,    creditGame.getPreferredSize().height);
        exitGame.setBounds(     10, creditGame.getY() + availableSpace,    exitGame.getPreferredSize().width,      exitGame.getPreferredSize().height);

        versionLabel.setBounds(width-versionLabel.getPreferredSize().width-10, height - versionLabel.getPreferredSize().height-30, versionLabel.getPreferredSize().width, versionLabel.getPreferredSize().height);

        /**
         * Render Elements
         */
        this.add(menuTitle);
        this.add(playGame);
        this.add(optionsGame);
        this.add(creditGame);
        this.add(exitGame);
        this.add(versionLabel);
    }
}
