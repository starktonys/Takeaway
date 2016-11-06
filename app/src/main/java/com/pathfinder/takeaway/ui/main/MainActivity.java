package com.pathfinder.takeaway.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pathfinder.takeaway.R;
import com.pathfinder.takeaway.injector.HasComponent;
import com.pathfinder.takeaway.ui.BaseActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity
    implements View.OnClickListener, MainContract.View, HasComponent<MainComponent> {

  @BindView(R.id.fl_main_container) FrameLayout mFlMainContainer;
  @BindView(R.id.rbtn_home_page) RadioButton mRbtnHomePage;
  @BindView(R.id.rbtn_user_center) RadioButton mRbtnUserCenter;
  @BindView(R.id.rg_main_menu) RadioGroup mRgMainMenu;
  @BindView(R.id.rbtn_order) RadioButton mRbtnOrder;

  public static void startActivity(Context mContext) {
    Intent intent = new Intent(mContext, MainActivity.class);
    mContext.startActivity(intent);
  }

  @Inject MainPresenter mPresenter;

  private MainComponent mMainComponent;

  @Override public int initContentView() {
    return R.layout.activity_main;
  }

  @Override public void initInjector() {
    mMainComponent = DaggerMainComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .mainModule(new MainModule(this))
        .build();
    //mMainComponent.inject(this);
  }

  @Override public void initUiAndListener() {

  }

  @Override protected boolean isApplyStatusBarTranslucency() {
    return true;
  }

  @Override protected boolean isApplyStatusBarColor() {
    return true;
  }

  @Override public void onBackPressed() {
    mPresenter.exist();
  }

  @Override public void onClick(View v) {

  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPresenter.detachView();
  }

  @Override public void showLoginUi() {
    //LoginActivity.startActivity(this);
  }

  @Override public void showAccountUi() {
    //AccountActivity.startActivity(this);
  }

  @Override public void showSettingUi() {

  }

  @Override public MainComponent getComponent() {
    return mMainComponent;
  }

  @OnClick(R.id.rg_main_menu) public void onClick() {

  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }
}
