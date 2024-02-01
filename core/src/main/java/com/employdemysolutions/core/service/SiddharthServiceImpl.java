package com.employdemysolutions.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.employdemysolutions.core.models.siddpojo;
import com.employdemysolutions.core.servlets.ResolverUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;

@Component(
        immediate = true,
        service = SiddharthService.class,
        name = "com.employdemysolutions.core.service.SiddharthServiceImpl",
        property = {
                "service.description=Getting the page names and paths"
        })
public class SiddharthServiceImpl implements SiddharthService {

    @Reference
    QueryBuilder queryBuilder;

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private Map<String, String> createSearchQuery(String path, String template) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("type", "cq:page");
        queryMap.put("path", path);
        queryMap.put("property", "jcr:content/cq:template");
        queryMap.put("property.value", template);
        queryMap.put("p.limit", "-1");
        return queryMap;
    }

    @Override
    public List<siddpojo> getPages(String path, String template) throws LoginException, RepositoryException {

        List<siddpojo>  renamedList;

        try (ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory)) {
            Session session = resourceResolver.adaptTo(Session.class);
            Query query = queryBuilder.createQuery(PredicateGroup.create(createSearchQuery(path, template)), session);

            SearchResult result = query.getResult();
            Iterable<Hit> hits = result.getHits();

            renamedList = StreamSupport.stream(hits.spliterator(), false)
                .map(hit -> {
                    try {
                        Page page = hit.getResource().adaptTo(Page.class);
                        String pagePath = page.getPath();
                        String pageName = page.getName();
                        return new siddpojo(pagePath, pageName);
                    } catch (RepositoryException e) {
                        throw new RuntimeException("Error adapting resource to Page", e);
                    }
                })
                .collect(Collectors.toList());
            
        }

        return  renamedList;
    }
}
