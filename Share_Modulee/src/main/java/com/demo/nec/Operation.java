package com.demo.nec;


import com.demo.finance.Finance;
import com.demo.finance.FinanceService;
import com.demo.hr.HRDao;
import com.demo.hr.HRInfo;
import com.demo.hr.HRService;
import com.demo.it.Asset;
import com.demo.it.AssetService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;


public class Operation {


    private static HRDao hrDao;
    private static final HRService hrService = new HRService();
    private static Scanner scanner = new Scanner(System.in);
    private static final AssetService assetService = new AssetService();
    private  static final FinanceService finservice = new FinanceService();

    private static final Logger logger = LogManager.getLogger(Operation.class);

    public static void main(String[] args) {


        logger.trace("1.This is a TRACE message.");
        logger.debug("2.This is a DEBUG message.");
        logger.info("3.This is an INFO message.");
        logger.warn("4.This is a WARN message.");



        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter department name (HR, Finance, IT): ");
        String department = scanner.nextLine();

        switch (department.toLowerCase()) {
            case "hr":
                handleHRDepartment();
                break;

            case "finance":
                handleFinanceDepartment();
                break;

            case "it":
                handleITDepartment();
                break;

            default:
                System.out.println("Invalid department entered. Please choose from HR, Finance, or IT.");
                break;
        }

        scanner.close();
    }

    private static void handleHRDepartment() {
        System.out.println("HR Department Operations:");
        System.out.println("1.Handle Recruitment");
        System.out.println("2. Manage Employee Records");
        System.out.println("3. update Employee data");
        System.out.println("4. Terminate Employee");
        System.out.println("5.view all employee record");


        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addEmployee();
                break;
            case 2:
                viewEmployeeById();
                break;
            case 3:
                updateEmployee();
                break;
            case 4:
                deleteEmployee();
                break;
            case 5:
                listAllEmployees();
                break;
            default:
                System.out.println("Invalid choice for HR Department.");
                break;
        }
    }

    private static void addEmployee() {
        System.out.println("\n--- Add Employee ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        HRInfo hrInfo = new HRInfo(0, name, position, salary);
        hrService.addEmployee(hrInfo);
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployeeById() {
        System.out.println("\n--- View Employee ---");
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        HRInfo hrInfo = hrService.getEmployeeById(id);
        if (hrInfo != null) {
            System.out.println(hrInfo);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void updateEmployee() {
        System.out.println("\n--- Update Employee ---");
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        HRInfo hrInfo = hrService.getEmployeeById(id);
        if (hrInfo != null) {
            System.out.println("Enter new name (or press Enter to keep current: " + hrInfo.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isBlank()) {
                hrInfo.setName(name);
            }

            System.out.println("Enter new position (or press Enter to keep current: " + hrInfo.getPosition() + "): ");
            String position = scanner.nextLine();
            if (!position.isBlank()) {
                hrInfo.setPosition(position);
            }

            System.out.print("Enter new salary (or press Enter to keep current: " + hrInfo.getSalary() + "): ");
            String salaryInput = scanner.nextLine();
            if (!salaryInput.isBlank()) {
                double salary = Double.parseDouble(salaryInput);
                hrInfo.setSalary(salary);
            }

            hrService.updateEmployee(hrInfo);
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmployee() {
        System.out.println("\n--- Delete Employee ---");
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        HRInfo hrInfo = hrService.getEmployeeById(id);
        if (hrInfo != null) {
            hrService.deleteEmployee(id);
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void listAllEmployees() {
        System.out.println("\n--- List of All Employees ---");
        List<HRInfo> employees = hrService.listAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employees.forEach(System.out::println);
        }
    }



    private static void handleITDepartment() {
        System.out.println("\n--- Asset Management System ---");
        System.out.println("1. Add Asset");
        System.out.println("2. View Asset by ID");
        System.out.println("3. View All Assets");
        System.out.println("4. Delete Asset");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addAsset();
                break;
            case 2:
                viewAssetById();
                break;
            case 3:
                viewAllAssets();
                break;
            case 4:
                deleteAsset();
                break;
            default:
                System.out.println("Invalid choice for IT Department.");
                break;
        }
    }


    public static void addAsset()
    {
        System.out.print("Enter Emp ID: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Asset id: ");
        int assetId = scanner.nextInt();
        System.out.print("Enter Asset Name: ");
        String name = scanner.next();
        Asset newAsset = new Asset(empId, assetId , name);
        assetService.addAsset(newAsset);
    }

    private static void viewAssetById() {
        System.out.print("Enter Asset ID to view: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Asset asset = assetService.getAsset(id);
        if (asset != null) {
            System.out.println("Asset Details: " + asset);
        } else {
            System.out.println("Asset with ID " + id + " not found.");
        }
    }

    private static void viewAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        if (assets.isEmpty()) {
            System.out.println("No assets found.");
        } else {
            System.out.println("\n--- All Assets ---");
            assets.forEach(System.out::println);
        }
    }

    private static void deleteAsset() {
        System.out.print("Enter Asset ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        assetService.deleteAsset(id);
    }


    private static void handleFinanceDepartment() {
        System.out.println("Finance Department Operations:");
        System.out.println("1.Salary Disbursements");
        System.out.println("2. remove payroll Report");
        System.out.println("3.Generate Financial Reports ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addpayrollData();
                break;
            case 2:
                deletepayrolldata();
                break;
            case 3:
                getallPayrollData();
                break;
            default:
                System.out.println("Invalid choice for Finance Department.");
                break;
        }
    }


    private static void addpayrollData(){
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Employee Name: ");
        scanner.nextLine(); // consume newline
        String name = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        Finance finance = new Finance(id,name,salary);
        finservice.addPayroll(finance);
    }

    private static void getallPayrollData(){

        finservice.getAllPayrolls().forEach(System.out::println);
    }

    private static void deletepayrolldata(){
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        finservice.deletePayroll(id);
    }
}
