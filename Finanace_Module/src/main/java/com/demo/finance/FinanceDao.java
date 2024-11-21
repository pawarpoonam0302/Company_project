package com.demo.finance;

import nec.demo.common.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceDao {

    Connection conn =DatabaseConnection.getConnection();


    public FinanceDao() {
    }



    public void createPayroll(Finance finance) {
        String query = "INSERT INTO payroll (id, employee_name, salary) VALUES (?, ?, ?)";
        try (
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, finance.getId());
            statement.setString(2, finance.getEmployeeName());
            statement.setDouble(3, finance.getSalary());
            statement.executeUpdate();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }


    public void deletePayroll(int id) {
        String query = "DELETE FROM payroll WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Finance> readAllPayrolls() {
        List<Finance> payrolls = new ArrayList<>();
        String query = "SELECT * FROM payroll";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Finance payroll = new Finance(
                        resultSet.getInt("id"),
                        resultSet.getString("employee_name"),
                        resultSet.getDouble("salary")
                );
                payrolls.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }

    public Finance readPayrollById(int id) {
        String query = "SELECT * FROM payroll WHERE id = ?";
            try (
                 PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return new Finance(
                            resultSet.getInt("id"),
                            resultSet.getString("employee_name"),
                            resultSet.getDouble("salary")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
