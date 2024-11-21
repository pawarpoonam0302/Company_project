package com.demo.hr;

import nec.demo.common.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HRDao {


    Connection conn = DatabaseConnection.getConnection();


    public HRDao(){}



    // CREATE
    public void addHRInfo(HRInfo hrInfo) {
        String sql = "INSERT INTO hr_info (name, position, salary) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hrInfo.getName());
            stmt.setString(2, hrInfo.getPosition());
            stmt.setDouble(3, hrInfo.getSalary());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public HRInfo getHRInfoById(int id) {
        String sql = "SELECT * FROM hr_info WHERE id = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new HRInfo(rs.getInt("id"), rs.getString("name"), rs.getString("position"), rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void updateHRInfo(HRInfo hrInfo) {
        String sql = "UPDATE hr_info SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hrInfo.getName());
            stmt.setString(2, hrInfo.getPosition());
            stmt.setDouble(3, hrInfo.getSalary());
            stmt.setInt(4, hrInfo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteHRInfo(int id) {
        String sql = "DELETE FROM hr_info WHERE id = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all records
    public List<HRInfo> getAllHRInfo() {
        List<HRInfo> hrList = new ArrayList<>();
        String sql = "SELECT * FROM hr_info";
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                HRInfo hrInfo = new HRInfo(rs.getInt("id"), rs.getString("name"), rs.getString("position"), rs.getDouble("salary"));
                hrList.add(hrInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hrList;
    }
}
