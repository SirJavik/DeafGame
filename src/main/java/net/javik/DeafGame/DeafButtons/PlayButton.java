package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafMainmenu;
import net.javik.DeafGame.DeafWindow;

public class PlayButton extends DeafButton {
    public PlayButton() {
        super("Spielen");
    }

    public void buttonTrigger(DeafWindow window, DeafMainmenu menu) {
        window.remove(menu);
        System.out.println("Play Btn");
    }
}
