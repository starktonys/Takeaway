package com.pathfinder.takeaway.ui.main;

import com.pathfinder.takeaway.ui.BasePresenter;
import com.pathfinder.takeaway.ui.BaseView;

public interface MainContract {

  interface View extends BaseView {

    void reload();

    void showLoginUi();

    void showAccountUi();

    void showSettingUi();
  }

  interface Presenter extends BasePresenter<View> {

    void exist();

    boolean isLogin();
  }
}
