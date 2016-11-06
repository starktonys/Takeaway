package com.pathfinder.takeaway.injector.component;

import android.app.Service;
import com.pathfinder.takeaway.injector.PerService;
import com.pathfinder.takeaway.injector.module.ServiceModule;
import dagger.Component;


@PerService @Component(dependencies = ApplicationComponent.class, modules = { ServiceModule.class })
public interface ServiceComponent {

  Service getServiceContext();

}
