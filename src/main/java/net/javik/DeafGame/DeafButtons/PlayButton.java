package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafMenus.DeafMenu;
import net.javik.DeafGame.DeafMenus.GameMenu;
import org.jetbrains.annotations.NotNull;

public class PlayButton extends DeafButton {
    public PlayButton() {
        super("Spielen");
    }

    @Override
    public void buttonTrigger(@NotNull DeafWindow window, @NotNull DeafMenu parent) {
       parent.close(window);

        window.setLayout(null);

        GameMenu gameMenu = new GameMenu(window, window.getHeight(), window.getWidth());
        gameMenu.setBounds(0,0,window.getWidth(), window.getHeight());

        window.add(gameMenu);
    }
}
