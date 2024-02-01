package com.employdemysolutions.core.service;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.employdemysolutions.core.models.AssesmentPojo;
import com.employdemysolutions.core.servlets.ResolverUtil;
import com.day.cq.wcm.api.Page;

@Component(immediate = true, service = AssesmentService.class, name = "com.employdemysolutions.core.services.AssessmentServiceImpl", 
        property = {
        "service.description=Getting the page names and paths" })
public class AssessmentServiceImpl implements AssesmentService {
    
    @Reference
    ResourceResolverFactory resourceResolverFactory;


    @Reference
    QueryBuilder queryBuilder;

     public Map<String,String> createSearchQuery(String path,String template){
        Map<String,String> queryMap=new HashMap<>();
        //Integer num=(Integer)number;
        queryMap.put("type","cq:page");
        queryMap.put("path",path);
        queryMap.put("property","jcr:content/cq:template");
        queryMap.put("property.value",template);
        queryMap.put("p.limit","-1");  
        return queryMap;
    }

    @Override
    public List<AssesmentPojo> getPages(String path,String template) throws LoginException, RepositoryException {

        List<AssesmentPojo> resultPojos=new ArrayList<AssesmentPojo> ();
       ResourceResolver resourceResolver=ResolverUtil.newResolver(resourceResolverFactory);
        Session session=resourceResolver.adaptTo(Session.class);
         Query query = queryBuilder.createQuery(PredicateGroup.create(createSearchQuery(path,template)), session);

            SearchResult result = query.getResult();
            List<Hit> hits =result.getHits();
            
            for(Hit hit: hits){
                Page page=hit.getResource().adaptTo(Page.class);
                String pagePath=page.getPath();
                String pageName=page.getName();
                AssesmentPojo pojoObject=new AssesmentPojo(pagePath, pageName);
                resultPojos.add(pojoObject);
            }  
        return resultPojos;
        
    }
    
}
