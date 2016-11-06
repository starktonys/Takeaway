package com.pathfinder.takeaway.ui.splash;

import dagger.Module;

@Module public class SplashModule {

  private SplashActivity mActivity;

  public SplashModule(SplashActivity mActivity) {
    this.mActivity = mActivity;
  }
}
