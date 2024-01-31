package com.employdemysolutions.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.wcm.foundation.Search.Page;

@Model(
    adaptables = { SlingHttpServletRequest.class },
    adapters = { ComponentExporter.class },
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
    resourceType = { SiddharthComp.RESOURCE_TYPE }
)
@Exporter(
    name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
    selector = ExporterConstants.SLING_MODEL_SELECTOR,
    extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class SiddharthComp implements somacomp {
 private static final Logger LOG = LoggerFactory.getLogger( SiddharthComp.class);
    static final String RESOURCE_TYPE = "employdemy-solutions/components/siddharthcomp";

    @ValueMapValue
    private String guideTopText;

    @Inject
    Page currentPage;
    

    public String getCurrentPageTitle() {
        return currentPage.getURL();
    }

    public String getGuideTopText() {
        return guideTopText;
    }

    public String getGuideBottomText() {
        return guideBottomText;
    }

    @ValueMapValue
    private String guideBottomText;

    @ChildResource
    private List<SomacomponentList> guideRelevantList = new ArrayList<>();

    @Override
    public String getExportedType() {
        return SiddharthComp.RESOURCE_TYPE;
    }

    public List<SomacomponentList> getGuideRelevantList() {
        return guideRelevantList;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class SomacomponentList {

        public String getGuideListImage() {
            return guideListImage;
        }

        public String getIconImageAltText() {
            return iconImageAltText;
        }

        public String getGuideText() {
            return guideText;
        }

        @ValueMapValue
        private String guideListImage;

        @ValueMapValue
        private String iconImageAltText;

        @ValueMapValue
        private String guideText;

       
    }
}

