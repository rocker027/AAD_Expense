package com.example.rocker.aad_expense;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by rocker on 2017/2/23.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHodler> implements View.OnClickListener {
    Cursor cursor = null;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    // 建立RecyclerView Item ClickListener
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    // 當被RecyclerView Item Click時要做什麼事情，指定將被點選的取得該Item的View 及 Expense物件
    @Override
    public void onClick(View v) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener.onItemClick(v,(Expense)v.getTag());
        }
    }

    // 當被點時所呼叫的onItemClick
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,Expense expense);
    }

    public ExpenseAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ExpenseViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        //
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        ExpenseViewHodler viewHodler = new ExpenseViewHodler(view);
        view.setOnClickListener(this);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(ExpenseViewHodler viewHodler, int position) {
        cursor.moveToPosition(position);
        Expense expense = new Expense(cursor);
        viewHodler.row_date.setText(expense.getDate());
        viewHodler.row_info.setText(expense.getInfo());
        viewHodler.row_amount.setText(String.valueOf(expense.getAmount()));
        viewHodler.itemView.setTag(expense);
        Log.d(TAG, "" + expense.getAmount());
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class ExpenseViewHodler extends RecyclerView.ViewHolder {

        public TextView row_date, row_info, row_amount;

        public ExpenseViewHodler(View itemView) {
            super(itemView);
            row_date = (TextView) itemView.findViewById(R.id.tv_date);
            row_info = (TextView) itemView.findViewById(R.id.tv_info);
            row_amount = (TextView) itemView.findViewById(R.id.tv_amount);

        }
    }
}
