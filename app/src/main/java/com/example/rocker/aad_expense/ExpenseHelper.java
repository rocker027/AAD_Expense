package com.example.rocker.aad_expense;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rocker on 2017/2/21.
 */

public class ExpenseHelper extends SQLiteOpenHelper {
    private static ExpenseHelper instance = null;

    String createDB = "CREATE TABLE " + ExpenseCommon.TableExpense.TABLE_NAME + "(" +
            ExpenseCommon.TableExpense.COL_ID + " INTEGER PRIMARY KEY , " +
            ExpenseCommon.TableExpense.COL_DATE + " VARCHAR NOT NULL , " +
            ExpenseCommon.TableExpense.COL_INFO + " VARCHAR , " +
            ExpenseCommon.TableExpense.COL_AMOUNT + " INTEGER )";

    public static ExpenseHelper getInstance(Context context){
        if (instance == null) {
            instance = new ExpenseHelper(context);
        }
        return instance;
    }


    public ExpenseHelper(Context context) {
        super(context, ExpenseCommon.Constant.DB_NAME, null, ExpenseCommon.Constant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
