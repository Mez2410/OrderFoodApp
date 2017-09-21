package com.example.vuphi.kitchen.InformationOrder;

/**
 * Created by vuphi on 7/13/2017.
 */

public class ItemInfoOrder {
    Integer BillCode;
    String EmployeeCode, EmployeeName, NumberTable, TimeOrder;


    public ItemInfoOrder() {

    }

    public ItemInfoOrder(Integer billCode, String numberTable) {
        BillCode = billCode;
        NumberTable = numberTable;
    }

    public ItemInfoOrder(Integer billCode, String employeeCode, String employeeName, String numberTable, String timeOrder) {
        BillCode = billCode;
        EmployeeCode = employeeCode;
        EmployeeName = employeeName;
        NumberTable = numberTable;
        TimeOrder = timeOrder;
    }

    public Integer getBillCode() {
        return BillCode;
    }

    public void setBillCode(Integer billCode) {
        BillCode = billCode;
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        EmployeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getNumberTable() {
        return NumberTable;
    }

    public void setNumberTable(String numberTable) {
        NumberTable = numberTable;
    }

    public String getTimeOrder() {
        return TimeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        TimeOrder = timeOrder;
    }
}
