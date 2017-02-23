package com.example.rocker.aad_expense;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Rocker on 2017/2/21.
 */

public class ExpenseCommon {
    public static final class Constant {
        public static final String DB_NAME = "expense.db";
        public static final int DB_VERSION = 1;
        public static final String AUTHORITY = "com.example.rocker.aad_expense";
        public static final Uri CONTENT_URI = new Uri.Builder()
                .scheme("content")
                .authority(AUTHORITY)
                .appendPath(TableExpense.TABLE_NAME)
                .build();
    }

    public static final class TableExpense implements BaseColumns {
        public static final String TABLE_NAME = "exp";
        public static final String COL_ID = "_id";
        public static final String COL_DATE = "cdate";
        public static final String COL_INFO = "info";
        public static final String COL_AMOUNT = "amount";

    }
}
