package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafMenus.MainMenu;
import net.javik.DeafGame.DeafBasics.DeafWindow;

public class ExitButton extends DeafButton implements IDeafButton {
    public ExitButton() {
        super("Beenden");
    }

    public void buttonTrigger(DeafWindow window, MainMenu menu) {
        System.exit(0);
    }
}
