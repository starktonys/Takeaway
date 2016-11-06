package com.pathfinder.takeaway.components.retrofit;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.pathfinder.takeaway.components.storage.UserStorage;
import com.pathfinder.takeaway.util.SecurityUtil;
import com.pathfinder.takeaway.util.SettingPrefUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class RequestHelper {

  private Context mContext;
  private UserStorage mUserStorage;

  public RequestHelper(Context mContext, UserStorage mUserStorage) {
    this.mContext = mContext;
    this.mUserStorage = mUserStorage;
  }

  public String getAndroidId() {
    return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
  }

  public String getDeviceId() {
    String deviceId;
    TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    if (tm.getDeviceId() == null) {
      deviceId = getAndroidId();
    } else {
      deviceId = tm.getDeviceId();
    }
    return deviceId;
  }

  public String getRequestSign(Map<String, String> map) {
    ArrayList<Map.Entry<String, String>> list =
        new ArrayList<Map.Entry<String, String>>(map.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
      @Override public int compare(Map.Entry<String, String> lhs, Map.Entry<String, String> rhs) {
        return lhs.getKey().compareTo(rhs.getKey());
      }
    });
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < list.size(); i = i + 1) {
      if (builder.length() > 0) {
        builder.append("&");
      }
      Map.Entry<String, String> map1 = list.get(i);
      builder.append(map1.getKey()).append("=").append(map1.getValue());
    }
    builder.append(SettingPrefUtil.getHuPuSign(mContext));
    return SecurityUtil.getMD5(builder.toString());
  }
}
