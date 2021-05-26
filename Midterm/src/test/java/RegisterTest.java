import controllers.LoginMenu;
import controllers.RegisterMenu;
import org.junit.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RegisterTest {

    @Test
    public void getNameTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("SIGN UP\nmamad\nadmin\n123A\n123Aparsa\n123Aparsaaaa\n123Aparsa\n40/12/2002\n".getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Scanner scanner = new Scanner(in);

        RegisterMenu registerMenu = new RegisterMenu();
        registerMenu.run(scanner);
        Assert.assertEquals("""
                You can start each menu from beginning only by pressing Enter button
                Please enter your full name
                Please enter your username
                Please enter your password
                Password is weak! Please try again.
                Password is weak! Please try again.
                Please enter your password again
                Passwords do not match! Please try again
                Please enter your birthday with this format: dd/mm/yyyy
                Birthday format is not valid! Please try again
                """.toString(), outContent.toString());
            System.setIn(sysInBackup);
    }

    @Test
    public void getLoginTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("LOGIN\nmamad\nadmin\n123Aparsas\ny\n123Aparsa\n".getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Scanner scanner = new Scanner(in);


        Assert.assertEquals("""
                You can start each menu from beginning only by pressing Enter button
                Please enter your full name
                Please enter your username
                Please enter your password
                Password is weak! Please try again.
                Password is weak! Please try again.
                Please enter your password again
                Passwords do not match! Please try again
                Please enter your birthday with this format: dd/mm/yyyy
                Birthday format is not valid! Please try again
                """.toString(), outContent.toString());
        System.setIn(sysInBackup);
    }
}
