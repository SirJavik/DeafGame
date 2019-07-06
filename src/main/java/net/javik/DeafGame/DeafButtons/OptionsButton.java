package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafMenus.DeafMenu;

public class OptionsButton extends DeafButton implements IDeafButton {
    public OptionsButton() {
        super("Optionen");
    }

    public void buttonTrigger(DeafWindow window, DeafMenu parent) {
        System.out.println("Play Btn");
    }
}
