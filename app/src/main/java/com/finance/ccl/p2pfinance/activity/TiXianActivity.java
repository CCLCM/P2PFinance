package com.finance.ccl.p2pfinance.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.account_zhifubao)
    TextView accountZhifubao;
    @BindView(R.id.select_bank)
    RelativeLayout selectBank;
    @BindView(R.id.chongzhi_text)
    TextView chongzhiText;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.input_money)
    EditText inputMoney;
    @BindView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleTv.setText("提现");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);

    }

    @OnClick(R.id.title_left)
    public void back(View v){
        closeCurrent();
    }


    @OnClick(R.id.btn_tixian)
    public void toxian(View v){
        Toast.makeText(getApplication(),"成功提取"+inputMoney.getText().toString()+"元",Toast.LENGTH_LONG).show();
    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_tixian;
    }

}
