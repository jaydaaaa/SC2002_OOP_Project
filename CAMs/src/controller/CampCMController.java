package controller;

import entity.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CampCMController extends StudentController{


	public CampCMController(CentralManager centralManager){
		super(centralManager);
	}

	public CampCM getCurrentCampCM() {
		return (CampCM) this.currentUser;
	}

}
