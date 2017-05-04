import java.util.Scanner;  ///Create the base code from Login.
import java.lang.*;

public class LogoutExamHelper {  //The user can logout and nobody can view their questions.

public static void main(String[] args){    
    


    String Username;
    String Password;
    String Logout;

    Password = "123";
    Username = "wis"
            + "dom";
    
    Logout = "Y";

    Scanner input1 = new Scanner(System.in);
    System.out.println("Enter Username : ");
    String username = input1.next();

    Scanner input2 = new Scanner(System.in);
    System.out.println("Enter Password : ");

    String password = input2.next();

    if (username.equals(Username) && password.equals(Password)) {

        System.out.println("Access Granted! Welcome!");
        
        
        Scanner input3 = new Scanner(System.in);
        
        System.out.println("Logout? (Press Y)");
        String logout = input3.next();
              
        
        if (logout.equals(Logout))
            System.out.println("Login Module - logout called");
                System.exit(0);
    }
    else if (username.equals(Username)) {
        System.out.println("Invalid Password!");

    } else if (password.equals(Password)) {
        System.out.println("Invalid Username!");
            System.exit(0);
    } else {
        System.out.println("Invalid Username & Password!");
            System.exit(0);
    }

    
}

}
