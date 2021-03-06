package com.pathfinder.takeaway.widget;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import java.util.Map;

public interface H5Callback {
  void doPerform(Map<Object, Object> map);

  void onPageFinished(WebView webView, String str);

  void onPageStarted(WebView webView, String str, Bitmap bitmap);

  void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error);

  void setJockeyEvents();

  void openBrowser(String url);
}