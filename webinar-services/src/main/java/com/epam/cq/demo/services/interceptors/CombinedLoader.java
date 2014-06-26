package com.epam.cq.demo.services.interceptors;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class CombinedLoader extends ClassLoader {

    Set<ClassLoader> loaders = new HashSet<ClassLoader>();

    public void addLoader(ClassLoader loader) {
        loaders.add(loader);
    }

    public Class findClass(String name) throws ClassNotFoundException {
        for (ClassLoader loader : loaders) {
            try {
                return loader.loadClass(name);
            } catch (ClassNotFoundException cnfe) {
                // Try next
            }
        }
        throw new ClassNotFoundException(name);
    }

    public URL getResource(String name) {
        for (ClassLoader loader : loaders) {
            URL url = loader.getResource(name);
            if (url != null)
                return url;
        }
        return null;
    }
}
