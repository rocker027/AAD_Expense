package com.example.rocker.aad_expense;

import android.database.Cursor;

import java.io.Serializable;

/**
 * Created by rocker on 2017/2/23.
 */

public class Expense implements Serializable {
    private Integer id, amount;
    private String date, info;

    public Expense() {
    }

    public Expense(Integer id, Integer amount, String date, String info) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.info = info;
    }

    // 可以傳入Cursor的建構子
    public Expense(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(ExpenseCommon.TableExpense.COL_ID));
        date = cursor.getString(cursor.getColumnIndex(ExpenseCommon.TableExpense.COL_DATE));
        info = cursor.getString(cursor.getColumnIndex(ExpenseCommon.TableExpense.COL_INFO));
        amount = cursor.getInt(cursor.getColumnIndex(ExpenseCommon.TableExpense.COL_AMOUNT));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
