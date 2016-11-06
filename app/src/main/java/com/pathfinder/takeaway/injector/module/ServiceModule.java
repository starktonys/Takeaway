package com.pathfinder.takeaway.injector.module;

import android.app.Service;
import com.pathfinder.takeaway.injector.PerService;
import dagger.Module;
import dagger.Provides;

@Module public class ServiceModule {
  private Service mService;

  public ServiceModule(Service service) {
    mService = service;
  }

  @Provides @PerService public Service provideContext() {
    return mService;
  }
}
