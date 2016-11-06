package com.pathfinder.takeaway.injector.component;

import android.app.Activity;
import com.pathfinder.takeaway.injector.PerActivity;
import com.pathfinder.takeaway.injector.module.ActivityModule;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  Activity getActivity();
}
