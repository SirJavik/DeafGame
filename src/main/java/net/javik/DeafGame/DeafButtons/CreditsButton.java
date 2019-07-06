package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafMenus.DeafMenu;
import net.javik.DeafGame.DeafBasics.DeafWindow;

public class CreditsButton extends DeafButton {
    public CreditsButton() {
        super("Credits");
    }

    public void buttonTrigger(DeafWindow window, DeafMenu parent) {
        System.out.println("Play Btn");
    }
}
