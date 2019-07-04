package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafMainmenu;
import net.javik.DeafGame.DeafWindow;

public class ExitButton extends DeafButton {
    public ExitButton() {
        super("Beenden");
    }

    public void buttonTrigger(DeafWindow window, DeafMainmenu menu) {
        System.exit(0);
    }
}
