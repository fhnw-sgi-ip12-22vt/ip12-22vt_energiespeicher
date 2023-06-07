package ch.fhnw.clashofenergiespeicher.model.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.fhnw.clashofenergiespeicher.model.world.EnergyStorage.Storage;
import org.junit.jupiter.api.Test;

public class EnergyStorageTest {
    int energy = 1500;
    EnergyStorage eStorage = new EnergyStorage(Storage.PUMPED_STORAGE);

    @Test
    void testIsEnergySaved() {
        assertTrue(eStorage.saveEnergy(energy));
    }

    @Test
    void testGetCurrentEnergy() {
        eStorage.saveEnergy(energy);
        assertEquals(eStorage.getCurrentEnergy(), energy);
    }

    @Test
    void testUsingAllEnergy() {
        eStorage.setCurrentEnergy(energy);
        assertEquals(0, eStorage.useEnergy(energy));
    }

    @Test
    void testUsingSomeEnergy() {
        eStorage.setCurrentEnergy(energy-1000);
        assertEquals(1000, eStorage.useEnergy(energy));
    }

    @Test
    void testUsingAllEnergyWithEnoughBackup() {
        eStorage.setCurrentEnergy(energy+1000);
        assertEquals(0, eStorage.useEnergy(energy));
    }
}
