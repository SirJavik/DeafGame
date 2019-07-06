package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafMenus.MainMenu;
import net.javik.DeafGame.DeafBasics.DeafWindow;

public class PlayButton extends DeafButton implements IDeafButton{
    public PlayButton() {
        super("Spielen");
    }

    public void buttonTrigger(DeafWindow window, MainMenu menu) {

        window.remove(menu);
        System.out.println("Play Btn");
    }
}
