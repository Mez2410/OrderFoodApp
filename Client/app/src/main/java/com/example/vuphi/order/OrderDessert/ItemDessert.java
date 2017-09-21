package com.example.vuphi.order.OrderDessert;

/**
 * Created by vuphi on 4/2/2017.
 */

public class ItemDessert {
    String nameDessert;
    Integer costDessert;
    String imageDessert;

    public ItemDessert() {

    }

    public ItemDessert(String nameDessert, Integer costDessert, String imageDessert) {
        this.nameDessert = nameDessert;
        this.costDessert = costDessert;
        this.imageDessert = imageDessert;
    }

    public void setNameDessert (String nameDessert) {
        this.nameDessert = nameDessert;
    }

    public String getNameDessert () {
        return nameDessert;
    }

    public void setCostDessert(Integer costDessert) {
        this.costDessert = costDessert;
    }

    public Integer getCostDessert() {
        return costDessert;
    }

    public void setImageDessert (String imageDessert) {
        this.imageDessert = imageDessert;
    }

    public String getImageDessert () {
        return imageDessert;
    }
}


