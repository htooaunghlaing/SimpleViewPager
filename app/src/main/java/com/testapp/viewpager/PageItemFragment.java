package com.testapp.viewpager;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import com.gzsll.jsbridge.WVJBWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageItemFragment extends Fragment {

    private static final String CHAR_TITLE = "CHAR_TITLE";
    private static final String COLOR_VALUE = "COLOR_VALUE";

    View rootView;
    Unbinder unbinder;


    String textContent;
    @BindView(R.id.webview)
    WVJBWebView webview;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public PageItemFragment() {
        // Required empty public constructor
    }

    public static PageItemFragment newInstance(String param1,String param2) {
        PageItemFragment fragment = new PageItemFragment();
        Bundle args = new Bundle();
        args.putString(CHAR_TITLE, param1);
        args.putString(COLOR_VALUE, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_page_item, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        WebView.setWebContentsDebuggingEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.loadUrl("file:///android_asset/index.html");

        if (getArguments() != null) {
            //txtTitle.setText(getArguments().getString(CHAR_TITLE));
            webview.callHandler("testJavascriptHandler", getArguments().getString(CHAR_TITLE)+","+getArguments().getString(COLOR_VALUE), new WVJBWebView.WVJBResponseCallback() {

                @Override
                public void callback(Object data) {
                    //Toast.makeText(getContext(), "Java call testJavascriptHandler got response! :" + data, Toast.LENGTH_LONG).show();
                }
            });
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
