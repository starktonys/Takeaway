package com.pathfinder.takeaway.ui.splash;

import android.content.Context;
import android.support.annotation.NonNull;
import com.alibaba.fastjson.JSON;
import com.pathfinder.takeaway.Constants;
import com.pathfinder.takeaway.bean.UpdateInfo;
import com.pathfinder.takeaway.components.okhttp.OkHttpHelper;
import com.pathfinder.takeaway.injector.PerActivity;
import com.pathfinder.takeaway.util.ChannelUtil;
import com.pathfinder.takeaway.util.SettingPrefUtil;
import com.umeng.analytics.MobclickAgent;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@PerActivity public class SplashPresenter implements SplashContract.Presenter {

  private SplashContract.View mSplashView;
  private Subscription mSubscription;

  private Context mContext;
  private OkHttpHelper mOkHttpHelper;

  @Inject public SplashPresenter(Context mContext, OkHttpHelper mOkHttpHelper) {
    this.mContext = mContext;
    this.mOkHttpHelper = mOkHttpHelper;
  }

  @Override public void initUmeng() {
    MobclickAgent.UMAnalyticsConfig config =
        new MobclickAgent.UMAnalyticsConfig(mContext, "581c379745297d04e0001ffc",
            ChannelUtil.getChannel(mContext));
    MobclickAgent.startWithConfigure(config);
  }

  @Override public void initHuPuSign() {
    mSubscription = Observable.create(new Observable.OnSubscribe<UpdateInfo>() {
      @Override public void call(Subscriber<? super UpdateInfo> subscriber) {
        try {
          String result = mOkHttpHelper.getStringFromServer(Constants.UPDATE_URL);
          subscriber.onNext(JSON.parseObject(result, UpdateInfo.class));
        } catch (Exception e) {
          subscriber.onError(e);
        }
        subscriber.onCompleted();
      }
    }).timeout(5, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<UpdateInfo>() {
          @Override public void call(UpdateInfo updateInfo) {
            if (updateInfo != null) {
              if (updateInfo.extra != null) {
                //SettingPrefUtil.setNeedExam(mContext, updateInfo.extra.needExam == 1);
              }
              SettingPrefUtil.setHuPuSign(mContext, updateInfo.hupuSign);
            }
            mSplashView.showMainUi();
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            throwable.printStackTrace();
            mSplashView.showMainUi();
          }
        });
  }

  @Override public void attachView(@NonNull SplashContract.View view) {
    mSplashView = view;
  }

  @Override public void detachView() {
    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }
    mSplashView = null;
  }
}
