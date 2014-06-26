package com.epam.cq.demo.services.osgi;

import java.util.ArrayList;
import java.util.List;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.hooks.service.EventHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifide.slice.api.context.ContextScope;
import com.cognifide.slice.api.injector.InjectorRunner;
import com.cognifide.slice.commons.SliceModulesFactory;
import com.cognifide.slice.core.internal.context.SliceContextScope;
import com.cognifide.slice.cq.module.CQModulesFactory;
import com.cognifide.slice.validation.ValidationModulesFactory;
import com.epam.cq.demo.services.interceptors.MetricsHooks;
import com.google.inject.Module;

/**
 * Bundle activator for com.epam.cq.demo - webinar-services.
 */
public class Activator extends DependencyActivatorBase implements BundleActivator {

    private static final Logger LOG = LoggerFactory.getLogger(Activator.class);

    /*
     * the pattern that defines which bundles to scan in order to find classes annotated by
     * 
     * @SliceResource
     */
    private static final String BUNDLE_NAME_FILTER = "com\\.epam\\.cq\\.demo\\.services\\..*";
    /*
     * the base package that defines which packages inside bundles specified above to scan in order
     * to find classes annotated by @SliceResource annotation.
     */
    private static final String BASE_PACKAGE = "com.epam.cq.demo.services";
    /* name of the injector under which is registered */
    private static final String INJECTOR_NAME = "webinar";

    // private ServiceProxyManager listenerEvent;

    @Override
    public void init(BundleContext bundleContext, DependencyManager dependencyManager) throws Exception {
        // 1. Using dependency manager for aspect service registration
        /*
         * Register aspect service in service registry manually
         */

        // String filter = "";
        // dependencyManager.add(dependencyManager.createAspectService(GoodbyeWorldService.class,
        // filter, 50)
        // .setImplementation(GoodbyeWorldServiceMonitor.class));
    }

    @Override
    public void destroy(BundleContext bundleContext, DependencyManager dependencyManager) throws Exception {

    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        LOG.info("start in activator was call");
        super.start(bundleContext);

        final ContextScope scope = new SliceContextScope();
        /*
         * It's a helper which allows you to add modules, creates injector and register it in
         * injector repository.
         */
        final InjectorRunner injectorRunner = new InjectorRunner(bundleContext, INJECTOR_NAME, scope);

        List<Module> sliceModules = SliceModulesFactory.createModules(bundleContext, INJECTOR_NAME, BUNDLE_NAME_FILTER,
                BASE_PACKAGE);
        List<Module> cqModules = CQModulesFactory.createModules();
        List<Module> validationModules = ValidationModulesFactory.createModules();
        List<Module> customModules = createCustomModules();

        injectorRunner.installModules(sliceModules);
        injectorRunner.installModules(cqModules);
        injectorRunner.installModules(validationModules);
        injectorRunner.installModules(customModules);

        injectorRunner.start();

        /*
         * 2. Collect metrics based on OSGi 4.2.0 Service Hooks
         * 
         * Register dynamic proxy service which handle all method calls. We can collect metrics
         * before/after method invocation
         */
        MetricsHooks loggerHooks = new MetricsHooks(bundleContext);
        bundleContext.registerService(new String[] { EventHook.class.getName() }, loggerHooks, null);

        /*
         * TODO: 3. Collect metrics based on OSGi 4.1.0 with AllServiceListener interface and CGLIB
         * help
         * 
         * Register dynamic proxy service which handle all method calls. We can collect metrics
         * before/after method invocation
         */

        // listenerEvent = new ServiceProxyManager();
        // bundleContext.addServiceListener(listenerEvent);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        super.stop(bundleContext);

        // bundleContext.removeServiceListener(listenerEvent);
    }

    private List<Module> createCustomModules() {
        List<Module> applicationModules = new ArrayList<Module>();

        applicationModules.add(new OsgiServicesModule());
        return applicationModules;
    }

}
