package com.pathfinder.takeaway.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import com.pathfinder.takeaway.AppManager;
import com.pathfinder.takeaway.components.storage.UserStorage;
import com.pathfinder.takeaway.injector.PerActivity;
import com.pathfinder.takeaway.otto.AccountChangeEvent;
import com.pathfinder.takeaway.otto.ChangeThemeEvent;
import com.pathfinder.takeaway.otto.LoginSuccessEvent;
import com.pathfinder.takeaway.util.ToastUtil;
import com.pathfinder.takeaway.util.UpdateAgent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;

@PerActivity public class MainPresenter implements MainContract.Presenter {

  private UserStorage mUserStorage;
  private Bus mBus;
  private Observable<Integer> mNotificationObservable;
  private UpdateAgent mUpdateAgent;
  private Context mContext;

  private Subscription mSubscription;
  private MainContract.View mMainView;
  private int count = 0;

  @Inject public MainPresenter(UserStorage mUserStorage, Bus mBus,
      Observable<Integer> mNotificationObservable, UpdateAgent mUpdateAgent, Context mContext) {
    this.mUserStorage = mUserStorage;
    this.mBus = mBus;
    this.mNotificationObservable = mNotificationObservable;
    this.mUpdateAgent = mUpdateAgent;
    this.mContext = mContext;
  }

  @Override public void attachView(@NonNull MainContract.View view) {
    mMainView = view;
    mBus.register(this);
    initUserInfo();
    initNotification();
    mUpdateAgent.checkUpdate();
  }

  private void initUserInfo() {

  }

  private void initNotification() {

  }

  private void toLogin() {
    mMainView.showLoginUi();
    ToastUtil.showToast("请先登录");
  }

  @Override public void exist() {
    if (isCanExit()) {
      AppManager.getAppManager().AppExit(mContext);
    }
  }

  private long mExitTime = 0;
  private boolean isCanExit() {
    if (System.currentTimeMillis() - mExitTime > 2000) {
      ToastUtil.showToast("再按一次退出程序");
      mExitTime = System.currentTimeMillis();
      return false;
    }
    return true;
  }

  @Override public boolean isLogin() {
    //return mUserStorage.isLogin();
    return false;
  }

  @Override public void detachView() {
    mBus.unregister(this);
    count = 0;
    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }
    mMainView = null;
  }

  @Subscribe public void onChangeThemeEvent(ChangeThemeEvent event) {
    mMainView.reload();
  }

  @Subscribe public void onLoginSuccessEvent(LoginSuccessEvent event) {
    initUserInfo();
  }

  @Subscribe public void onAccountChangeEvent(AccountChangeEvent event) {
    initUserInfo();
  }
}
