package controller;

import entity.CentralManager;
import utils.Base;
import utils.BaseInterface;

public class BaseController extends Base implements BaseInterface {
    CentralManager centralManager;

    public BaseController(CentralManager centralManager) {
        super(centralManager);
        this.centralManager = centralManager;
    }
}
