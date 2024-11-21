import com.demo.hr.HRDao;
import com.demo.hr.HRInfo;
import com.demo.hr.HRService;
import nec.demo.common.DatabaseConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestHR {

    private HRService hrService;
    private HRDao hrDao;
    private static Connection connection;

    @BeforeEach
    public void setup(){
        hrDao = new HRDao();
        hrService = new HRService();
    }


    @BeforeAll
    public static void setdbconn()  {
        connection = DatabaseConnection.getConnection();
    }

    @Test
    public void testAddEmployee() {
        HRInfo hrInfo = new HRInfo(1, "Alice Johnson", "Developer", 60000);
        hrService.addEmployee(hrInfo);

        // Check if the employee was added
        HRInfo retrievedInfo = hrService.getEmployeeById(hrInfo.getId());
        assertNotNull(retrievedInfo);
        assertEquals("Alice Johnson", retrievedInfo.getName());
        assertEquals("Developer", retrievedInfo.getPosition());
        assertEquals(60000, retrievedInfo.getSalary());
    }

}
