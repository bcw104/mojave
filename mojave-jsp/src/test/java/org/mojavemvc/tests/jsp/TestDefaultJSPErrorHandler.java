/*
 * Copyright (C) 2011-2013 Mojavemvc.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mojavemvc.tests.jsp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mojavemvc.exception.DefaultJSPErrorHandler;
import org.mojavemvc.exception.ErrorHandler;
import org.mojavemvc.initialization.AppProperties;
import org.mojavemvc.views.JSP;
import org.mojavemvc.views.View;

/**
 * @author Luis Antunes
 */
public class TestDefaultJSPErrorHandler {

    @Test
    public void handleError() throws Exception {

        String errorJsp = "error.jsp";
        
        AppProperties properties = mock(AppProperties.class);
        when(properties.getProperty(DefaultJSPErrorHandler.JSP_ERROR_FILE))
            .thenReturn(errorJsp);

        ErrorHandler errorHandler = new DefaultJSPErrorHandler();
        View view = errorHandler.handleError(null, properties);

        assertTrue(view instanceof JSP);
        JSP jspView = (JSP) view;
        assertEquals(errorJsp, jspView.getJSPName());
    }
}