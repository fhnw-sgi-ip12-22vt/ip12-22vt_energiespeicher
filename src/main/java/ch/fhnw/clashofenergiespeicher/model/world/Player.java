package ch.fhnw.clashofenergiespeicher.model.world;

/*
 * Player class which includes the current status of all Objects
 */
public class Player {

    private Score score;
    private EnergyStorage [] energyStorages;
    private PlayerNumber playerNumber;
    private int warnings;
    private boolean hasActiveEvent = false;

    public Player(EnergyStorage[] energyStorages, PlayerNumber playerNumber) {
        this.score = new Score();
        this.energyStorages = energyStorages;
        this.warnings = 0;
        this.playerNumber = playerNumber;
    }

    public Score getScore() {
        return score;
    }

    public EnergyStorage[] getEnergyStorages() {
        return energyStorages;
    }

    public int getWarnings() {
        return warnings;
    }

    public void increaseWarning() {
        this.warnings++;
    }

    public boolean getHasActiveEvent() {
        return hasActiveEvent;
    }

    public void setHasActiveEvent(boolean hasActiveEvent) {
        this.hasActiveEvent = hasActiveEvent;
    }

    public PlayerNumber getPlayerNumber() {
        return playerNumber;
    }

    public void calculateScore(double optimalEnergyToStore, double currentEnergyToStore) {
        this.score.calculateScore(optimalEnergyToStore, currentEnergyToStore);
    }
}