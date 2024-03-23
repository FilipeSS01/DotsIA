package game;

public class Player {
    private boolean player;

    public Player() {
        setPlayer(false);
    }

    public void invert() {
        setPlayer(!isPlayer());
    }

    @Override
    public String toString() {
        return isPlayer() ? "1" : "0";
    }

    // Getters and Setters
    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }
}
