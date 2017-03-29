package com.stupidwind.com.blacknumber.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stupidwind.com.blacknumber.BlackNumber;

import java.util.List;

/**
 * Created by 蠢风 on 2017/3/29.
 */

public class BlackNumberAdapter extends BaseAdapter {

    private List<BlackNumber> datas;

    private Context context;

    public BlackNumberAdapter(Context context, List<BlackNumber> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(context, android.R.layout.simple_expandable_list_item_1, null);
        }
        BlackNumber blackNumber = datas.get(position);
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(blackNumber.getNumber());
        return convertView;
    }
}
