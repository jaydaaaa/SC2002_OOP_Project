public class StudentController {

	/**
	 * 
	 * @param String
	 */
	public void addEnquiry(int String) {
		// TODO - implement StudentController.addEnquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 * @param enquiry
	 */
	public void deleteEnquiry(String student, String enquiry) {
		// TODO - implement StudentController.deleteEnquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Student
	 */
	public void enrollForCamp(Student student, Camp camp) {
	    if (!student.getMyCamps().contains(camp)) {
	        student.getMyCamps().add(camp);
	        System.out.println(student.getUserID() + " has successfully enrolled for " + camp.getCampName());
	    } else {
	        System.out.println(student.getUserID() + " is already enrolled in " + camp.getCampName());
	    }
	}
	}

	public void withdrawFromCamp(Student student, Camp camp) {
	    if (student.getMyCamps().contains(camp)) {
	        student.getMyCamps().remove(camp);
	        System.out.println(student.getUserID() + " has successfully withdrawn from " + camp.getCampName());
	    } else {
	        System.out.println(student.getUserID() + " is not enrolled in " + camp.getCampName());
	    }
	}
	}

}