package com.example.rocker.aad_expense;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_date,et_info,et_amount;
    private ExpenseHelper helper;
    private Button btnADD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        helper = ExpenseHelper.getInstance(this);
        findViews();


    }

    private void findViews() {
        et_date = (EditText) findViewById(R.id.et_date);
        et_info = (EditText) findViewById(R.id.et_info);
        et_amount = (EditText) findViewById(R.id.et_amount);
        btnADD = (Button) findViewById(R.id.btn_addexp);
        btnADD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addexp:
                addExpense();
                break;

        }
    }

    private void addExpense() {
        String cdate = et_date.getText().toString();
        String info = et_info.getText().toString();
        Integer amount = Integer.valueOf(et_amount.getText().toString());

        ContentValues values = new ContentValues();
        values.put("cdate",cdate);
        values.put("info",info);
        values.put("amount",amount);

        helper.getWritableDatabase().insert(ExpenseCommon.TableExpense.TABLE_NAME, null, values);
        Toast.makeText(this, "add new expense", Toast.LENGTH_SHORT).show();

    }
}
