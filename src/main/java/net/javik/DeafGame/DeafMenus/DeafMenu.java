package net.javik.DeafGame.DeafMenus;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import javax.swing.*;

public class DeafMenu extends JPanel implements IDeafMenu {
    public void close(DeafWindow parent) {
        parent.remove(this);
        parent.invalidate();
        parent.validate();
        parent.repaint();
    }

    public void update(DeafWindow parent) {
        parent.invalidate();
        parent.validate();
        parent.repaint();
    }

    public void update() {
        this.invalidate();
        this.validate();
        this.repaint();
    }
}
