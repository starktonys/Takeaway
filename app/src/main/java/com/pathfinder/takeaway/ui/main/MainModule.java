package com.pathfinder.takeaway.ui.main;

import com.pathfinder.takeaway.components.okhttp.OkHttpHelper;
import com.pathfinder.takeaway.injector.PerActivity;
import com.pathfinder.takeaway.util.UpdateAgent;
import dagger.Module;
import dagger.Provides;

@Module public class MainModule {

  private MainActivity mActivity;

  public MainModule(MainActivity mActivity) {
    this.mActivity = mActivity;
  }

  @Provides @PerActivity UpdateAgent provideUpdateAgent(OkHttpHelper mOkHttpHelper) {
    return new UpdateAgent(mOkHttpHelper, mActivity);
  }

}
