package com.zumumu.imumu.ui.personcenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.personcenter.contract.AddressContract;
import com.zumumu.imumu.ui.personcenter.model.AddAddressModel;
import com.zumumu.imumu.ui.personcenter.model.UserAddressBean;
import com.zumumu.imumu.ui.personcenter.presenter.AddAddressPresenter;
import com.zumumu.imumu.utils.CheckPhoneUtil;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by PC_p on 2017/1/11.
 * 新增地址界面
 */

public class AddAddressActivity extends BaseActivity<AddAddressPresenter,AddAddressModel> implements AddressContract.AddAddressSuperView{


    @Bind(R.id.tv_toolbar)
    TextView tvToolbar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_addaddress_nicknam)
    EditText etAddaddressNicknam;
    @Bind(R.id.til_addaddress_nickname)
    TextInputLayout tilAddaddressNickname;
    @Bind(R.id.et_addaddress_phone)
    EditText etAddaddressPhone;
    @Bind(R.id.til_addaddress_phone)
    TextInputLayout tilAddaddressPhone;
    @Bind(R.id.et_addaddress_location)
    EditText etAddaddressLocation;
    @Bind(R.id.til_addaddress_location)
    TextInputLayout tilAddaddressLocation;
    @Bind(R.id.cb_addaddress)
    CheckBox cbAddaddress;
    @Bind(R.id.bt_addaddress)
    Button btAddaddress;
    @Bind(R.id.tv_addaddress_province)
    TextView tvAddaddressProvince;
    @Bind(R.id.tv_addaddress_citys)
    TextView tvAddaddressCitys;
    @Bind(R.id.tv_addaddress_ares)
    TextView tvAddaddressAres;
    @Bind(R.id.bt_addaddress_addloction)
    Button btAddaddressAddloction;

    private String json;
    private String isDefault;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        tvToolbar.setText("新增收货地址");
        initEditText();
        try {
            json = ConvertUtils.toString(getAssets().open("city.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //初始化EditText
    private void initEditText() {
        EditText mEditText1 = tilAddaddressNickname.getEditText();
        mEditText1.addTextChangedListener(new MyTextWatch(1, mEditText1));

        EditText mEditText = tilAddaddressPhone.getEditText();
        mEditText.addTextChangedListener(new MyTextWatch(2, mEditText));

        EditText mEditText2 = tilAddaddressLocation.getEditText();
        mEditText2.addTextChangedListener(new MyTextWatch(3, mEditText2));


    }

    @OnClick({R.id.bt_addaddress_addloction, R.id.bt_addaddress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_addaddress_addloction:
                showPlacePicker();
                break;
            case R.id.bt_addaddress:
                mPresenter.returnAddAddressChannelsRequst(addAddress());
                break;
        }
    }

    //初始化滚轮地址选择器
    public void showPlacePicker(){
        ArrayList<Province> data = new ArrayList<Province>();
        data.addAll(JSON.parseArray(json, Province.class));
        AddressPicker picker = new AddressPicker(this, data);
        picker.setSelectedItem("湖南", "长沙", "雨花区");
        picker.setColumnWeight(2 / 8.0, 3 / 8.0, 3 / 8.0);//省级、地级和县级的比例为2:3:3
        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(Province province, City city, County county) {
                tvAddaddressProvince.setText("" + province);
                tvAddaddressCitys.setText("" + city);
                tvAddaddressAres.setText("" + county);
            }
        });
        picker.show();
    }

    //获取新增收货地址信息
    private UserAddressBean addAddress(){
        String province = tvAddaddressProvince.getText().toString().trim();
        String citys = tvAddaddressCitys.getText().toString().trim();
        String ares = tvAddaddressAres.getText().toString().trim();
        if (TextUtils.isEmpty(province) || TextUtils.isEmpty(citys) || TextUtils.isEmpty(ares)) {
            ToastUitl.show("请选择收货地址",Toast.LENGTH_SHORT);
            return null;
        }else {
            if (cbAddaddress.isChecked()) {
                isDefault = "0";
            }else {
                isDefault = "1";
            }
            String userid = sp.getString("userid", " ");
            String name = etAddaddressNicknam.getText().toString().trim();
            String phone = etAddaddressPhone.getText().toString().trim();
            String location = etAddaddressLocation.getText().toString().trim();
            UserAddressBean userAddressBean = new UserAddressBean();
            userAddressBean.setUser_id(userid);
            userAddressBean.setAddress_name(name);
            userAddressBean.setAddress_mobile(phone);
            userAddressBean.setAddress_province(province);
            userAddressBean.setAddress_city(citys);
            userAddressBean.setAddress_area(ares);
            userAddressBean.setAddress_detailed(location);
            userAddressBean.setAddress_isDefault(isDefault);
            return userAddressBean;
        }
    }

    @Override
    public void returnAddAddressChannels(UserAddressBean userAddressBean) {
        ToastUitl.show("添加成功", Toast.LENGTH_SHORT);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userAddress",userAddressBean);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(0,intent);
        finish();
    }

    @Override
    public void returnAddAddressChannelsError(String msg) {
        ToastUitl.show(msg,Toast.LENGTH_SHORT);
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    //输入框监听,错误关注到每个输入框
    class MyTextWatch implements TextWatcher {
        private int type;
        private EditText editText;

        public MyTextWatch(int type, EditText editText) {
            this.type = type;
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (type == 1) {
                if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                    tilAddaddressNickname.setError("收货人不能为空");
                } else {
                    tilAddaddressNickname.setErrorEnabled(false);
                }
            } else if (type == 2) {
                if (!CheckPhoneUtil.check(editText.getText().toString().trim())) {
                    tilAddaddressPhone.setError("电话号码格式不正确");
                } else {
                    tilAddaddressPhone.setErrorEnabled(false);
                }
            } else if (type == 3) {
                if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                    tilAddaddressLocation.setError("详细地址输入不能为空");
                } else {
                    tilAddaddressLocation.setErrorEnabled(false);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
