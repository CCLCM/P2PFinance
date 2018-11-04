package com.finance.ccl.p2pfinance.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.common.BaseActivity;
import com.finance.ccl.p2pfinance.common.apply.PayKeys;
import com.finance.ccl.p2pfinance.common.apply.PayResult;
import com.finance.ccl.p2pfinance.common.apply.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChongzhiActivity extends BaseActivity {
    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_right)
    ImageView titleRight;
    @BindView(R.id.chongzhi_text)
    TextView chongzhiText;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.chongzhi_et)
    EditText chongzhiEt;
    @BindView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @BindView(R.id.yue_tv)
    TextView yueTv;
    @BindView(R.id.chongzhi_btn)
    Button chongzhiBtn;
    private TextWatcher watch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String s1 = chongzhiEt.getText().toString();
            if (!TextUtils.isEmpty(s1)) {
                chongzhiBtn.setBackgroundResource(R.drawable.btn_01);
            } else {
                chongzhiBtn.setBackgroundResource(R.drawable.btn_023);
            }
        }
    };


    @Override
    protected void initData() {

        chongzhiEt.addTextChangedListener(watch);

    }

    @Override
    protected void initTitle() {
        titleTv.setText("用户登录");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);

    }

    @OnClick(R.id.title_left)
    public void back(View view) {
        closeCurrent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chongzhi;
    }

    @OnClick(R.id.chongzhi_btn)
    public void  clickzf(View view){
        String orderInfo = getOrderInfo("测试的商品", "测试商品的价格", "0.01");
        String sign = SignUtils.sign(orderInfo,PayKeys.PRIVATE);

        try {
            sign = URLEncoder.encode(sign,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        final String payInfo = orderInfo + "&sing=\""+sign +"\"&" +getSignType();

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(ChongzhiActivity.this);
                String result = alipay.pay(payInfo);
                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread paythread = new Thread(payRunnable);
        paythread.start();
    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((String) msg.obj);

            // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
            String resultInfo = payResult.getResult();

            String resultStatus = payResult.getResultStatus();

            // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
            if (TextUtils.equals(resultStatus, "9000")) {
                Toast.makeText(ChongzhiActivity.this, "支付成功",
                        Toast.LENGTH_SHORT).show();
            } else {
                // 判断resultStatus 为非“9000”则代表可能支付失败
                // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                if (TextUtils.equals(resultStatus, "8000")) {
                    Toast.makeText(ChongzhiActivity.this, "支付结果确认中",
                            Toast.LENGTH_SHORT).show();

                } else {
                    // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                    Toast.makeText(ChongzhiActivity.this, "支付失败",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * get the sign type we use. 获取签名方式
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }



    /**
     * create the order info. 创建订单信息
     */
    public String getOrderInfo(String subject, String body, String price) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PayKeys.DEFAULT_PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + PayKeys.DEFAULT_SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }


}
