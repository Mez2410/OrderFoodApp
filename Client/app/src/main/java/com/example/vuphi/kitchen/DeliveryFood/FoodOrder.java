package com.example.vuphi.kitchen.DeliveryFood;

/**
 * Created by vuphi on 7/20/2017.
 */

public class FoodOrder {
    String NameFood;
    Integer CostFood, QuantumFood;

    public FoodOrder() {

    }

    public FoodOrder(String nameFood, Integer costFood, Integer quantumFood) {
        NameFood = nameFood;
        CostFood = costFood;
        QuantumFood = quantumFood;
    }

    public String getNameFood() {
        return NameFood;
    }

    public void setNameFood(String nameFood) {
        NameFood = nameFood;
    }

    public Integer getCostFood() {
        return CostFood;
    }

    public void setCostFood(Integer costFood) {
        CostFood = costFood;
    }

    public Integer getQuantumFood() {
        return QuantumFood;
    }

    public void setQuantumFood(Integer quantumFood) {
        QuantumFood = quantumFood;
    }
}
