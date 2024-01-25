package com.employdemysolutions.core.servlets;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;

public class ResolverUtil {
     private static final String SYSTEM_USER = "siddharth";

    static
    ResourceResolverFactory resourceResolverFactory;

   
    public static ResourceResolver getResourceResolver() throws LoginException {

        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, SYSTEM_USER);
        return resourceResolverFactory.getServiceResourceResolver(param);
    }
}


    

