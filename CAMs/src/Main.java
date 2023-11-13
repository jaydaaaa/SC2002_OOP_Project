import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginAuthentication loginAuthentication = new LoginAuthentication();
        //call csv reader here to initiallise files

        while(true){    
            System.out.println("Enter your User ID:");
            String user = sc.nextLine();
            System.out.println("Enter your password");
            String pass = sc.nextLine();
            if(loginAuthentication.login(user, pass)){
                if(/*user is in STUDENT_LIST*/){
                    //call StudentDisplay
                }
                else{
                    //call StaffDisplay
                }
                break;
            } 
            else{
                System.out.println("Wrong details entered. Try again.");
            } 
        }
    }
}
