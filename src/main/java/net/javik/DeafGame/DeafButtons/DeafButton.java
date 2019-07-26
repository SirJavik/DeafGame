package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafMenus.DeafMenu;

import javax.swing.*;

abstract public class DeafButton extends JLabel implements IDeafButton {
    public DeafButton(String title) {
        super(title);
    }

    public void buttonTrigger(DeafWindow window, DeafMenu parent) {}
}
