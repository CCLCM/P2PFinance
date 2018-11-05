package com.finance.ccl.p2pfinance.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.activity.BarChartActivity;
import com.finance.ccl.p2pfinance.activity.ChongzhiActivity;
import com.finance.ccl.p2pfinance.activity.LineChartActivity;
import com.finance.ccl.p2pfinance.activity.LogInActivity;
import com.finance.ccl.p2pfinance.activity.PieChartActivity;
import com.finance.ccl.p2pfinance.activity.TiXianActivity;
import com.finance.ccl.p2pfinance.activity.ToggleActivity;
import com.finance.ccl.p2pfinance.activity.UserInfoActivity;
import com.finance.ccl.p2pfinance.bean.Login;
import com.finance.ccl.p2pfinance.common.AppNetConfig;
import com.finance.ccl.p2pfinance.common.BaseActivity;
import com.finance.ccl.p2pfinance.common.BaseFragment;
import com.finance.ccl.p2pfinance.utils.BitMapUtils;
import com.finance.ccl.p2pfinance.utils.UIutils;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ccl on 18-4-9.
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.icon_time)
    RelativeLayout iconTime;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.chongzhi)
    ImageView chongzhi;
    @BindView(R.id.tixian)
    ImageView tixian;
    @BindView(R.id.ll_touzi)
    TextView llTouzi;
    @BindView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @BindView(R.id.ll_zichang)
    TextView llZichang;
    @BindView(R.id.ll_zhanquan)
    TextView llZhanquan;
    Unbinder unbinder;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    public String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    public void initData(String resultState) {
        isLogin();
    }

    private void isLogin() {
        SharedPreferences sp = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String uf_acc = sp.getString("UF_ACC","");
        if (TextUtils.isEmpty(uf_acc)) {
            showLoginDialog();
        } else {
            doUser();
        }
    }

    private void doUser() {
        Login login = ((BaseActivity) getActivity()).getLogin();
        textView11.setText(login.UF_ACC);
        Picasso.get().load(login.UF_AVATAR_URL).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap zoom = BitMapUtils.zoom(source, UIutils.dp2px(62), UIutils.dp2px(62));
                Bitmap bitmap = BitMapUtils.circleBitMap(zoom);
                source.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "";
            }
        }).into(imageView1);
    }

    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("登录");
        builder.setMessage("请先登录");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"登录",Toast.LENGTH_LONG).show();
                ((BaseActivity)getActivity()).gotoActivity(LogInActivity.class,null);
            }
        });
        builder.setCancelable(false);
        builder.create().show();


    }

    @Override
    public void initTitle() {
        titleTv.setText("我的资产");
        titleRight.setVisibility(View.VISIBLE);
        titleLeft.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.chongzhi)
    public void chongzhiClick(View v){
        ((BaseActivity)getActivity()).gotoActivity(ChongzhiActivity.class,null);

    }
    @OnClick(R.id.tixian)
    public void tixianClick(View v){
        ((BaseActivity)getActivity()).gotoActivity(TiXianActivity.class,null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.title_right)
    public void clickUserSetting(View view){
        ((BaseActivity)getActivity()).gotoActivity(UserInfoActivity.class,null);
    }

    @OnClick(R.id.ll_touzi)
    public void clickline(View view){
        ((BaseActivity)getActivity()).gotoActivity(LineChartActivity.class,null);
    }
    @OnClick(R.id.ll_touzi_zhiguan)
    public void  clickbar(View view){
        ((BaseActivity)getActivity()).gotoActivity(BarChartActivity.class,null);
    }
    @OnClick(R.id.ll_zichang)
    public void  clickpie(View view){
        ((BaseActivity)getActivity()).gotoActivity(PieChartActivity.class,null);
    }

    @OnClick(R.id.ll_zhanquan)
    public void  zhanquan(View view){
        ((BaseActivity)getActivity()).gotoActivity(ToggleActivity.class,null);
    }

}
