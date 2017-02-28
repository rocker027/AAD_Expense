package com.example.rocker.aad_expense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView detail_tvDate;
    private TextView detail_tvInfo;
    private TextView detail_tvAmount;
    private Expense expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViews();
        expense = getIntent().getParcelableExtra("Exp");
        detail_tvDate.setText(expense.getDate());
        detail_tvInfo.setText(expense.getInfo());
        detail_tvAmount.setText(String.valueOf(expense.getAmount()));

    }

    private void findViews() {
        detail_tvDate = (TextView) findViewById(R.id.detail_date);
        detail_tvInfo = (TextView) findViewById(R.id.detail_info);
        detail_tvAmount = (TextView) findViewById(R.id.detail_amount);
    }
}
