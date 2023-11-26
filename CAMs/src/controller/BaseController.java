package controller;

import entity.CentralManager;
import utils.Base;
import utils.BaseInterface;

/**
 * The BaseController class is a base controller that extends the Base class and implements the BaseInterface.
 * It serves as a foundation for other controllers in the system.
 * This class holds a reference to a CentralManager, providing common functionality for interacting with the central management system.
 * 
 * @author Group 2
 * @since 2023-11-26
 */
public class BaseController extends Base implements BaseInterface {

    /**
     * The centralManager field holds a reference to the CentralManager instance.
     */
    CentralManager centralManager;

    /**
     * Constructs a BaseController with the specified CentralManager.
     *
     * @param centralManager The CentralManager instance to associate with the BaseController.
     */
    public BaseController(CentralManager centralManager) {
        super(centralManager);
        this.centralManager = centralManager;
    }
}
