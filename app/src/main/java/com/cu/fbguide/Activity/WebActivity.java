package com.cu.fbguide.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.cu.fbguide.R;

public class WebActivity extends AppCompatActivity {

    WebView webView;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_web);
        String name = null;
        try {
            pd = new ProgressDialog(this);
            name=getIntent().getStringExtra("Name");
            pd.setTitle(name+ "\nLoading...");
            pd.show();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage()+name, Toast.LENGTH_SHORT).show();
        }
        webView=findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                pd.dismiss();
                webView.loadData(String.valueOf(request),"text/plain","utf-8");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });
        webView.loadUrl(getIntent().getStringExtra("Url"));

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}