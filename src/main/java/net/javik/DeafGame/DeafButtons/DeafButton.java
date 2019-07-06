package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafMenus.MainMenu;
import net.javik.DeafGame.DeafBasics.DeafWindow;

import javax.swing.*;

public class DeafButton extends JLabel implements IDeafButton {
    public DeafButton(String title) {
        super(title);
    }

    public void buttonTrigger(DeafWindow window, MainMenu menu) {}
}
