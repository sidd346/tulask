package com.employdemysolutions.core.service;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Component;

import com.day.cq.search.QueryBuilder;

import com.day.cq.search.PredicateGroup;

import javax.jcr.Session;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component(service = PageQueryService.class, immediate = true)
public class PageQueryServiceImpl implements PageQueryService {

    @OSGiService
    private QueryBuilder queryBuilder;

    @OSGiService
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public List<PageInfo> queryPagesWithTemplate() {
        List<PageInfo> pageInfoList = new ArrayList<>();

        try (ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(null)) {
            if (queryBuilder == null) {
                throw new RuntimeException("QueryBuilder is null. Ensure OSGi configuration is correct.");
            }

            // Build the query using Map
            Map<String, String> queryParams = Map.of(
                    "type", "cq:Page",
                    "path", "/content/employdemy-solutions",
                    "property", "jcr:content/cq:template",
                    "property.value", "/conf/employdemy-solutions/settings/wcm/templates/page-content",
                    "p.limit", "-1");

            // Execute the query
            Iterator<Resource> resultIterator = queryBuilder.createQuery(PredicateGroup.create(queryParams), resourceResolver.adaptTo(Session.class))
                    .getResult().getResources();

            // Extract and put page paths and titles into the list
            while (resultIterator.hasNext()) {
                Resource pageResource = resultIterator.next();
                PageInfo pageInfo = new PageInfo();
                pageInfo.setPagePath(pageResource.getPath());
                pageInfo.setPageTitle(pageResource.getValueMap().get("jcr:title", String.class));
                pageInfoList.add(pageInfo);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error executing query", e);
        }

        return pageInfoList;
    }
}
