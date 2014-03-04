package com.epam.cq.demo.services.osgi;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifide.slice.api.context.ContextScope;
import com.cognifide.slice.api.injector.InjectorRunner;
import com.cognifide.slice.commons.SliceModulesFactory;
import com.cognifide.slice.core.internal.context.SliceContextScope;
import com.cognifide.slice.cq.module.CQModulesFactory;
import com.cognifide.slice.validation.ValidationModulesFactory;
import com.google.inject.Module;

/**
 * Bundle activator for com.epam.cq.demo - webinar-services.
 */
public class Activator implements BundleActivator {

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

    @Override
    public void start(BundleContext bundleContext) {

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
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        // To change body of implemented methods use File | Settings | File Templates.
    }

    private List<Module> createCustomModules() {
        List<Module> applicationModules = new ArrayList<Module>();
        // populate the list with your modules
        return applicationModules;
    }

}
