//package com.example.vuphi.order.DatabaseOrder;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.example.vuphi.bill.BillOrder.ItemBillOrder;
//import com.example.vuphi.order.OrderTable.OrderTable;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by vuphi on 5/11/2017.
// */
//
//public class DatabaseHandler extends SQLiteOpenHelper {
//
//    public static final int DATABASE_VERSION = 2;
//    public static final String DATABASE_NAME = "DataOrder";
//
//    public static final String TABLE_ORDER = "TableOrder";
//    public static final String BILL_ORDER = "BillOrder";
//
//    private static final String KEY_ID_ORDER = "ID";
//    private static final String TABLE_CODE_ORDER = "TableCode";
//    private static final String BILL_CODE_ORDER = "BillCode";
//    private static final String EMPLOYEE_CODE_ORDER = "EmployeeCode";
//    private static final String EMPLOYEE_NAME_ORDER = "EmployeeName";
//
//    private static final String BILL_CODE = "BillCode";
//    private static final String FOOD_NAME_BILL = "FoodName";
//    private static final String QUANTUM_BILL = "Quantum";
//    private static final String COST_BILL = "Cost";
//    private static final String TIME_ORDER_FOOD_BILL = "TimeBill";
//
//    public DatabaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "("
//                + BILL_CODE_ORDER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + TABLE_CODE_ORDER + " INTEGER,"
//                + EMPLOYEE_CODE_ORDER + " INTEGER,"
//                + EMPLOYEE_NAME_ORDER + " TEXT" + ")";
//        db.execSQL(CREATE_ORDER_TABLE);
//
//        String CREATE_ORDER_BILL = "CREATE TABLE " + BILL_ORDER + "("
//                + KEY_ID_ORDER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + BILL_CODE + " INTEGER,"
//                + FOOD_NAME_BILL + " TEXT,"
//                + QUANTUM_BILL + " INTEGER," + COST_BILL + " INTEGER,"
//                + TIME_ORDER_FOOD_BILL + " TEXT" + ")";
//        db.execSQL(CREATE_ORDER_BILL);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//    // Mở kết nối CSDL SQLite
//    public SQLiteDatabase Open() {
//        return this.getWritableDatabase();
//    }
//
//    // xóa database
//    public void DeleteDatabase(Context context) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS " + BILL_ORDER);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
//        context.deleteDatabase(DATABASE_NAME);
//    }
//
//    public void addFood(ItemBillOrder itemBillOrder) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(FOOD_NAME_BILL, itemBillOrder.getNameFood());
//        values.put(QUANTUM_BILL, itemBillOrder.getQuantumFood());
//        values.put(COST_BILL, itemBillOrder.getCostFood());
//        values.put(TIME_ORDER_FOOD_BILL, itemBillOrder.getTimeOrder());
//
//        // Inserting Row
//        db.insert(BILL_ORDER, null, values);
//        db.close();
//    }
//
//    // thêm các giá trị khi order Table vào bảng TableOrder
//    public void addOrderTable(OrderTable orderTable) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(TABLE_CODE_ORDER, orderTable.getNumberTable());
//        values.put(EMPLOYEE_CODE_ORDER, orderTable.getEmployeeCode());
//        values.put(EMPLOYEE_NAME_ORDER, orderTable.getEmployeeName());
//        db.insert(TABLE_ORDER, null, values);
//        db.close();
//    }
//
//    // Getting All Food
//    public List<ItemBillOrder> getAllFood() {
//        List<ItemBillOrder> itemBOList = new ArrayList<>();
//        // Select All Query
//        String selectQuery = "SELECT * FROM " + BILL_ORDER;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                ItemBillOrder itemBO = new ItemBillOrder();
//                itemBO.setNameFood(cursor.getString(0));
//                itemBO.setQuantumFood(cursor.getString(1));
//                itemBO.setCostFood(cursor.getString(2));
//                itemBO.setTimeOrder(cursor.getString(3));
//                // Adding food to list
//                itemBOList.add(itemBO);
//            } while (cursor.moveToNext());
//        }
//
//        // return itemBOList
//        return itemBOList;
//    }
//}
