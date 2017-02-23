package com.example.rocker.aad_expense;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by rocker on 2017/2/23.
 */

public class ExpenseProvider extends ContentProvider {
    private ExpenseHelper helper;
    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int EXPENSES = 200;
    public static final int EXPENSES_WITH_ID = 201;

    static{
        sUriMatcher.addURI(
                ExpenseCommon.Constant.AUTHORITY,
                ExpenseCommon.TableExpense.TABLE_NAME,
                EXPENSES);
        sUriMatcher.addURI(
                ExpenseCommon.Constant.AUTHORITY,
                ExpenseCommon.TableExpense.TABLE_NAME + "/#",
                EXPENSES_WITH_ID);
    }



    @Override
    public boolean onCreate() {


        helper = new ExpenseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (sUriMatcher.match(uri)) {
            case EXPENSES:
                break;
            case EXPENSES_WITH_ID:
                selection = ((selection == null) ? "" : selection);
                selection = new StringBuilder(selection)
                        .append(ExpenseCommon.TableExpense.COL_ID)
                        .append("=")
                        .append(uri.getLastPathSegment())
                        .toString();
                break;

        }
        cursor = helper.getReadableDatabase().query(ExpenseCommon.TableExpense.TABLE_NAME,
                projection, selection, selectionArgs, sortOrder, null, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
