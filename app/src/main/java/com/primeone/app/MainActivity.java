package com.primeone.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.ValueCallback;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends Activity {
    private WebView web;
    private ValueCallback<Uri[]> fileCallback;
    private static final int PICK_FILE = 8102;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        web = new WebView(this);
        setContentView(web);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setSupportZoom(false);
        s.setBuiltInZoomControls(false);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> callback, FileChooserParams params) {
                if (fileCallback != null) fileCallback.onReceiveValue(null);
                fileCallback = callback;
                Intent intent = params.createIntent();
                try { startActivityForResult(intent, PICK_FILE); return true; }
                catch (Exception e) { fileCallback = null; return false; }
            }
        });
        if (savedInstanceState != null) web.restoreState(savedInstanceState);
        else web.loadUrl("file:///android_asset/index.html");
    }
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE && fileCallback != null) {
            Uri[] results = (resultCode == RESULT_OK && data != null && data.getData() != null) ? new Uri[]{data.getData()} : null;
            fileCallback.onReceiveValue(results); fileCallback = null;
        }
    }
    @Override protected void onSaveInstanceState(Bundle out) { web.saveState(out); super.onSaveInstanceState(out); }
    @Override public void onBackPressed() { if (web != null && web.canGoBack()) web.goBack(); else super.onBackPressed(); }
}
