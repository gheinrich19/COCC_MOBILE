package com.gheinrich.cocc_mobile;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by gheinrich on 7/28/2014.
 */
public class emoloyee_webview extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee);


        // setting up a webview, an extension of the view class it shows webpages in your program not on another browser.

        WebView myWebview = (WebView) findViewById(R.id.webview);

        // instantiate the websettings class attachted to our webview
        WebSettings webSettings = myWebview.getSettings();

        // allowing javascript to be enabled on the page that will be requested by the webview class when calling .loadurl()
        webSettings.setJavaScriptEnabled(true);

        // this forces the phone to actually run a webview before searching for other browsers installed on the phone
        myWebview.setWebViewClient(new WebViewClient());
        myWebview.loadUrl("http://www.cocc.edu/employee-login/");
    }
}
