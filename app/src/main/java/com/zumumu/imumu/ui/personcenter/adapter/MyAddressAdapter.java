package com.zumumu.imumu.ui.personcenter.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.personcenter.activity.MyAddressActivity;
import com.zumumu.imumu.ui.personcenter.model.MyAddressBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PC_p on 2017/1/11.
 * 收货地址adapter
 */

public class MyAddressAdapter extends RecyclerView.Adapter {
    private Context context;
    private MyAddressBean mBean;
    private boolean curDisplay = false; //当前是删除还是箭头
    private Activity mActivity;


    class MyclickListener implements View.OnClickListener{
        private int position;
        public MyclickListener(int position){
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            MyAddressActivity myAddressActivity =  (MyAddressActivity)mActivity;
            myAddressActivity.loadModifyScreen(position);
        }
    }



    public MyAddressAdapter(Context context, MyAddressBean bean, Activity activity) {
        this.context = context;
        mBean = bean;
        mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_myaddress, parent, false);
        ViewHolder holder1 = new ViewHolder(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        if(curDisplay == true){
            holder1.ivMyaddressArrow.setVisibility(View.GONE);
            holder1.btMyaddressDelete.setVisibility(View.VISIBLE);
        }else{
            holder1.ivMyaddressArrow.setVisibility(View.VISIBLE);
            holder1.btMyaddressDelete.setVisibility(View.GONE);
        }
        holder1.tvMyaddressPhone.setText("电话:"+mBean.getData().get(position).getAddress_mobile());
        holder1.tvMyaddressReceiver.setText("收货人姓名:"+mBean.getData().get(position).getAddress_name());
        holder1.myaddressDetail.setText("详细地址:"+mBean.getData().get(position).getAddress_province()+mBean.getData().get(position).getAddress_city()+mBean.getData().get(position).getAddress_area()+mBean.getData().get(position).getAddress_detailed());

        if(0 != mBean.getData().get(position).getAddress_default()){
            holder1.tvMyaddressDefault.setVisibility(View.GONE);
        }else{
            holder1.tvMyaddressDefault.setVisibility(View.VISIBLE);
        }

        holder1.ivMyaddressArrow.setOnClickListener(new MyclickListener(position));

        holder1.btMyaddressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAddressActivity myAddressActivity = (MyAddressActivity) mActivity;
                myAddressActivity.loadDeleteLogic(position,mBean.getData().get(position).getAddress_id());
            }
        });

    }
    @Override
    public int getItemCount() {
        return mBean.getData().size();
    }

    /**
     * 刷新界面状态,是删除按钮还是箭头arrow
     */
    public void refreshDisplay(){
        curDisplay = !curDisplay;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_myaddress_receiver)
        TextView tvMyaddressReceiver;
        @Bind(R.id.tv_myaddress_phone)
        TextView tvMyaddressPhone;
        @Bind(R.id.myaddress_detail)
        TextView myaddressDetail;
        @Bind(R.id.tv_myaddress_default)
        TextView tvMyaddressDefault;
        @Bind(R.id.bt_myaddress_delete)
        Button btMyaddressDelete;
        @Bind(R.id.iv_myaddress_arrow)
        ImageView ivMyaddressArrow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
}

}

