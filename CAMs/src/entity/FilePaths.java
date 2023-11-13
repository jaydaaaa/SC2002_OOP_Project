public class FilePaths {

    public String STUDENT_LIST;
    public String STAFF_LIST;
    public String CAMP_LIST;
    public String CAMP_COMM_MEM_LIST;
    public static final String STAFF_LIST = "c:/NTU/SC2002 OOP/Proj/staff_list.csv";
    public static final String STUDENT_LIST = "c:/NTU/SC2002 OOP/Proj/student_list.csv";

    public FilePaths(String studentList, String staffList, String campList, String campCommMemList) {
        this.STUDENT_LIST = studentList;
        this.STAFF_LIST = staffList;
        this.CAMP_LIST = campList;
        this.CAMP_COMM_MEM_LIST = campCommMemList;
    }
}
