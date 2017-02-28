package com.example.rocker.aad_expense;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements ExpenseAdapter.OnRecyclerViewItemClickListener {

    private ExpenseHelper helper;
    private ListView listView;
    private RecyclerView recycler;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // use ListView
        listView = (ListView) findViewById(R.id.listview);
        // use RecycleView
        recycler = (RecyclerView) findViewById(R.id.recycleviewer);
        // 确保尺寸是通过用户输入从而确保RecyclerView的尺寸是一个常数。RecyclerView 的Item宽或者高不会变。每一个Item添加或者删除都不会变。
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        helper = ExpenseHelper.getInstance(this);
        onLoadToQuery();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        onLoadToQuery();
    }

    private void onLoadToQuery() {
        Cursor cursor = getContentResolver().query(ExpenseCommon.Constant.CONTENT_URI, null, null, null, null);
        ExpenseAdapter adapter = new ExpenseAdapter(cursor);
        // 初始化RecyclerViewItemClickListener 傳送 RecyclerViewItemClickListener 到 ExpenseAdapter
        adapter.setOnRecyclerViewItemClickListener(this);
        recycler.setAdapter(adapter);


        // 1.直接使用SQliteOpenHelper
//        Cursor cursor = helper.getReadableDatabase().query(ExpenseCommon.TableExpense.TABLE_NAME, null, null, null, null, null, null);
        // 2.使用Provider查詢全部
//        Cursor cursor = getContentResolver().query(ExpenseCommon.Constant.CONTENT_URI,
//                null, null, null, null);
        // 3.使用Provider查詢單筆
//        Uri uri = ContentUris.withAppendedId(
//                ExpenseCommon.Constant.CONTENT_URI, 1);
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);
//        Log.d(TAG, "" + cursor.getCount());

        // 使用listview
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, cursor, new String[]{"info", "amount"}, new int[]{android.R.id.text1, android.R.id.text2},0);
//        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, Expense expense) {
        Log.d(TAG, "Click id" + expense.getId());
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Exp", expense);
        startActivity(intent);
    }
}
