package com.pathfinder.takeaway.injector.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class DBModule {

  //@Provides @Singleton DaoMaster.DevOpenHelper provideDevOpenHelper(Context context) {
  //  return new DaoMaster.DevOpenHelper(context, "app.db", null);
  //}
  //
  //@Provides @Singleton DaoMaster provideDaoMaster(DaoMaster.DevOpenHelper helper) {
  //  return new DaoMaster(helper.getWritableDatabase());
  //}
  //
  //@Provides @Singleton DaoSession provideDaoSession(DaoMaster master) {
  //  return master.newSession();
  //}
  //
  //@Provides @Singleton UserDao getUserDao(DaoSession session) {
  //  return session.getUserDao();
  //}


}
