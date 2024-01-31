package com.employdemysolutions.core.models;

import com.employdemysolutions.core.service.PageInfo;
import com.employdemysolutions.core.service.PageQueryService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.employdemysolutions.core.service.PageInfo;
import com.employdemysolutions.core.service.PageQueryService;

@Model
   ( adaptables =Resource.class)

public class  OverviewArticleComp   {
    private static final Logger LOG = LoggerFactory.getLogger(OverviewArticleComp .class);
    @ValueMapValue
    private String pageTitle;

    public String  getPageTitle() {
        return   pageTitle;
    }

    @ValueMapValue
    private String PathPath;

    public String getPagepath() {
        return PathPath;
    }

     @OSGiService
    private PageQueryService pageQueryService;

    private List<PageInfo> pageInfoList;

    public void init() {
        if (pageQueryService != null) {
            pageInfoList = pageQueryService.queryPagesWithTemplate();
        }
    }

    public List<PageInfo> getPageInfoList() {
        return pageInfoList;
    }
}

