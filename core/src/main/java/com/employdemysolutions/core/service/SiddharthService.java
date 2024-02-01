package com.employdemysolutions.core.service;

import javax.jcr.RepositoryException;
import java.util.List;

import org.apache.sling.api.resource.LoginException;

import com.employdemysolutions.core.models.siddpojo;

public interface SiddharthService {
    List<siddpojo> getPages(String path,String template) throws LoginException, RepositoryException;
    
}
