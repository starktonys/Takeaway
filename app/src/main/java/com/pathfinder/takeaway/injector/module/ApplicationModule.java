package com.pathfinder.takeaway.injector.module;

import android.app.NotificationManager;
import android.content.Context;
import android.view.LayoutInflater;
import com.pathfinder.takeaway.components.okhttp.CookieInterceptor;
import com.pathfinder.takeaway.components.okhttp.HttpLoggingInterceptor;
import com.pathfinder.takeaway.components.okhttp.OkHttpHelper;
import com.pathfinder.takeaway.components.retrofit.RequestHelper;
import com.pathfinder.takeaway.components.storage.UserStorage;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

@Module public class ApplicationModule {

  private final Context context;

  public ApplicationModule(Context context) {
    this.context = context;
  }

  @Provides @Singleton public Context provideApplicationContext() {
    return context.getApplicationContext();
  }

  @Provides @Singleton public Bus provideBusEvent() {
    return new Bus();
  }

  @Provides @Singleton @Named("api") OkHttpClient provideApiOkHttpClient(
      CookieInterceptor mCookieInterceptor) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS);
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    builder.addInterceptor(logging);
    builder.addInterceptor(mCookieInterceptor);
    return builder.build();
  }


  @Provides @Singleton OkHttpClient provideOkHttpClient(@Named("api") OkHttpClient mOkHttpClient) {
    OkHttpClient.Builder builder = mOkHttpClient.newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true);
    builder.interceptors().clear();
    return builder.build();
  }

  @Provides @Singleton LayoutInflater provideLayoutInflater(Context context) {
    return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Provides @Singleton NotificationManager provideNotificationManager(Context mContext) {
    return (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
  }

  @Provides @Singleton CookieInterceptor provideCookieInterceptor(UserStorage mUserStorage) {
    return new CookieInterceptor(mUserStorage);
  }

  @Provides @Singleton UserStorage provideUserStorage(Context mContext) {
    return new UserStorage(mContext);
  }

  @Provides @Singleton RequestHelper provideRequestHelper(Context mContext,
      UserStorage mUserStorage) {
    return new RequestHelper(mContext, mUserStorage);
  }

  @Provides @Singleton OkHttpHelper provideOkHttpHelper(OkHttpClient mOkHttpClient) {
    return new OkHttpHelper(mOkHttpClient);
  }
}
