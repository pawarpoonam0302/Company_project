package com.demo.hr;

import java.util.List;

public class HRService {

    private HRDao hrDao = new HRDao();

    public HRService() {
    }

    public void addEmployee(HRInfo hrInfo) {

        hrDao.addHRInfo(hrInfo);
    }

    public HRInfo getEmployeeById(int id) {

        return hrDao.getHRInfoById(id);
    }

    public void updateEmployee(HRInfo hrInfo) {

        hrDao.updateHRInfo(hrInfo);
    }

    public void deleteEmployee(int id) {

        hrDao.deleteHRInfo(id);
    }

    public List<HRInfo> listAllEmployees() {
        return hrDao.getAllHRInfo();
    }
}
