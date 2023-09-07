package com.example.guidebook.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.guidebook.R
import com.example.guidebook.domain.model.Data

class DetailFragment : Fragment() {
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webView)
        val arg = arguments?.getSerializable("data") as Data
        arg?.let {
            webView.loadUrl("https://guidebook.com" + it.url)
        }
    }
}