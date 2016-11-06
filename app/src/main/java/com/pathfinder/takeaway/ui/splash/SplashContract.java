package com.pathfinder.takeaway.ui.splash;

import com.pathfinder.takeaway.ui.BasePresenter;
import com.pathfinder.takeaway.ui.BaseView;

public interface SplashContract {

  interface View extends BaseView {
    void showMainUi();
  }

  interface Presenter extends BasePresenter<View> {
    void initUmeng();

    void initHuPuSign();
  }

}
