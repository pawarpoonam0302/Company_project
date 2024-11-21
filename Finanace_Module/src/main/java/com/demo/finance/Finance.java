package com.demo.finance;

public class Finance {

        private int id;
        private String employeeName;
        private double salary;

        // Constructors
        public Finance() {
        }

        public Finance(int id, String employeeName, double salary) {
            this.id = id;
            this.employeeName = employeeName;
            this.salary = salary;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Payroll [id=" + id + ", employeeName=" + employeeName + ", salary=" + salary + "]";
        }
    }


