package com.example.vuphi.main;

/**
 * Created by vuphi on 4/22/2017.
 */

public class ItemMenuHome {
    Integer ImageItem;
    String NameItem;

    public ItemMenuHome(Integer imageItem, String nameItem) {
        ImageItem = imageItem;
        NameItem = nameItem;
    }

    public Integer getImageItem() {
        return ImageItem;
    }

    public void setImageItem(Integer imageItem) {
        ImageItem = imageItem;
    }

    public String getNameItem() {
        return NameItem;
    }

    public void setNameItem(String nameItem) {
        NameItem = nameItem;
    }
}
