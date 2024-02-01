package com.employdemysolutions.core.models;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.employdemysolutions.core.service.SiddharthService;

@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SiddModel {

    @ValueMapValue
    private String path;

    @ValueMapValue
    private String template;

    @OSGiService
    private SiddharthService siddharthService;


    public String getPath() {
        return path;
    }

    public String getTemplate() {
        return template;
    }

    private List<siddpojo> pageinfo = new ArrayList<>();

    public List<siddpojo> getPageinfo() {
        return pageinfo;
    }

    @PostConstruct
    protected void init() {
        try {
            pageinfo = siddharthService.getPages(path, template);
        } catch (LoginException | RepositoryException e) {
            // Handle exception appropriately, e.g., log or throw a RuntimeException
            e.printStackTrace();
        }
    }
}
