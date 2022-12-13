package com.app.deeplink

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    // url for loading in custom chrome tab
    var url = "https://www.stackoverflow.com"
    //var url = "https://www.google.com"

    // First time, above url is loading in webview,
    // then, loaded url need to added intent url format inside button.
    // after loaded initial webpage, while clicking inside deep link button,
    // our installed app open automatically.

    //Ex: we want to open our whatsup app
    // intent://page=whatsupDeepDrop|linkstatus=success#intent|package=com.whatsup.com

    var btn_webview: Button? = null
    var btn_customchrome: Button? = null
    var btn_chrometabexternal: Button? = null

    //----------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialization()
        btn_webview = findViewById(R.id.btn_webview);
        btn_customchrome = findViewById(R.id.btn_customchrome);
        btn_chrometabexternal = findViewById(R.id.btn_chrometabexternal);

        setonclicklistener()
    }

    //----------------------------------------------------------------------------------------------
    private fun initialization() {

        btn_webview = findViewById(R.id.btn_webview);
        btn_customchrome = findViewById(R.id.btn_customchrome);
        btn_chrometabexternal = findViewById(R.id.btn_chrometabexternal);

    }

    //----------------------------------------------------------------------------------------------
    private class MyBrowser : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {

            if (url != null) {
                if (url.startsWith("intent://")) {
                    Toast.makeText(view.context, "deep link opened app success:", Toast.LENGTH_SHORT).show()
                    val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    view.context.startActivity(intent)
                    return true
                } else {
                    Toast.makeText(view.context, "normal url loaded for first time opening.", Toast.LENGTH_SHORT).show()
                    view.loadUrl(url)
                }
            }
            return true
        }
    }

    private fun setonclicklistener() {

        btn_webview?.setOnClickListener(View.OnClickListener {

            val myWebView: WebView = findViewById(R.id.webView)
            myWebView.setWebViewClient(MyBrowser())
            myWebView.settings.javaScriptEnabled = true
            myWebView.settings.loadsImagesAutomatically = true
            myWebView.scrollBarSize = View.SCROLLBARS_INSIDE_OVERLAY
            myWebView.loadUrl(url);

        })

        btn_customchrome?.setOnClickListener(View.OnClickListener {

            // initializing object for custom chrome tabs.
            val customIntent = CustomTabsIntent.Builder()
            customIntent.setToolbarColor(ContextCompat.getColor(this@MainActivity,
                R.color.purple_200))
            openCustomTab(this@MainActivity, customIntent.build(), Uri.parse(url))
        })

        btn_chrometabexternal?.setOnClickListener(View.OnClickListener {

            //val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            //startActivity(browserIntent)

            val urlString = url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setPackage("com.android.chrome")
            startActivity(intent)

        })
    }

    //----------------------------------------------------------------------------------------------
    open fun openCustomTab(activity: Activity, customTabsIntent: CustomTabsIntent, uri: Uri?) {
        val packageName = "com.android.chrome"
        if (packageName != null) {
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(activity, uri!!)
        } else {
            // redirecting our user to users device default browser.
            activity.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
    //----------------------------------------------------------------------------------------------
}