package com.zumumu.imumu.ui.user.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.user.contract.UserContract;
import com.zumumu.imumu.ui.user.model.SigninModel;
import com.zumumu.imumu.ui.user.model.UserBean;
import com.zumumu.imumu.ui.user.presenter.SigninPresenter;
import com.zumumu.imumu.ui.view.ClearEditText;
import com.zumumu.imumu.ui.view.TimeButton;
import com.zumumu.imumu.utils.CheckPhoneUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by PC_p on 2016/12/26.
 * 用户注册界面
 */

public class SigninActivity extends BaseActivity<SigninPresenter, SigninModel> implements UserContract.SigninSuperView {


    @Bind(R.id.tv_toolbar)
    TextView tvToolbar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_signin_username)
    ClearEditText etSigninUsername;
    @Bind(R.id.et_signin_phone)
    ClearEditText etSigninPhone;
    @Bind(R.id.et_signin_pwd)
    ClearEditText etSigninPwd;
    @Bind(R.id.et_signin_pwd_again)
    ClearEditText etSigninPwdAgain;
    @Bind(R.id.bt_signin_verificationCode)
    TimeButton btSigninVerificationCode;
    @Bind(R.id.et_signin_verificationCode)
    ClearEditText etSigninVerificationCode;
    @Bind(R.id.cb_signin)
    CheckBox cbSignin;
    @Bind(R.id.tv_signin_userInfo)
    TextView tvSigninUserInfo;
    @Bind(R.id.tv_signin_protocol)
    TextView tvSigninProtocol;
    @Bind(R.id.bt_signin_submit)
    Button btSigninSubmit;
    @Bind(R.id.tit_signin_username)
    TextInputLayout titSigninUsername;
    @Bind(R.id.tit_signin_phone)
    TextInputLayout titSigninPhone;
    @Bind(R.id.tit_signin_pwd)
    TextInputLayout titSigninPwd;
    @Bind(R.id.tit_signin_pwd_again)
    TextInputLayout titSigninPwdAgain;
    @Bind(R.id.tit_signin_verficationCode)
    TextInputLayout titSigninVerficationCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_signin;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        etSigninUsername.addTextChangedListener(new TextChangeLisenter(TextChangeLisenter.TEXT_USERNAME));
        etSigninPhone.addTextChangedListener(new TextChangeLisenter(TextChangeLisenter.TEXT_PHONE));
        etSigninPwd.addTextChangedListener(new TextChangeLisenter(TextChangeLisenter.TEXT_PWD));
        etSigninPwdAgain.addTextChangedListener(new TextChangeLisenter(TextChangeLisenter.TEXT_PWDAGAIN));
        etSigninVerificationCode.addTextChangedListener(new TextChangeLisenter(TextChangeLisenter.TEXT_VERFIYCODE));
    }

    @Override
    public void returnSigninChannels(String SigninMsg) {
        ToastUitl.show(SigninMsg, Toast.LENGTH_LONG);
        finish();
    }


    @Override
    public void returnSigninChannelsError(String msg) {
        ToastUitl.show(msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void returnVerifyCodeChannels(String msg) {
        ToastUitl.show(msg, Toast.LENGTH_LONG);
    }

    @Override
    public void returnVerifyCodeChannelsError(String erroMsg) {
        ToastUitl.show(erroMsg, Toast.LENGTH_SHORT);
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

    @OnClick({R.id.bt_signin_verificationCode, R.id.bt_signin_submit, R.id.tv_signin_protocol})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_signin_verificationCode:
                String phoneNum = etSigninPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNum)) {
                    titSigninPhone.setError("输入的手机号不能为空!");
                    etSigninPhone.shakeAnimation(5);
                    etSigninPhone.requestFocus();
                    return;
                }
                if (!CheckPhoneUtil.check(phoneNum)) {
                    titSigninPhone.setError("请检查输入的手机号格式是否正确!");
                    etSigninPhone.shakeAnimation(5);
                    etSigninPhone.requestFocus();
                    return;
                }
                mPresenter.loadVerifyCodeChannelsRequest(phoneNum);

                break;


            case R.id.bt_signin_submit:
                String username = etSigninUsername.getText().toString().trim();
                String phone = etSigninPhone.getText().toString().trim();
                String pwd = etSigninPwd.getText().toString().trim();
                String pwd_again = etSigninPwdAgain.getText().toString().trim();
                String verifyCode = etSigninVerificationCode.getText().toString().trim();
                //判断用户输入是否为空
                if (TextUtils.isEmpty(username)) {
                    titSigninUsername.setError("昵称输入不能为空!");
                    etSigninUsername.shakeAnimation(5);
                    etSigninUsername.requestFocus();
                    //Toast.makeText(mContext, "输入信息不能为空!请您检查", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断用户输入的电话是否为空
                if (TextUtils.isEmpty(phone)) {
                    titSigninPhone.setError("电话输入不能为空!");
                    etSigninPhone.shakeAnimation(5);
                    etSigninPhone.requestFocus();
                    return;
                }
                //判断用户输入的密码是否为空
                if (TextUtils.isEmpty(pwd)) {
                    titSigninPwd.setError("电话输入不能为空!");
                    etSigninPwd.shakeAnimation(5);
                    etSigninPwd.requestFocus();
                    return;
                }
                //判断用户输入的确认密码是否为空
                if (TextUtils.isEmpty(pwd_again)) {
                    titSigninPwdAgain.setError("电话输入不能为空!");
                    etSigninPwdAgain.shakeAnimation(5);
                    etSigninPwdAgain.requestFocus();
                    return;
                }
                //判断用户输入验证码是否为空
                if (TextUtils.isEmpty(verifyCode)) {
                    titSigninVerficationCode.setError("验证码输入不能为空!");
                    etSigninVerificationCode.shakeAnimation(5);
                    etSigninVerificationCode.requestFocus();
                    return;
                }


                //判断用户输入的手机号是否正确规范
                if (!CheckPhoneUtil.check(phone)) {
                    titSigninPhone.setError("请检查您输入的手机号格式是否正确!");
                    etSigninPhone.shakeAnimation(5);
                    etSigninPhone.requestFocus();
                    return;
                }
                if (!pwd.equals(pwd_again)) {
                    titSigninPwdAgain.setError("两次输入的密码不一致!");
                    etSigninPwdAgain.shakeAnimation(5);
                    etSigninPwdAgain.requestFocus();
                    return;
                }

                if (!cbSignin.isChecked()) {
                    Toast.makeText(mContext, "确认遵守协议后才能通过注册!", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserBean userBean = new UserBean();
                userBean.setPhone(phone);
                userBean.setPassWord(pwd);
                userBean.setUserName(username);
                userBean.setVerifycode(verifyCode);
                mPresenter.loadSigninChannelsRequst(userBean);
                break;

            case R.id.tv_signin_protocol:
                final PopupWindow popupWindow = new PopupWindow(mContext);
                View popView = View.inflate(mContext, R.layout.signin_popwindow_protocol, null);
                popupWindow.setContentView(popView);
                popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                popView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                popupWindow.setOutsideTouchable(true);
                int[] location = new int[2];
                btSigninVerificationCode.getLocationOnScreen(location);
                popupWindow.showAtLocation(btSigninVerificationCode, Gravity.CENTER, location[0],
                        0);
                break;
        }
    }

    class TextChangeLisenter implements TextWatcher {
        final static int TEXT_USERNAME = 0;
        final static int TEXT_PHONE = 1;
        final static int TEXT_PWD = 2;
        final static int TEXT_PWDAGAIN = 3;
        final static int TEXT_VERFIYCODE = 4;
        int type;

        public TextChangeLisenter(int type){
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (type == TEXT_USERNAME) {
                if (!TextUtils.isEmpty(s)) {
                    titSigninUsername.setErrorEnabled(false);
                }
            }else if (type == TEXT_PHONE) {
                if (!TextUtils.isEmpty(s)) {
                    titSigninPhone.setErrorEnabled(false);
                }
            }else if (type == TEXT_PWD){
                if (!TextUtils.isEmpty(s)) {
                    titSigninPwd.setErrorEnabled(false);
                }
            }else if (type == TEXT_PWDAGAIN){
                if (!TextUtils.isEmpty(s)){
                    titSigninPwdAgain.setErrorEnabled(false);
                }
            }else if (type == TEXT_VERFIYCODE){
                if (!TextUtils.isEmpty(s)){
                    titSigninVerficationCode.setErrorEnabled(false);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
