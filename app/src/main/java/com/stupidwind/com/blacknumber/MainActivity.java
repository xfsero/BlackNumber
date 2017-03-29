package com.stupidwind.com.blacknumber;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.stupidwind.com.blacknumber.adapter.BlackNumberAdapter;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private ListView lv_main;
    private Button btn_add;
    private BlackNumberDao dao;
    private BlackNumberAdapter adapter;
    private List<BlackNumber> datas;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化配置
        initSettings();
        // 设置监听器
        setListener();
    }

    // 设置监听器
    private void setListener() {
        btn_add.setOnClickListener(this);
        lv_main.setOnCreateContextMenuListener(this);
    }

    // 添加按钮的点击事件
    @Override
    public void onClick(View v) {
        final EditText editText = new EditText(MainActivity.this);
        editText.setHint("请输入黑名单号");
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("添加黑名单")
                .setView(editText)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //保存到数据表中
                        String number = editText.getText().toString();
                        BlackNumber blackNumber = new BlackNumber(-1, number);
                        dao.add(blackNumber);
                        //保存到List中
                        datas.add(0, blackNumber);
                        //通知列表更新
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }


    // 设置ContextMenu的条目
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "更新");
        menu.add(0, 2, 0, "删除");

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        position = info.position;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //得到对应的BlackNumber对象
        BlackNumber blackNumber = datas.get(position);

        switch (item.getItemId()) {
            case 1: //更新
                showUpdateDialog(blackNumber);
                break;
            case 2: //删除
                dao.deleteById(blackNumber.getId());
                datas.remove(position);
                adapter.notifyDataSetChanged();
                break;

            default:
        }

        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(final BlackNumber blackNumber) {
        final EditText editText = new EditText(this);
        editText.setHint(blackNumber.getNumber());

        new AlertDialog.Builder(this)
                .setTitle("更新黑名单")
                .setView(editText)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newNum = editText.getText().toString();
                        blackNumber.setNumber(newNum);
                        dao.update(blackNumber);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    // 初始化设置
    private void initSettings() {
        lv_main = (ListView) findViewById(R.id.lv_main);
        btn_add = (Button) findViewById(R.id.btn_add);
        dao = new BlackNumberDao(this);
        datas = dao.getAll();
        adapter = new BlackNumberAdapter(this, datas);
        lv_main.setAdapter(adapter);

    }
}