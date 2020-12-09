package com.example.resepmasakan.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.resepmasakan.R;
import com.example.resepmasakan.model.DataModel;

import static com.example.resepmasakan.activities.MainActivity.setWindowFlag;

public class DetailActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private WebView web;
    private ProgressBar mProgressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Toolbar
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        DataModel dataModel = (DataModel) bundle.getSerializable("dataModel");

        // setting judul bar
        setTitle(dataModel.getJudul());

        web = findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());

        web.loadUrl("file:///android_assets/" + dataModel.getKonten());

        WebSettings websettings = web.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setAllowContentAccess(true);
        websettings.setAppCacheEnabled(true);
        websettings.setDomStorageEnabled(true);
        websettings.setUseWideViewPort(true);
        //tampil data konten
        //webView = findViewById(R.id.webView);
        //WebSettings websettings = webView.getSettings();
        //websettings.setAllowFileAccess(true);
        /*webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String urlStart, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageFinished(WebView view, String urlPage) {
                mProgressBar.setVisibility(View.GONE);
                super.onPageFinished(view, urlPage);

            }
        });*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}