package com.demo.it;

import org.apache.log4j.Logger;

import java.util.List;

public class AssetService {

    private final AssetDao assetDao = new AssetDao();

    static Logger log = Logger.getLogger(AssetService.class.getName());

    public void addAsset(Asset asset) {
        assetDao.assignAssetToEmployee(asset);
       log.info("Asset added: " + asset);
    }

    public Asset getAsset(int id) {
        Asset asset = assetDao.readAsset(id);
        if (asset != null) {
            System.out.println("Asset retrieved: " + asset);
        } else {
            log.warn("Asset with ID " + id + " not found.");
        }
        return asset;
    }


    public List<Asset> getAllAssets() {
        return assetDao.readAllAssets();
    }

    public void updateAsset(Asset asset) {
        assetDao.updateAsset(asset);
        System.out.println("Asset updated: " + asset);
    }

    public void deleteAsset(int id) {
        assetDao.deleteAsset(id);
        System.out.println("Asset with ID " + id + " deleted.");
    }


}
