package com.demo.finance;

import org.apache.log4j.Logger;

import java.util.List;

public class FinanceService {

    private FinanceDao financeDao = new FinanceDao();

    static Logger log = Logger.getLogger(FinanceService.class.getName());

    public FinanceService( ) {

    }

    public void addPayroll(Finance finance) {
        if (finance.getSalary() > 0) {
            financeDao.createPayroll(finance);
        } else {
            throw new IllegalArgumentException("Salary must be greater than 0.");
        }
    }

    public Finance getPayrollById(int id) {
        return financeDao.readPayrollById(id);
    }

    public List<Finance> getAllPayrolls() {

        return financeDao.readAllPayrolls();
    }





    public void deletePayroll(int id) {

        financeDao.deletePayroll(id);
    }
}

