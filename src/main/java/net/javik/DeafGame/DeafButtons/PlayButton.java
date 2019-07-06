package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafMenus.DeafMenu;
import net.javik.DeafGame.DeafMenus.GameMenu;

public class PlayButton extends DeafButton implements IDeafButton{
    public PlayButton() {
        super("Spielen");
    }

    public void buttonTrigger(DeafWindow window, DeafMenu parent) {
        parent.close(window);

        GameMenu gameMenu = new GameMenu();
        parent.add(gameMenu);
    }
}
