package com.gheinrich.cocc_mobile;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by gage heinrich on 6/13/14.
 * <p/>
 * this class is the activity that shows the student log in page in a webview!! not a webpage!!!!!
 * uses many different methods such as websettings, loadurl(), webviewclient, setJavascriptEnabled.
 */
public class facebook_cocc extends Activity {

    static final String LOG_TAG = "LOG_TAG";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook);


        // setting up a webview, an extension of the view class it shows webpages in your program not on another browser.

        WebView myWebview = (WebView) findViewById(R.id.webview);

        // instantiate the websettings class attachted to our webview
        WebSettings webSettings = myWebview.getSettings();

        // allowing javascript to be enabled on the page that will be requested by the webview class when calling .loadurl()
        webSettings.setJavaScriptEnabled(true);

        // this forces the phone to actually run a webview before searching for other browsers installed on the phone
        myWebview.setWebViewClient(new WebViewClient());
        myWebview.loadUrl("https://www.facebook.com/CentralOregonCommunityCollege");


        Log.d(LOG_TAG, "HEllO ANDROID");


    }
}
