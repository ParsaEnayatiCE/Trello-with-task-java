import controllers.Controller;
import controllers.RegisterMenu;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MainProgTest {
    @Test
    public void test() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;

        String inpuTamamadFuckHim = "LOGIN\nmamad\nmamad";

        ByteArrayInputStream in = new ByteArrayInputStream(inpuTamamadFuckHim.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);


        Controller controller = new Controller();
        controller.run(scanner);

        Assert.assertEquals("""
                Welcome to trello!
                Please type LOGIN for logging in or SIGN UP for register and EXIT for exit program.
                sgin up
                Invalid Command
                Welcome to trello!
                Please type LOGIN for logging in or SIGN UP for register and EXIT for exit program.
                You can start each menu from beginning only by pressing Enter button
                Please enter your full name
                You can start each menu from beginning only by pressing Enter button
                Please enter your full name
                Please enter your username
                Please enter your password
                Password is weak! Please try again.
                Please enter your password again
                Passwords do not matches! Please try again
                Please enter your birthday with this format: dd/mm/yyyy
                Birthday format is not valid! Please try again
                You registered successfully.
                Welcome to trello!
                Please type LOGIN for logging in or SIGN UP for register and EXIT for exit program.
                """, outContent.toString());

        //System.setIn((sysInBackup));
    }
}
