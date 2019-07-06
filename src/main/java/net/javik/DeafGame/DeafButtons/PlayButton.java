package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafMenus.DeafMenu;

public class PlayButton extends DeafButton implements IDeafButton{
    public PlayButton() {
        super("Spielen");
    }

    public void buttonTrigger(DeafWindow window, DeafMenu parent) {
        System.out.println(this.getParent());
        System.out.println(parent.getParent());
        System.out.println(window);
        //parent.close();
    }
}
