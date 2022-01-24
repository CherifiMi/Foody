package com.example.foud.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.foud.R
import com.example.foud.models.Result

class WebFrahment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_web_frahment, container, false)

        var webView: WebView = view.findViewById(R.id.web_thing)
        webView.settings.javaScriptEnabled

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        //data bundle
        val args = arguments
        val data: Result? = args?.getParcelable("recipeBundle")

        var myUrl: String = data!!.sourceUrl

        webView.loadUrl(myUrl)

        return view
    }
}