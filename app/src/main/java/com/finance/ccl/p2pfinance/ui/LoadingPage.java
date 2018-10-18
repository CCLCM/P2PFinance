package com.finance.ccl.p2pfinance.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.finance.ccl.p2pfinance.R;
import com.finance.ccl.p2pfinance.utils.UIutils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public abstract class LoadingPage extends FrameLayout {
    AsyncHttpClient client = new AsyncHttpClient();
    private static final int PAGE_LOADING_STATE =1 ;
    private static final int PAGE_ERROR_STATE =2 ;
    private static final int PAGE_EMPTY_STATE =3 ;
    private static final int PAGE_SUCCESS_STATE =4 ;
    private int PAGE_CURRENT_STATE = PAGE_LOADING_STATE;

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;
    private  LayoutParams lp;
    private ResultState resultState = null;


    public LoadingPage(@NonNull Context context) {
        this(context,null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        init();
    }

    private void init() {
        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = UIutils.getXmlView(R.layout.page_loading);
            addView(loadingView,lp);
        }
        if (errorView ==null) {
            errorView = UIutils.getXmlView(R.layout.page_error);
            addView(errorView,lp);
        }
        if (emptyView == null) {
            emptyView = UIutils.getXmlView(R.layout.page_empty);
            addView(emptyView,lp);
        }
        showSafePage();
    }

    private void  showSafePage(){
        UIutils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    private void showPage() {
        loadingView.setVisibility(PAGE_CURRENT_STATE == PAGE_LOADING_STATE ? VISIBLE: GONE);
        errorView.setVisibility(PAGE_CURRENT_STATE == PAGE_ERROR_STATE ? VISIBLE: GONE);
        emptyView.setVisibility(PAGE_CURRENT_STATE == PAGE_EMPTY_STATE ? VISIBLE: GONE);
        if (successView == null) {
            successView = UIutils.getXmlView(layoutId());
            addView(successView,lp);
        }
        successView.setVisibility(PAGE_CURRENT_STATE == PAGE_SUCCESS_STATE ? VISIBLE: GONE);
    }

    public abstract int layoutId();

    public void  show() {

          if (PAGE_CURRENT_STATE != PAGE_LOADING_STATE) {
              PAGE_CURRENT_STATE = PAGE_LOADING_STATE;
          }
          //处理不需要发送请求来决定的界面
        String url = url();
          if (TextUtils.isEmpty(url)) {
              resultState = ResultState.SECUSS;
              resultState.setContent("");
              loadPage();
          } else {
              client.get(url(),params(),new AsyncHttpResponseHandler(){
                  @Override
                  public void onSuccess(String content) {
                      if (TextUtils.isEmpty(content)) {
                          resultState = ResultState.EMPTY;
                          resultState.setContent("");
                      } else {
                          resultState = ResultState.SECUSS;
                          resultState.setContent(content);
                      }
                      loadPage();
                  }

                  @Override
                  public void onFailure(Throwable error, String content) {
                      resultState = ResultState.ERROR;
                      resultState.setContent("");
                      loadPage();
                  }
              });
          }
    }

    private void loadPage() {

        switch (resultState) {
            case ERROR:
                //当前状态设置为2 显示错误状态
                PAGE_CURRENT_STATE = PAGE_ERROR_STATE;
                break;
            case EMPTY:
                //当前状态设置为3 显示空界面
                PAGE_CURRENT_STATE = PAGE_EMPTY_STATE;
                break;
            case SECUSS:
                //当前界面显示为4 显示界面成功
                PAGE_CURRENT_STATE = PAGE_SUCCESS_STATE;
                break;
        }
        showSafePage();
        if (PAGE_CURRENT_STATE == PAGE_SUCCESS_STATE) {
            OnSuccess(resultState,successView);
        }
    }

    protected abstract void OnSuccess(ResultState resultState,View view);

    protected abstract RequestParams params();

    protected abstract String url();

    public enum ResultState{
        ERROR(2),EMPTY(3),SECUSS(4);
        private final int state;
        private String content;

        public String getContent() {
            return content;
        }

        public int getState() {
            return state;
        }

        public void setContent(String content) {
            this.content = content;
        }

        ResultState(int state){
            this.state = state;
        }
    }
}
