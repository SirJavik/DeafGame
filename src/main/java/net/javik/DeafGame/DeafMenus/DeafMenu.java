package net.javik.DeafGame.DeafMenus;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import javax.swing.*;

public class DeafMenu extends JPanel implements IDeafMenu {
    public void close(DeafWindow parent) {
        /*this.getParent().remove(this);
        this.getParent().invalidate();
        this.getParent().validate();
        this.getParent().repaint();*/
        System.out.println(parent);
    }
}
