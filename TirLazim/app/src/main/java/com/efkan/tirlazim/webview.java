package com.efkan.tirlazim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {
   WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView=findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // WebView içinde linklerin açılmasını engelle, kendi içinde aç
        webView.setWebViewClient(new WebViewClient());

        // Web sayfasının yüklenme durumunu takip et
        webView.setWebChromeClient(new WebChromeClient());

        // WebView'e açılacak olan web sayfasının URL'sini belirle
        webView.loadUrl("https://www.ziraatbank.com.tr/tr");
    }
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}