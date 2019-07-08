package net.javik.DeafGame.DeafButtons;

import net.javik.DeafGame.DeafBasics.DeafWindow;
import net.javik.DeafGame.DeafMenus.DeafMenu;
import org.jetbrains.annotations.NotNull;

public class ExitButton extends DeafButton implements IDeafButton {
    public ExitButton() {
        super("Beenden");
    }

    @Override
    public void buttonTrigger(@NotNull DeafWindow window, @NotNull DeafMenu parent) {
        System.exit(0);
    }
}
