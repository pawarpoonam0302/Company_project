package com.demo.it;

import nec.demo.common.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssetDao {

    Connection conn = DatabaseConnection.getConnection();

    public AssetDao() {
    }

    public AssetDao(Connection connection) {
    }


    public boolean assignAssetToEmployee(Asset asset) {

        String query = "INSERT INTO asset (Empid, AssetId, AssetName) VALUES (?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, asset.getAssignedEmployeeId());
            pstm.setInt(2, asset.getAssetId());
            pstm.setString(3, asset.getAssetName());

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Failed to assign asset: " + e.getMessage());
            return false;
        }
    }


    public Asset readAsset(int id) {
        String sql = "SELECT * FROM asset WHERE AssetId = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Asset(rs.getInt("Empid"), rs.getInt("AssetId"), rs.getString("AssetName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Asset> readAllAssets() {
        List<Asset> assets = new ArrayList<>();
        String sql = "SELECT * FROM asset";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                assets.add(new Asset(rs.getInt("Empid"), rs.getInt("AssetId"), rs.getString("AssetName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    public void updateAsset(Asset asset) {
        String sql = "UPDATE asset SET Empid = ?, AssetName = ?,  WHERE AssetId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, asset.getAssignedEmployeeId());
            stmt.setInt(2, asset.getAssetId());
            stmt.setString(3, asset.getAssetName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAsset(int id) {
        String sql = "DELETE FROM asset WHERE AssetId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
