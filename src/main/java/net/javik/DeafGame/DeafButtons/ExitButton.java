package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafMenus.DeafMenu;

public class ExitButton extends DeafButton implements IDeafButton {
    public ExitButton() {
        super("Beenden");
    }

    public void buttonTrigger(DeafWindow window, DeafMenu parent) {
        System.exit(0);
    }
}
