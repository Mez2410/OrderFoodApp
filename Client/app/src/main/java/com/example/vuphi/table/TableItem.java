package com.example.vuphi.table;

/**
 * Created by vuphi on 3/26/2017.
 */

public class TableItem {
    private String ImageTable;
    private Integer NumberTable;
    private Boolean SttTab;
    private Boolean SttBtn;

    public TableItem() {
    }

    public TableItem(String imageTable, Integer numberTable, Boolean sttTab, Boolean sttBtn) {
        ImageTable = imageTable;
        NumberTable = numberTable;
        SttTab = sttTab;
        SttBtn = sttBtn;
    }

    public String getImageTable() {
        return ImageTable;
    }

    public void setImageTable(String imageTable) {
        ImageTable = imageTable;
    }

    public Integer getNumberTable() {
        return NumberTable;
    }

    public void setNumberTable(Integer numberTable) {
        NumberTable = numberTable;
    }

    public Boolean getSttTab() {
        return SttTab;
    }

    public void setSttTab(Boolean sttTab) {
        SttTab = sttTab;
    }

    public Boolean getSttBtn() {
        return SttBtn;
    }

    public void setSttBtn(Boolean sttBtn) {
        SttBtn = sttBtn;
    }
}
