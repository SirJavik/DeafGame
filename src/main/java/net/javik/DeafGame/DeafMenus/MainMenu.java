package net.javik.DeafGame.DeafMenus;

import net.javik.DeafGame.DeafBasics.DeafColor;
import net.javik.DeafGame.DeafBasics.DeafFonts;
import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafButtons.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class MainMenu extends ImageMenu implements IDeafMenu, MouseListener {
    private DeafWindow window;

    /*
     * Position and size for elements
     */
    private Map<String, DeafButton> buttonElements   = new HashMap<String, DeafButton>();

    /*
     * Elements
     */
    private JLabel menuTitle  = new JLabel("DeafGame");

    private PlayButton playGame         = new PlayButton();
    private OptionsButton optionsGame   = new OptionsButton();
    private CreditsButton creditGame    = new CreditsButton();
    private ExitButton exitGame         = new ExitButton();

    private JLabel versionLabel = new JLabel();

    public MainMenu(DeafWindow window, int height, int width, String backgroundImage, String version) {
        /*
         * Child calls parent (constructor)
         */
        super(backgroundImage, height, width);

        this.window = window;

        this.setLayout(null);
        addMouseListener(this);

        /*
         * Elements
         */
        this.menuTitle.setFont(DeafFonts.Heading1);
        this.menuTitle.setForeground(DeafColor.white);

        this.playGame.setFont(DeafFonts.MenuEntities);
        this.playGame.setForeground(DeafColor.white);

        this.optionsGame.setFont(DeafFonts.MenuEntities);
        this.optionsGame.setForeground(DeafColor.white);

        this.creditGame.setFont(DeafFonts.MenuEntities);
        this.creditGame.setForeground(DeafColor.white);

        this.exitGame.setFont(DeafFonts.MenuEntities);
        this.exitGame.setForeground(DeafColor.white);

        this.versionLabel.setFont(DeafFonts.VersionLabel);
        this.versionLabel.setText(version);
        this.versionLabel.setForeground(DeafColor.white);

        /*
         * Positioning elements
         */
        int availableSpace = (height - this.menuTitle.getPreferredSize().height - this.playGame.getPreferredSize().height - this.optionsGame.getPreferredSize().height - this.creditGame.getPreferredSize().height - this.exitGame.getPreferredSize().height) / 5;

        this.menuTitle.setBounds(    50, 15,                                            this.menuTitle.getPreferredSize().width,    this.menuTitle.getPreferredSize().height);
        this.playGame.setBounds(     50, this.menuTitle.getY() + availableSpace + 15,   this.playGame.getPreferredSize().width,     this.playGame.getPreferredSize().height);
        this.optionsGame.setBounds(  50, this.playGame.getY() + availableSpace + 15,    this.optionsGame.getPreferredSize().width,  this.optionsGame.getPreferredSize().height);
        this.creditGame.setBounds(   50, this.optionsGame.getY() + availableSpace + 15, this.creditGame.getPreferredSize().width,   this.creditGame.getPreferredSize().height);
        this.exitGame.setBounds(     50, this.creditGame.getY() + availableSpace + 15,  this.exitGame.getPreferredSize().width,     this.exitGame.getPreferredSize().height);

        this.versionLabel.setBounds(width-this.versionLabel.getPreferredSize().width-10, height - this.versionLabel.getPreferredSize().height-30, this.versionLabel.getPreferredSize().width, this.versionLabel.getPreferredSize().height);

        /*
         * Saving positions and sizes
         */
        this.buttonElements.put("playBtn",     this.playGame);
        this.buttonElements.put("optionsBtn",  this.optionsGame);
        this.buttonElements.put("creditsBtn",  this.creditGame);
        this.buttonElements.put("exitBtn",     this.exitGame);

        /*
         * Render Elements
         */
        this.add(this.menuTitle);
        this.add(this.playGame);
        this.add(this.optionsGame);
        this.add(this.creditGame);
        this.add(this.exitGame);
        this.add(this.versionLabel);
    }

    //-- Maus-Methoden aus dem MouseListener-Interface ---------
    public void mouseClicked(MouseEvent mouse){}

    public void mousePressed(@NotNull MouseEvent mouse)
    {
        Point mousePosition = mouse.getPoint();

        for(Map.Entry <String, DeafButton> entry : buttonElements.entrySet()) {
            if( this.buttonPositionMatch(mousePosition, entry.getValue() ) ) {
                entry.getValue().buttonTrigger(this.window,this);
            }
        }


    }

    public void mouseReleased(MouseEvent mouse){}

    public void mouseEntered(MouseEvent mouse){

    }
    public void mouseExited(MouseEvent mouse){}

    private boolean buttonPositionMatch(@NotNull Point mousePosition,
                                        @NotNull DeafButton deafButton) {
        return ((mousePosition.x >= deafButton.getX()) && (mousePosition.x <= (deafButton.getX() + deafButton.getWidth()))) &&
                (mousePosition.y >= deafButton.getY()) && (mousePosition.y <= (deafButton.getY() + deafButton.getHeight()));

    }

}
