package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafMenus.DeafMenu;
import net.javik.DeafGame.DeafMenus.GameMenu;

public class PlayButton extends DeafButton implements IDeafButton{
    public PlayButton() {
        super("Spielen");
    }

    public void buttonTrigger(DeafWindow window, DeafMenu parent) {
       parent.close(window);

        window.setLayout(null);

        GameMenu gameMenu = new GameMenu(window, 900,1600, "assets/img/nasa-Q1p7bh3SHj8-unsplash.jpg", "1.1.0");
        gameMenu.setBounds(0,0,1600,900);

        window.add(gameMenu);

        /*window.invalidate();
        window.validate();
        window.repaint();*/
        //window.update(window);

    }
}
