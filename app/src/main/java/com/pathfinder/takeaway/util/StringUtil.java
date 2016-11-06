package com.pathfinder.takeaway.util;

import android.content.Context;

public class StringUtil {

  public static void copy(Context mContext, String stripped) {
    android.content.ClipboardManager clipboard =
        (android.content.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
    android.content.ClipData clip = android.content.ClipData.newPlainText("content", stripped);
    clipboard.setPrimaryClip(clip);
    ToastUtil.showToast("复制成功");
  }
}
