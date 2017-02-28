package com.example.rocker.aad_expense;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by rocker on 2017/2/23.
 */

public class Expense implements Parcelable {
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


    // Parcelable 的建構式
    protected Expense(Parcel in) {
        id = in.readInt();
        date = in.readString();
        info = in.readString();
        amount = in.readInt();
    }

    // 當getInten.getParcelableExtra 時，可以自動解包
    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

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

    // 實作 parcelable時，必須實作的兩個方法，重點是writeToParcel 自動裝箱
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(date);
        parcel.writeString(info);
        parcel.writeInt(amount);
    }
}
