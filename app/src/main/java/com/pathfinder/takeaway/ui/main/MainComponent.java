package com.pathfinder.takeaway.ui.main;

import com.pathfinder.takeaway.injector.PerActivity;
import com.pathfinder.takeaway.injector.component.ApplicationComponent;
import com.pathfinder.takeaway.injector.module.ActivityModule;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, MainModule.class
}) public interface MainComponent {

  //void inject(MainActivity activity);

}
