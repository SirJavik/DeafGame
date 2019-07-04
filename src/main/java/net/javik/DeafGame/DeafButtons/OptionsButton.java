package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafMainmenu;
import net.javik.DeafGame.DeafWindow;

public class OptionsButton extends DeafButton {
    public OptionsButton() {
        super("Optionen");
    }

    public void buttonTrigger(DeafWindow window, DeafMainmenu menu) {
        System.out.println("Play Btn");
    }
}
