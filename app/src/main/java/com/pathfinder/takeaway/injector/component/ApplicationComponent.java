package com.pathfinder.takeaway.injector.component;

import android.content.Context;
import com.pathfinder.takeaway.MyApplication;
import com.pathfinder.takeaway.api.Api;
import com.pathfinder.takeaway.components.okhttp.OkHttpHelper;
import com.pathfinder.takeaway.components.storage.UserStorage;
import com.pathfinder.takeaway.injector.module.ApiModule;
import com.pathfinder.takeaway.injector.module.ApplicationModule;
import com.pathfinder.takeaway.injector.module.DBModule;
import com.pathfinder.takeaway.ui.BaseActivity;
import com.squareup.otto.Bus;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = {
    ApplicationModule.class, ApiModule.class, DBModule.class
}) public interface ApplicationComponent {

  Context getContext();

  Api getApi();

  Bus getBus();

  OkHttpHelper getOkHttpHelper();

  UserStorage getUserStorage();

  void inject(MyApplication mApplication);

  void inject(BaseActivity mBaseActivity);

}
