import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
    Database database = new Database();

    @Test
    public void select() throws Exception {
        String info = database.select("Marius");

        Assert.assertTrue(info.contains("Marius"));
    }

}