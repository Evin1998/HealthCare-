package com.inti.student.healthcareordering.Database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import com.inti.student.healthcareordering.LoginActivity;
import com.inti.student.healthcareordering.Model.Cart;
import com.inti.student.healthcareordering.Model.Product;
import com.inti.student.healthcareordering.Model.User;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "health_care_DB";

    // Contacts table name
    private static final String TABLE_USER = "users";
    private static final String TABLE_CART = "cart";
    private static final String TABLE_HISTORY = "history";

    // Users Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CONTACT= "contact";

    // Cart
    private static final String KEY_CART_ID = "cart_id";
    private static final String KEY_CART_NAME = "cart_name";
    private static final String KEY_CART_CODE = "cart_code";
    private static final String KEY_CART_DESC = "cart_description";
    private static final String KEY_CART_PRICE = "cart_price";
    private static final String KEY_CART_USERNAME = "username";

    //History
    private static final String KEY_HISTORY_ID = "history_id";
    private static final String KEY_HISTORY_NAME = "history_name";
    private static final String KEY_HISTORY_CODE = "history_code";
    private static final String KEY_HISTORY_DESC = "history_description";
    private static final String KEY_HISTORY_PRICE = "history_price";
    private static final String KEY_HISTORY_USERNAME = "username";

    // SQL Query to create table
    public static final String CREATE_TABLE_USERS=
            "CREATE TABLE " + TABLE_USER + " ("
            + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_CONTACT + " TEXT );";

    public static final String CREATE_TABLE_CART=
            "CREATE TABLE " + TABLE_CART + " ("
                    + KEY_CART_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_CART_CODE + " TEXT,"
                    + KEY_CART_NAME + " TEXT,"
                    + KEY_CART_DESC + " TEXT,"
                    + KEY_CART_PRICE + " DOUBLE,"
                    + KEY_CART_USERNAME+ " TEXT );";

    public static final String CREATE_TABLE_HISTORY=
            "CREATE TABLE " + TABLE_HISTORY + " ("
                    + KEY_HISTORY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_HISTORY_CODE + " TEXT,"
                    + KEY_HISTORY_NAME + " TEXT,"
                    + KEY_HISTORY_DESC + " TEXT,"
                    + KEY_HISTORY_PRICE + " DOUBLE,"
                    + KEY_HISTORY_USERNAME+ " TEXT );";

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_HISTORY);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_CART);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_HISTORY);
    }

    /****
     *
     *
     * USER FUNCTIONS
     *
     */
    //Insert values to the table contacts
    public void insertUser(User user){

        SQLiteDatabase db = this.getReadableDatabase();

        // Create a container for the data
        ContentValues values=new ContentValues();

        String username = user.getUsername();
        String password = user.getPassword();
        String contact = user.getContact();

        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_CONTACT, contact);

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    // Check user input when login
    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE USERNAME=? AND PASSWORD=?",
                new String[]{username,password});

        if(cursor != null){
            if(cursor.getCount()>0){
                cursor.close();
                return true;
            }
        }

        return false;
    }

    // Edit user info when edit profile
    public void updateUser(String oriname, String name, String pass, String contact) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, name);
        contentValues.put(KEY_PASSWORD, pass);
        contentValues.put(KEY_CONTACT, contact);
        String whereClause = "username=?";
        String whereArgs[] = {oriname};
        db.update(TABLE_USER, contentValues, whereClause, whereArgs);
    }

    // fetch user data profile/session
    public Cursor fetchData(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE USERNAME=? AND PASSWORD=?",
                new String[]{username,password});

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // check if username exists
    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE USERNAME=?",
                new String[]{username});

        if(cursor != null){
            if(cursor.getCount()>0){
                cursor.close();
                return true;
            }
        }

        return false;
    }

    /****
     *
     *
     * CART FUNCTIONS
     *
     */

    // Add to cart
    public void insertCart(Product product, String username){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        String code = product.getCode();
        String name = product.getName();
        String desc = product.getDescription();
        Double price = product.getPrice();

        values.put(KEY_CART_CODE, code);
        values.put(KEY_CART_NAME, name);
        values.put(KEY_CART_DESC, desc);
        values.put(KEY_CART_PRICE, price);
        values.put(KEY_CART_USERNAME, username);

        db.insert(TABLE_CART, null, values);
        db.close();
    }

    // Delete cart item one by one
    public void deleteCart(String id, String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String whereClause = "USERNAME=? AND CART_ID=?";
        String whereArgs[] = {username, id};
        db.delete(TABLE_CART, whereClause, whereArgs );
    }

    // Display cart items
    public ArrayList<Product> displayCart(String username){
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART + " WHERE USERNAME=? ",
                new String[]{username});

        while(cursor.moveToNext()){
            String id = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_CART_ID)));
            String code = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_CART_CODE)));
            String name = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_CART_NAME)));
            String desc = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_CART_DESC)));
            Double price = Double.parseDouble(cursor.getString(cursor.getColumnIndex(KEY_CART_PRICE)));

            list.add(new Product(id, code, name, desc, price));
        }

        return list;
    }

    /****
     *
     *
     * ORDER HISTORY FUNCTIONS
     *
     */

    // When payment is done
    public void insertHistory(Product product, String username){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        String code = product.getCode();
        String name = product.getName();
        String desc = product.getDescription();
        Double price = product.getPrice();

        values.put(KEY_HISTORY_CODE, code);
        values.put(KEY_HISTORY_NAME, name);
        values.put(KEY_HISTORY_DESC, desc);
        values.put(KEY_HISTORY_PRICE, price);
        values.put(KEY_HISTORY_USERNAME, username);

        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    // display history
    public ArrayList<Product> retrieveHistory(String username){
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_HISTORY + " WHERE USERNAME=? ",
                new String[]{username});

        while(cursor.moveToNext()){
            String id = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_HISTORY_ID)));
            String code = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_HISTORY_CODE)));
            String name = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_HISTORY_NAME)));
            String desc = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_HISTORY_DESC)));
            Double price = Double.parseDouble(cursor.getString(cursor.getColumnIndex(KEY_HISTORY_PRICE)));

            list.add(new Product(id, code, name, desc, price));
        }

        return list;
    }


}