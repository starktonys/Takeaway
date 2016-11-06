package com.pathfinder.takeaway.injector.module;

import com.pathfinder.takeaway.api.Api;
import com.pathfinder.takeaway.components.retrofit.RequestHelper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

@Module public class ApiModule {

  @Provides @Singleton public Api provideApi(RequestHelper requestHelper,
      @Named("api") OkHttpClient okHttpClient) {
    return new Api(requestHelper, okHttpClient);
  }

}
