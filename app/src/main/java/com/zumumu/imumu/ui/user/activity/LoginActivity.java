package com.zumumu.imumu.ui.user.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.home.HomeActivity;
import com.zumumu.imumu.ui.user.contract.UserContract;
import com.zumumu.imumu.ui.user.model.LoginModel;
import com.zumumu.imumu.ui.user.model.UserBean;
import com.zumumu.imumu.ui.user.presenter.LoginPresenter;
import com.zumumu.imumu.ui.view.ClearEditText;
import com.zumumu.imumu.utils.CheckPhoneUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC_p on 2016/12/26.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements UserContract.LoginSuperView {


    @Bind(R.id.et_login_user)
    ClearEditText etLoginUser;
    @Bind(R.id.et_login_pwd)
    ClearEditText etLoginPwd;
    @Bind(R.id.cb_login)
    CheckBox cbLogin;
    @Bind(R.id.tv_login_signin)
    TextView tvLoginSignin;
    @Bind(R.id.bt_login_submit)
    Button btLoginSubmit;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.iv_login_wechat)
    ImageView ivLoginWechat;
    @Bind(R.id.tv_login_wechat)
    TextView tvLoginWechat;
    @Bind(R.id.tv_login_forget)
    TextView tvLoginForget;

    private boolean isRemember = false;
    private String pwd;
    private String phone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        SetTranslanteBar();
        if(sp.getBoolean("rememberPwd",false)){
            etLoginUser.setText(sp.getString("username",""));
            etLoginPwd.setText(sp.getString("password",""));
            cbLogin.setChecked(true);
            isRemember = !isRemember;
        }

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_login_forget, R.id.tv_login_signin, R.id.bt_login_submit, R.id.iv_login_wechat,R.id.cb_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_forget:
                startActivity(ForgetActivity.class);
                break;
            case R.id.tv_login_signin:
                startActivity(SigninActivity.class);
                break;
            case R.id.bt_login_submit:
                UserBean userBean = new UserBean();
                phone = etLoginUser.getText().toString().trim();
                pwd = etLoginPwd.getText().toString().trim();
                //判断手机号是否为空
                if (TextUtils.isEmpty(phone)) {
                    ToastUitl.show("手机号输入不能为空!", Toast.LENGTH_SHORT);
                    // Toast.makeText(mContext, "手机号或者是密码输入不能为空!", Toast.LENGTH_SHORT).show();
                    etLoginUser.shakeAnimation(5);
                    etLoginUser.requestFocus();
                    return;
                }
                //判断密码是否为空
                if (TextUtils.isEmpty(pwd)) {
                    ToastUitl.show("密码输入不能为空!", Toast.LENGTH_SHORT);
                    etLoginPwd.shakeAnimation(5);
                    etLoginPwd.requestFocus();
                    return;
                }
                //判断用户输入的手机号是否正确规范
                if (!CheckPhoneUtil.check(phone)) {
                    ToastUitl.show("请检查您输入的手机号格式是否正确!", Toast.LENGTH_SHORT);
                    //   Toast.makeText(mContext, "请检查您输入的手机号格式是否正确!", Toast.LENGTH_SHORT).show();
                    etLoginUser.shakeAnimation(5);
                    etLoginUser.requestFocus();
                    return;
                }
                userBean.setPhone(phone);
                userBean.setPassWord(pwd);
                mPresenter.loadUserChannelsRequst(userBean);

                break;
            case R.id.iv_login_wechat:
                startActivity(HomeActivity.class);
                finish();
                break;

            case R.id.cb_login:        //记住密码
                isRemember = !isRemember;
                break;

        }
    }

    @Override
    public void returnUserChannels(String userId) {
        ToastUitl.show("登录成功", Toast.LENGTH_SHORT);
        SharedPreferences.Editor editor = sp.edit();

        if(isRemember){
            editor.putString("username",phone);
            editor.putString("password",pwd);
            editor.putBoolean("rememberPwd",true);
        }else{
            editor.putBoolean("rememberPwd",false);
        }
        editor.putString("userid",userId);

        editor.commit();
        startActivity(HomeActivity.class);
        finish();
    }

    @Override
    public void returnUserChannelsError(String msg) {
        ToastUitl.show(msg, Toast.LENGTH_SHORT);
    }

}
