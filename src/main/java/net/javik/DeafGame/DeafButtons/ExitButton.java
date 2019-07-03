package net.javik.DeafGame.DeafButtons;

public class ExitButton extends DeafButton {
    public ExitButton() {
        super("Beenden");
    }

    public void buttonTrigger() {
        System.exit(0);
    }
}
