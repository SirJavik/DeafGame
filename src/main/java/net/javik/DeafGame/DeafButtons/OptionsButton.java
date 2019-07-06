package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafMenus.MainMenu;
import net.javik.DeafGame.DeafBasics.DeafWindow;

public class OptionsButton extends DeafButton implements IDeafButton {
    public OptionsButton() {
        super("Optionen");
    }

    public void buttonTrigger(DeafWindow window, MainMenu menu) {
        System.out.println("Play Btn");
    }
}
