package com.pathfinder.takeaway.ui.splash;

import com.pathfinder.takeaway.injector.PerActivity;
import com.pathfinder.takeaway.injector.component.ApplicationComponent;
import com.pathfinder.takeaway.injector.module.ActivityModule;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, SplashModule.class
}) public interface SplashComponent {

  void inject(SplashActivity activity);
}
