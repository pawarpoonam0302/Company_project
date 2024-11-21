package nec.demo;

import com.demo.finance.Finance;
import com.demo.finance.FinanceDao;
import com.demo.finance.FinanceService;
import nec.demo.common.DatabaseConnection;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFinances {

        private static FinanceService financeser;
        private static Connection connection;
        FinanceDao dao = new FinanceDao();

        @BeforeAll
       public static void setUp()  {
            connection = DatabaseConnection.getConnection();
            financeser = new FinanceService();
        }

        @BeforeEach
      public void cleanUpBefore()  {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM payroll");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        @Test
       public void testCreatePayroll() {
            Finance finance = new Finance(1, "John Doe", 55000.00);
            dao.createPayroll(finance);

            Finance fetchedPayroll = dao.readPayrollById(1);
            assertNotNull(fetchedPayroll, "Payroll should not be null");
            assertEquals("John Doe", fetchedPayroll.getEmployeeName());
            assertEquals(55000.00, fetchedPayroll.getSalary());
        }

        @Test
      public void testReadAllPayrolls() {
            Finance finance = new Finance(1, "Alice Johnson", 60000);
            Finance finance2 = new Finance(2, "Bob smith", 70000);

            financeser.addPayroll(finance);
            financeser.addPayroll(finance2);
            List<Finance> finances = financeser.getAllPayrolls();
            assertEquals(2, finances.size());
        }




        @Test
       public void testDeletePayroll() {
            dao.deletePayroll(1);

            Finance deletedPayroll = dao.readPayrollById(1);
            assertNull(deletedPayroll, "Payroll should be null after deletion");
        }

        @AfterAll
        public static void tearDown() throws SQLException {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }



