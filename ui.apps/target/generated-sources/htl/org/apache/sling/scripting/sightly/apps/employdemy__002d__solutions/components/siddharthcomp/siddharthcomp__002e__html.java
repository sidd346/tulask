/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.employdemy__002d__solutions.components.siddharthcomp;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class siddharthcomp__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_model = null;
Collection var_collectionvar2_list_coerced$ = null;
out.write("\r\n");
_global_model = renderContext.call("use", com.employdemysolutions.core.models.SiddharthComp.class.getName(), obj());
out.write("\r\n    <div class=\"siddharthcomp\">\r\n        <h2>");
{
    Object var_0 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_model, "guideTopText"), "text");
    out.write(renderContext.getObjectModel().toString(var_0));
}
out.write("</h2>\r\n        <p>");
{
    Object var_1 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_model, "guideBottomText"), "text");
    out.write(renderContext.getObjectModel().toString(var_1));
}
out.write("</p>\r\n       \r\n                ");
{
    Object var_collectionvar2 = renderContext.getObjectModel().resolveProperty(_global_model, "guideRelevantList");
    {
        long var_size3 = ((var_collectionvar2_list_coerced$ == null ? (var_collectionvar2_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar2)) : var_collectionvar2_list_coerced$).size());
        {
            boolean var_notempty4 = (var_size3 > 0);
            if (var_notempty4) {
                {
                    long var_end7 = var_size3;
                    {
                        boolean var_validstartstepend8 = (((0 < var_size3) && true) && (var_end7 > 0));
                        if (var_validstartstepend8) {
                            if (var_collectionvar2_list_coerced$ == null) {
                                var_collectionvar2_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar2);
                            }
                            long var_index9 = 0;
                            for (Object item : var_collectionvar2_list_coerced$) {
                                {
                                    boolean var_traversal11 = (((var_index9 >= 0) && (var_index9 <= var_end7)) && true);
                                    if (var_traversal11) {
                                        out.write("\r\n                    <li class=\"siddharthcomp-list-item\">\r\n                        <img");
                                        {
                                            Object var_attrvalue12 = renderContext.getObjectModel().resolveProperty(item, "guideListImage");
                                            {
                                                Object var_attrcontent13 = renderContext.call("xss", var_attrvalue12, "uri");
                                                {
                                                    boolean var_shoulddisplayattr15 = (((null != var_attrcontent13) && (!"".equals(var_attrcontent13))) && ((!"".equals(var_attrvalue12)) && (!((Object)false).equals(var_attrvalue12))));
                                                    if (var_shoulddisplayattr15) {
                                                        out.write(" src");
                                                        {
                                                            boolean var_istrueattr14 = (var_attrvalue12.equals(true));
                                                            if (!var_istrueattr14) {
                                                                out.write("=\"");
                                                                out.write(renderContext.getObjectModel().toString(var_attrcontent13));
                                                                out.write("\"");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        out.write(" width=\"500\" height=\"600\"/>");
                                        {
                                            String var_16 = (((("\r\n                        " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(item, "iconImageAltText"), "text"))) + "\r\n                        ") + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(item, "guideText"), "text"))) + "\r\n                    ");
                                            out.write(renderContext.getObjectModel().toString(var_16));
                                        }
                                        out.write("</li>\r\n                ");
                                    }
                                }
                                var_index9++;
                            }
                        }
                    }
                }
            }
        }
    }
    var_collectionvar2_list_coerced$ = null;
}
out.write("\r\n            </div>\r\n         ");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

