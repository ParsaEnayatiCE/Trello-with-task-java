import Task.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import user.User;

import java.util.HashMap;
import java.util.Map;

public class TestEasyFuncs {
    User user;
    Task task;

    @Before
    public void before() {
        user = new User("Parsa", "Parsakvn", "123Parsamord", "12/12/1312");
    }

    @Test
    public void TestName() {
        Assert.assertEquals("Parsa", user.getFullName());
    }

    @Test
    public void TestPass() {
        Assert.assertEquals("123Parsamord", user.getPassword());
    }

    @Test
    public void TestPasswordBool() {
        Assert.assertEquals(false, user.isPasswordCorrect("123"));
        Assert.assertEquals(false, user.isPasswordCorrect("123Ar"));
        Assert.assertEquals(false, user.isPasswordCorrect("12Par"));
        Assert.assertEquals(false, user.isPasswordCorrect("123parsa"));
        Assert.assertEquals(true, user.isPasswordCorrect("123Parsamord"));
    }


    @Test
    public void MapTest() {
        Map<String, User> users = new HashMap<>();
        users.put(user.getUsername(), user);
        Assert.assertEquals(users, User.getUsersList());
    }

    @Test
    @DisplayName("TestingTask")
    public void TestTask() {
        task = new Task("Test Task");
        user.addAssignedTask(task);
        task.setWhoTaskIsAssignedTo(user);
        Assert.assertEquals(user, task.getWhoTaskIsAssignedTo());
        Assert.assertEquals("Test Task", task.getTitle());
        Assert.assertEquals(1, task.getId());
    }

    
}
