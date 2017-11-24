package com.zumumu.imumu.ui.pay.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.nzf.mvpframe.base.BaseActivity;
import com.nzf.mvpframe.utils.ToastUitl;
import com.zumumu.imumu.R;
import com.zumumu.imumu.ui.pay.contract.PayContract;
import com.zumumu.imumu.ui.pay.model.PayModel;
import com.zumumu.imumu.ui.pay.presenter.PayPresenter;
import com.zumumu.imumu.ui.view.ClearEditText;
import com.zumumu.imumu.ui.view.MyDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PC_p on 2017/1/20.
 */

public class PayActivity extends BaseActivity<PayPresenter, PayModel> implements PayContract.PaySuperView {

    @Bind(R.id.tv_toolbar)
    TextView tvToolbar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_pay_balance)
    ImageView ivPayBalance;
    @Bind(R.id.tv_pay_balance)
    TextView tvPayBalance;
    @Bind(R.id.tv_pay_balancedesc)
    TextView tvPayBalancedesc;
    @Bind(R.id.cb_pay_balance)
    CheckBox cbPayBalance;
    @Bind(R.id.iv_pay_alilog)
    ImageView ivPayAlilog;
    @Bind(R.id.tv_pay_zfb)
    TextView tvPayZfb;
    @Bind(R.id.tv_pay_zfbdesc)
    TextView tvPayZfbdesc;
    @Bind(R.id.cb_pay_zfb)
    CheckBox cbPayZfb;
    @Bind(R.id.btn_pay_commit)
    Button btnPayCommit;
    private String orderid;

    private MyDialog dialog;
    private View contentView;
    public static final String APPID = "2016072800113127";
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXfzYSLf3cTkKH\n" +
            "7fD9tvOgnPNZBH5l95FgHU4v7hZqwHbtMK59kv9gPYEaUfkI5ZeMuqJIB8s5RQow\n" +
            "8NalT4jKaoHcWSZnKulqLWS7Jbs/on4B0M5lWv1m7NQn5L1G0+L7VDlirZ7nQCX6\n" +
            "+TXi83pd6Lr2N3MBwv5I/oGtwZrzFa7A+Bm/Is8fVD0AV3SYjI5zW6TzzIMS0gk/\n" +
            "m3jqhKyPdA/YACTLFoujI+0Zp+gYYzlJJ9oY2/+Qh+KXrc8F+iqtLdneku5QuaE8\n" +
            "XwNxMIbFUFGApOH4BWLIvkNCi5GY3YTWX+Qq2TaxDFF6R6EV13Btb32snh6Qk/I7\n" +
            "l7bvevWRAgMBAAECggEAH523vhGFEi3u6ok4quo8a791dZVff44r86AKCiyo1vx1\n" +
            "lUh8kc5Up1QOllyXGUk42HC2n0upGLmUx+PrF7LCfGO/2Cluv0cQUU1J4VidfEk/\n" +
            "/McD29kiWxF/sRemfJqJmyPXmgVbzSzxADB0E3n2hpSkN/AYezyjbr5Wd9mYqbQB\n" +
            "f1U4xvHXoQ2nTyaIPdBZYsZvl5xgQ3rKZaB7zNnFHay7f0mecPgTtHYwgd/o7qjA\n" +
            "mPJUO9CLIWkb9FUCSJadSpUlr/otEmmdA+O0z9cVwMNILPdllOIPnU62LlBbzF+b\n" +
            "ROqAlfAlKuKgaI6KBdxbZT98kAAIX31RyqkCIPocQQKBgQDIbfUmMCPzMfmSiuND\n" +
            "bW0NZf/wAJSirG7LPoziH+oyX5YMNntkizgCcah4Yn0a2H9VsRKSEVHje9JfcBsQ\n" +
            "6jcNLakFM/EIGvFYGldVoDrpoL2zj5qogDVKfef0datwUt7AwSzjwhR+LB+kpMVT\n" +
            "9KXHH1pOjzrwcaU6TIvqJyM1GQKBgQDBgCBHnbIpm2J7pl2mFy2FEvoRtqFQnUkF\n" +
            "bo6SWV7JonpIqTDKAlD2YaT9oholkSrvopXGJUkt9P8743q8NPBuxBxJwt/MG1ch\n" +
            "SuVIHREi4fTPdqwtXwmayMNDTqcm0Z7dJ5C/mdA/smSvxO0cjNTfpkNrgdokVypw\n" +
            "4d9n5S6bOQKBgHGpEYWndSub4oj85CY/rsETX9GNsryRhn8RqLkvGhOY6zFf0BfT\n" +
            "b17Lb5tdVs7biVJiCUL2OXcEp+dhXqf3+mIpy5jcvMo45TZuMaqoI+CLPCjQvgbi\n" +
            "NdZZkimqdR9Mj2/F3JwIWrC2evdWTWTv7vb9y6uTQ/xIpkdqU0nlCe05AoGACwQT\n" +
            "UmBs6yd4dNekzzbUlZXd/LIr/t3lcVS48yX5WntQgOB48ZcuKsimTvYyjN711cyo\n" +
            "wC4RO3eOLEeoDhzpbzTk82IPwZvw2ulboj5dYmIAwOrdpHubnV8+lo9cvr4CUE7X\n" +
            "U8Ea9PVf/A22/gAagl0By5YJ6DW6KdECGfUXMSkCgYEAgt0uC2UXW/D1++0T21Ok\n" +
            "CdLblsxUynR7nqFlg5MSJcK9Nw26Q0iDNHTuNOB/ZEU6NL5g/IuWS70RRSPpWAIt\n" +
            "hWSlnIohrdv1bVvV9w9Ao99L6tTMFpgGZsLADNuLqqO7KMiNhjnlDdtZ0j37uPWf\n" +
            "nqidW6wzN/ccR+RYobwDxpk=";


    private final int SDK_PLAY_FLAG = 1;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            return false;
        }
    });
    private String orderInfo;
    private ClearEditText cet_balance_pass;


    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvToolbar.setText("支付订单");
        orderid = getIntent().getExtras().getString("orderid", "");
        mPresenter.returnPayChannelsRequst(orderid);

        cbPayBalance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbPayZfb.setChecked(false);
                }
            }
        });

        cbPayZfb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbPayBalance.setChecked(false);
                }
            }
        });


        btnPayCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbPayBalance.isChecked()){
                    initDialog();
                }else{

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
                        PayTask payTask = new PayTask(PayActivity.this);
                        Map<String, String> result = payTask.payV2(orderInfo, true);
                        Message msg = new Message();
                        msg.what = SDK_PLAY_FLAG;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }).start();
                }
            }
        });

    }

    private void initDialog() {

            dialog = new MyDialog(mContext,R.style.GoodDialog);
            //设置退出速度
            dialog.outDuration(100);
            dialog.inDuration(100);
            //设置铺满
            dialog.heightParam(ViewGroup.LayoutParams.WRAP_CONTENT);
            //解析视图
            contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_input_password, null);
            //设置视图
            dialog.setContentView(contentView);

            cet_balance_pass = (ClearEditText) contentView.findViewById(R.id.cet_balance_pass);
            Button btn_balance_pay = (Button) contentView.findViewById(R.id.btn_balance_pay);

            btn_balance_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(cet_balance_pass.getText().toString().trim())){
                        ToastUitl.show("输入不能为空", Toast.LENGTH_SHORT);
                        return;
                    }

                    Map<String,String> params = new HashMap<String, String>();
                    params.put("order_id",orderid);
                    params.put("pay_name","余额支付");
                    params.put("payPwd", cet_balance_pass.getText().toString().trim());
                    mPresenter.returnBalancePayChannelsRequst(params);
                }
            });
            dialog.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void returnPayChannels(String data) {
        orderInfo = data;
        System.out.println("数据信息:" + data);
    }

    @Override
    public void returnPayChannelsError(String msg) {

    }

    @Override
    public void returnBalancePayChannels(String msg) {
        ToastUitl.show(msg,Toast.LENGTH_SHORT);
        dialog.dismiss();
        finish();
    }

    @Override
    public void returnBalancePayChannelsError(String msg) {

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
}
