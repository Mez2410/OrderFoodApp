package com.example.vuphi.order.OrderDrink;

/**
 * Created by vuphi on 3/30/2017.
 */

public class ItemDrink {
    String nameDrinks;
    Integer costDrinks;
    Integer quantumDrinks;
//    Integer residual;
    String imageDrinks;

    public void setNameDrinks (String nameDrinks) {
        this.nameDrinks = nameDrinks;
    }

    public String getNameDrinks() {
        return nameDrinks;
    }

    public void setCostDrinks (Integer costDrinks) {
        this.costDrinks = costDrinks;
    }

    public Integer getCostDrinks() {
        return costDrinks;
    }

    public void setQuantumDrinks (Integer quantumDrinks) {
        this.quantumDrinks =quantumDrinks;
    }

    public Integer getQuantumDrinks() {
        return quantumDrinks;
    }

    public void setImageDrinks (String imageDrinks) {
        this.imageDrinks = imageDrinks;
    }

    public String getImageDrinks() {
        return imageDrinks;
    }
}

