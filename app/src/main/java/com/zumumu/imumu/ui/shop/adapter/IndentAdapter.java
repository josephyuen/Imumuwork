package com.zumumu.imumu.ui.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zumumu.imumu.R;

/**
 * Created by PC_p on 2017/1/11.
 */

public class IndentAdapter extends BaseAdapter{
    private int num;
    private Context context;
    private String type;
    public IndentAdapter(Context context,int num, String type){
        this.num = num;
        this.context = context;
        this.type = type;
    }

    @Override
    public int getCount() {
        return num;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(context, R.layout.item_myindent, null);
        }
        Button bt1 = (Button) convertView.findViewById(R.id.bt1_myindent);
        Button bt2 = (Button) convertView.findViewById(R.id.bt2_myindent);
        TextView tv_state = (TextView) convertView.findViewById(R.id.tv_indent_state);

        if("待付款".equals(type)){
            bt1.setText("删除订单");
            bt2.setText("立即支付");
            tv_state.setText("状态:待付款");
        }else if("待发货".equals(type)){
            bt1.setText("提醒发货");
            bt2.setText("申请退货");
            tv_state.setText("状态:待发货");
        }else if("待收货".equals(type)){
            bt1.setText("确认收货");
            bt2.setText("申请退货");
            tv_state.setText("状态:待收货");
        }else if("待评价".equals(type)){
            bt1.setText("删除订单");
            bt2.setText("立即评价");
            tv_state.setText("状态:待评价");
        }
        return convertView;
    }
}
