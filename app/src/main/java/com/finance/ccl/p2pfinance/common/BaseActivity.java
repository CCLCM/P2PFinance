package com.finance.ccl.p2pfinance.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.finance.ccl.p2pfinance.bean.Login;
import com.finance.ccl.p2pfinance.utils.LogUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        LogUtils.logshow("chencl_   onCreate  ",getClass());
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initTitle();
        initData();
    }

    protected abstract void initData();

    protected abstract void initTitle();


    public abstract int getLayoutId();

    public  void saveLogin(Login login) {
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("UF_ACC",login.UF_ACC);
        editor.putString("UF_AVATAR_URL",login.UF_AVATAR_URL);
        editor.putString("UF_IS_CERT",login.UF_IS_CERT);
        editor.putString("UF_PHONE",login.UF_PHONE);
        editor.commit();
    }

    public Login getLogin(){
        Login login  = new Login();
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        login.UF_ACC = sp.getString("UF_ACC","");
        login.UF_AVATAR_URL = sp.getString("UF_AVATAR_URL","");
        login.UF_IS_CERT = sp.getString("UF_IS_CERT","");
        login.UF_PHONE = sp.getString("UF_PHONE","");
        return login;
    }

    public void gotoActivity(Class clazz,Bundle bundle){
        Intent it = new Intent(this,clazz);
        if (bundle != null) {
            it.putExtra("param",bundle);
        }
        startActivity(it);
    }
}
