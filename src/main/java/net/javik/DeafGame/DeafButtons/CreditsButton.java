package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafMainmenu;
import net.javik.DeafGame.DeafWindow;

public class CreditsButton extends DeafButton {
    public CreditsButton() {
        super("Credits");
    }

    public void buttonTrigger(DeafWindow window, DeafMainmenu menu) {
        System.out.println("Play Btn");
    }
}
