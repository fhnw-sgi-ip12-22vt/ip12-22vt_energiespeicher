package ch.fhnw.clashofenergiespeicher.model.world;

import ch.fhnw.clashofenergiespeicher.settings.Setting;

/**
 * Class holding information about the Energy Storage a player has.
 */
public class EnergyStorage {
    Storage storage;
    private int currentEnergy;
    private int maxEnergy;

    /**
     * @param storage 1 out of 4 storage option which is also represented on the
     * playing field
     */
    public EnergyStorage(Storage storage) {
        this.storage = storage;
        this.currentEnergy = 10000;
        this.maxEnergy = storage.maxEnergyCapacity;
    }

    /**
     * method for saving energy into the storage
     * 
     * @param intEnergy amount of energy to be stored
     * 
     * @return boolean if energy got saved (true) or if maxEnergyCapacity is reached (false)
     */
    public boolean saveEnergy(double intEnergy) {
        if (currentEnergy+intEnergy < storage.maxEnergyCapacity) {
            currentEnergy += intEnergy;
            System.out.println("EnergyStorage --> Saved "+ currentEnergy + " Energy");
            return true;
        }
        return false;
    }

    /**
     * method for using energy out of storage
     * 
     * @param intEnergy amount of energy to be used
     * 
     * @return leftover Energy in storage
     */
    public double useEnergy(double intEnergy) {
        if (currentEnergy >= intEnergy) {
            currentEnergy -= intEnergy;
            return 0;
        } else {
            double leftOver = intEnergy - currentEnergy;
            currentEnergy = 0;
            return leftOver;
        }
    }

    /**
     * enum of the different storages we use in our game
     */
    public enum Storage {
        COMPRESSED_AIR_STORAGE(Setting.MAX_ENERGY_BIG_STORAGE),
        PUMPED_STORAGE(Setting.MAX_ENERGY_BIG_STORAGE), CRANE(Setting.MAX_ENERGY_SMALL_STORAGE),
        FLYWHEEL(Setting.MAX_ENERGY_SMALL_STORAGE);

        final int maxEnergyCapacity;

        Storage(int maxEnergyCapacity) {
            this.maxEnergyCapacity = maxEnergyCapacity;
        }
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }
}
