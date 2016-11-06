package com.pathfinder.takeaway.components.storage;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.pathfinder.takeaway.db.User;
import com.pathfinder.takeaway.util.SettingPrefUtil;

public class UserStorage {

  private Context mContext;

  public UserStorage(Context mContext) {
    this.mContext = mContext;
  }

  private String cookie;
  private String token;

  private User user;

  public User getUser() {
    return user;
  }

  public void login(User user) {

  }

  public void logout() {
  }

  private void removeCookie() {
    CookieSyncManager.createInstance(mContext);
    CookieManager cookieManager = CookieManager.getInstance();
    cookieManager.removeAllCookie();
    CookieSyncManager.getInstance().sync();
  }

  public boolean isLogin() {
    return user != null && SettingPrefUtil.getLoginUid(mContext).equals(user.getUid());
  }

  public String getToken() {
    if (!isLogin()) {
      return token;
    }
    return user.getToken();
  }

  public String getUid() {
    if (!isLogin()) {
      return "";
    }
    return user.getUid();
  }

  public String getCookie() {
    if (isLogin()) {
      return user.getCookie();
    }
    return cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
