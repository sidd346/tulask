package com.employdemysolutions.core.models;

import javax.inject.Inject;
import javax.inject.Named;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
 
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
 
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class AccordionModel {
      @Inject
    private String title;
 
    @Inject
    private String subtitle;
 
    @Inject
    @Named("multifield/.")
    private List<AccordionBulletPointModel> bulletPointList;
 
    public String getTitle() {
        return title;
    }
 
    public String getSubtitle() {
        return subtitle;
    }
 
    public List<AccordionBulletPointModel> getBulletPointList() {
        return bulletPointList;
    }
}
