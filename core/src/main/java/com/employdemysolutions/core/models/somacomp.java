package com.employdemysolutions.core.models;


import com.adobe.cq.export.json.ComponentExporter;

public interface somacomp  extends ComponentExporter{

    @Override
    default String getExportedType() {
        throw new UnsupportedOperationException();
    }
}


