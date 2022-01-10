package com.mygotoservices.mygotoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class OtherPaymentActivity extends AppCompatActivity {
    String url;
    private ProgressBar progressBar;
    WebView webView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_payment);
        url = (String) getIntent().getExtras().get("url");
        // initializing toolbar
       // Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }


}
