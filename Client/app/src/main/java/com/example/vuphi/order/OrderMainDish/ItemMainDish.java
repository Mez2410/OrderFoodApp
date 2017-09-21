package com.example.vuphi.order.OrderMainDish;

/**
 * Created by vuphi on 3/29/2017.
 */

public class ItemMainDish {
    String foodName;
    Integer costFood;
    String imageFood;

    public ItemMainDish() {

    }

    public ItemMainDish(String foodName, Integer costFood, String imageFood) {
        this.foodName = foodName;
        this.costFood = costFood;
        this.imageFood = imageFood;
    }

    public void setFoodName (String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setCostFood (Integer costFood) {
        this.costFood = costFood;
    }

    public Integer getCostFood() {
        return costFood;
    }

    public void setImageFood (String imageFood) {
        this.imageFood = imageFood;
    }

    public String getImageFood() {
        return imageFood;
    }
}
