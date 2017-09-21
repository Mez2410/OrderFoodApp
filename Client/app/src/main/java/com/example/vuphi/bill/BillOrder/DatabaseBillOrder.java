package com.example.vuphi.bill.BillOrder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vuphi on 5/27/2017.
 */

public class DatabaseBillOrder extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DataBillOrder";

    public static final String TABLE_NAME = "TableBillOrder";

    private static final String KEY_ID = "ID";
    private static final String NAME_FOOD_ORDER = "NameFood";
    private static final String QUANTUM_FOOD_ORDER = "QuantumFood";
    private static final String COST_FOOD_ORDER = "CostFood";
    private static final String TIME_ORDER_FOOD = "TimeOrder";
    private static final String IMAGE_ORDER_FOOD = "ImageFood";
    private static final String CODE_BILL_ORDER = "CodeBill";

    public DatabaseBillOrder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BILL_ORDER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_FOOD_ORDER + " TEXT,"
                + QUANTUM_FOOD_ORDER + " INTEGER,"
                + COST_FOOD_ORDER + " TEXT,"
                + ")";
        db.execSQL(CREATE_BILL_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Mở kết nối CSDL SQLite
    public SQLiteDatabase Open() {
        return this.getWritableDatabase();
    }

        // xóa database
    public void DeleteDatabase(Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        context.deleteDatabase(DATABASE_NAME);
    }

    public void addFood(ItemBillOrder itemBillOrder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_FOOD_ORDER, itemBillOrder.getNameFood());
        values.put(QUANTUM_FOOD_ORDER, itemBillOrder.getQuantumFood());
        values.put(COST_FOOD_ORDER, itemBillOrder.getCostFood());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

        // Getting All Food
    public List<ItemBillOrder> getAllFood() {
        List<ItemBillOrder> itemBOList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ItemBillOrder itemBO = new ItemBillOrder();
                itemBO.setNameFood(cursor.getString(0));
                itemBO.setQuantumFood(cursor.getInt(1));
                itemBO.setCostFood(cursor.getInt(2));
                // Adding food to list
                itemBOList.add(itemBO);
            } while (cursor.moveToNext());
        }

        // return itemBOList
        return itemBOList;
    }
}
