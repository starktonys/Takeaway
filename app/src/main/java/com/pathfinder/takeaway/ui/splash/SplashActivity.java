package com.pathfinder.takeaway.ui.splash;

import android.text.TextUtils;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.pathfinder.takeaway.R;
import com.pathfinder.takeaway.ui.BaseActivity;
import com.pathfinder.takeaway.ui.main.MainActivity;
import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashContract.View {

  public static final String ACTION_NOTIFICATION_MESSAGE =
      "com.pathfinder.takeaway.ACTION_NOTIFICATION_MESSAGE";

  @BindView(R.id.splash) FrameLayout splash;
  @Inject SplashPresenter mPresenter;

  @Override public int initContentView() {
    return R.layout.activity_splash;
  }

  @Override public void initInjector() {
    DaggerSplashComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .splashModule(new SplashModule(this))
        .build()
        .inject(this);
  }

  @Override public void initUiAndListener() {
    mPresenter.attachView(this);
    mPresenter.initUmeng();
    mPresenter.initHuPuSign();
  }

  @Override protected boolean isApplyStatusBarTranslucency() {
    return false;
  }

  @Override protected boolean isApplyStatusBarColor() {
    return false;
  }

  @Override public void showMainUi() {
    MainActivity.startActivity(SplashActivity.this);
    String action = getIntent().getAction();
    if (TextUtils.equals(action, ACTION_NOTIFICATION_MESSAGE)) {
      //MessageActivity.startActivity(SplashActivity.this);
    }
    finish();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPresenter.detachView();
  }
}
