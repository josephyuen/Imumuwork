package com.zumumu.imumu.ui.user.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.user.contract.UserContract;
import com.zumumu.imumu.ui.user.model.ForgetModel;
import com.zumumu.imumu.ui.user.presenter.ForgetPresenter;
import com.zumumu.imumu.ui.view.ClearEditText;
import com.zumumu.imumu.ui.view.TimeButton;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/27 0027.
 */

public class ForgetActivity extends BaseActivity<ForgetPresenter, ForgetModel> implements UserContract.ForgetSuperView {
    public static final String PHONE_ERROR_MIS = "1";
    public static final String VERIFYCODE_ERROR = "2";
    public static final String EMPTY_PHONE = "3";
    public static final String EMPTY_VERIFYCODE = "4";
    public static final String EMPTY_PWD = "5";
    public static final String PWD_VERIFYERROR = "6";
    public static final String PHONE_ERROR = "7";

    @Bind(R.id.tv_toolbar)
    TextView tvToolbar;
    @Bind(R.id.et_forget_phone)
    ClearEditText etForgetPhone;
    @Bind(R.id.bt_forget_verificationCode)
    TimeButton btForgetVerificationCode;
    @Bind(R.id.et_forget_verificationCode)
    ClearEditText etForgetVerificationCode;
    @Bind(R.id.et_forget_pwd)
    ClearEditText etForgetPwd;
    @Bind(R.id.et_forget_pwd_again)
    ClearEditText etForgetPwdAgain;
    @Bind(R.id.bt_forget_submit)
    Button btForgetSubmit;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tit_forget_phone)
    TextInputLayout titForgetPhone;
    @Bind(R.id.tit_forget_verificationCode)
    TextInputLayout titForgetVerificationCode;
    @Bind(R.id.tit_forget_pwd)
    TextInputLayout titForgetPwd;
    @Bind(R.id.tit_forget_pwd_again)
    TextInputLayout titForgetPwdAgain;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        SetStatusBarColor();
        tvToolbar.setText("找回密码");
        etForgetPhone.addTextChangedListener(new TextChangeLisenter(TextChangeLisenter.TEXT_PHONE));
        etForgetVerificationCode.addTextChangedListener(new TextChangeLisenter(TextChangeLisenter.TEXT_VERFIYCODE));
        etForgetPwd.addTextChangedListener(new TextChangeLisenter(TextChangeLisenter.TEXT_PWD));
        etForgetPwd.addTextChangedListener(new TextChangeLisenter(TextChangeLisenter.TEXT_PWDAGAIN));
    }

    @Override
    public void returnForgetChannels(String msg) {
        ToastUitl.show(msg, Toast.LENGTH_SHORT);
        finish();
    }

    @Override
    public void returnForgetVerifyCodeChannels(String msg) {
        ToastUitl.show(msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void returnForgetChannelsError(String msg) {
        System.out.println(msg);
        if (EMPTY_PHONE.equals(msg)) {
            titForgetPhone.setError("电话号码为空，请输入电话号码");
            etForgetPhone.shakeAnimation(5);
            etForgetPhone.requestFocus();
        } else if (EMPTY_VERIFYCODE.equals(msg)) {
            titForgetVerificationCode.setError("验证码为空，请输入验证码");
            etForgetVerificationCode.shakeAnimation(5);
            etForgetVerificationCode.requestFocus();
        } else if (EMPTY_PWD.equals(msg)) {
            titForgetPwd.setError("密码不能为空，请输入密码");
            etForgetPwd.shakeAnimation(5);
            etForgetPwd.requestFocus();
        } else if (PWD_VERIFYERROR.equals(msg)) {
            titForgetPwdAgain.setError("两次输入密码不一致，请确认后重新输入");
            etForgetPwdAgain.shakeAnimation(5);
            etForgetPwdAgain.setText("");
            etForgetPwdAgain.requestFocus();
        } else if (PHONE_ERROR.equals(msg)) {
            titForgetPhone.setError("电话号码格式错误，请重新输入电话号码");
            etForgetPhone.shakeAnimation(5);
            etForgetPhone.requestFocus();
        }else if (PHONE_ERROR_MIS.equals(msg)){
            titForgetPhone.setError("修改失败，您输入的电话号码有误");
            etForgetPhone.shakeAnimation(5);
            etForgetPhone.requestFocus();
        }else if (VERIFYCODE_ERROR.equals(msg)){
            titForgetVerificationCode.setError("验证码输入错误");
            etForgetVerificationCode.shakeAnimation(5);
            etForgetVerificationCode.requestFocus();
        }else {
            ToastUitl.show(msg, Toast.LENGTH_SHORT);
        }
    }

    @OnClick({R.id.bt_forget_verificationCode, R.id.bt_forget_submit})
    public void onClick(View view) {
        String phone, verify, pwd, pwdAgin;
        switch (view.getId()) {
            case R.id.bt_forget_verificationCode:
                phone = etForgetPhone.getText().toString().trim();
                mPresenter.loadForgetVerifyChannelsRequst(phone);
                break;
            case R.id.bt_forget_submit:
                phone = etForgetPhone.getText().toString().trim();
                verify = etForgetVerificationCode.getText().toString().trim();
                pwd = etForgetPwd.getText().toString().trim();
                pwdAgin = etForgetPwdAgain.getText().toString().trim();
                mPresenter.loadForgetChannelsRequst(phone, verify, pwd, pwdAgin);
                break;
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

    class TextChangeLisenter implements TextWatcher {
        final static int TEXT_PHONE = 0;
        final static int TEXT_VERFIYCODE = 1;
        final static int TEXT_PWD = 2;
        final static int TEXT_PWDAGAIN = 3;
        int type;

        public TextChangeLisenter(int type){
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (type == 0) {
                if (!TextUtils.isEmpty(s)) {
                    titForgetPhone.setErrorEnabled(false);
                }
            }else if (type == 1) {
                if (!TextUtils.isEmpty(s)) {
                    titForgetVerificationCode.setErrorEnabled(false);
                }
            }else if (type == 2){
                if (!TextUtils.isEmpty(s)) {
                    titForgetPwd.setErrorEnabled(false);
                }
            }else if (type == 3){
                if (!TextUtils.isEmpty(s)){
                    titForgetPwdAgain.setErrorEnabled(false);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
