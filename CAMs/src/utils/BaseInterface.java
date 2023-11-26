package utils;

import entity.CentralManager;

/**
 * The BaseInterface interface defines a contract for classes that provide access to a CentralManager.
 * Classes implementing this interface are expected to provide an implementation for retrieving the central manager.
 * @author Group 2
 * @since 2023-11-26
 */
public interface BaseInterface {
	
	/**
     * Gets the CentralManager associated with the implementing class.
     *
     * @return The CentralManager instance associated with the implementing class.
     */
    public CentralManager getCentralManager();
}
