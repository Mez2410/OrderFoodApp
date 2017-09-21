package com.example.vuphi.bill.BillOrder;

/**
 * Created by vuphi on 5/19/2017.
 */

public class ItemBillOrder {

    String NameFood, TimeOrder;
    Integer NumberTab, CostFood, QuantumFood;

    public ItemBillOrder() {

    }

    public ItemBillOrder(String nameFood, String timeOrder, Integer numberTab, Integer costFood, Integer quantumFood) {
        NameFood = nameFood;
        TimeOrder = timeOrder;
        NumberTab = numberTab;
        CostFood = costFood;
        QuantumFood = quantumFood;
    }

    public String getNameFood() {
        return NameFood;
    }

    public void setNameFood(String nameFood) {
        NameFood = nameFood;
    }

    public Integer getQuantumFood() {
        return QuantumFood;
    }

    public void setQuantumFood(Integer quantumFood) {
        QuantumFood = quantumFood;
    }

    public Integer getCostFood() {
        return CostFood;
    }

    public void setCostFood(Integer costFood) {
        CostFood = costFood;
    }

    public String getTimeOrder() {
        return TimeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        TimeOrder = timeOrder;
    }

    public Integer getNumberTab() {
        return NumberTab;
    }

    public void setNumberTab(Integer numberTab) {
        NumberTab = numberTab;
    }
}
