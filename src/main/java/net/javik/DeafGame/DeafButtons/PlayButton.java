package net.javik.DeafGame.DeafButtons;

public class PlayButton extends DeafButton {
    public PlayButton() {
        super("Spielen");
    }

    public void buttonTrigger() {
        System.out.println("Play Btn");
    }
}
