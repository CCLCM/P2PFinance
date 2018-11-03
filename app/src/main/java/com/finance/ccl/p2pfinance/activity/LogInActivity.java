package com.finance.ccl.p2pfinance.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.finance.ccl.p2pfinance.MainActivity;
import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.bean.Login;
import com.finance.ccl.p2pfinance.common.AppManager;
import com.finance.ccl.p2pfinance.common.AppNetConfig;
import com.finance.ccl.p2pfinance.common.BaseActivity;
import com.finance.ccl.p2pfinance.utils.MD5Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends BaseActivity {
    protected AsyncHttpClient client = new AsyncHttpClient();
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.log_ed_mob)
    EditText logEdMob;
    @BindView(R.id.about_com)
    RelativeLayout aboutCom;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.log_ed_pad)
    EditText logEdPad;
    @BindView(R.id.log_log_btn)
    Button logLogBtn;

    @Override
    protected void initData() {
    }

    @OnClick(R.id.log_log_btn)
    public void clickLogin(View view) {
        login();
    }

    private void login() {
        String username = logEdMob.getText().toString();
        String password = logEdPad.getText().toString();


        if (!TextUtils.isEmpty(username) && ! TextUtils.isEmpty(password)) {
            RequestParams params = new RequestParams();
            params.put("username",username);
            params.put("password",MD5Utils.MD5(password));

            client.post(AppNetConfig.LOGIN,params,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {
                    JSONObject jsonObject = JSON.parseObject(content);
                    if (jsonObject.getBoolean("success")){
                        String data = jsonObject.getString("data");
                        Login login = JSON.parseObject(data, Login.class);
                        //保存到sp当中
                        saveLogin(login);
                        gotoActivity(MainActivity.class,null);
                        closeCurrent();

                        //关闭当前
                    }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                }
            });
        }
    }

    @Override
    protected void initTitle() {
        titleTv.setText("用户登录");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.title_left)
    public void back(View view) {
        closeCurrent();
    }

    public void closeCurrent(){
        AppManager.getInstance().removeCurrent();
    }


}
