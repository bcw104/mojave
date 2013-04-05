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
package org.mojavemvc.tests.controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.mojavemvc.annotations.Action;
import org.mojavemvc.annotations.BeforeAction;
import org.mojavemvc.annotations.StatelessController;
import org.mojavemvc.views.JSP;
import org.mojavemvc.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * 
 * @author Luis Antunes
 */
@StatelessController("stream3")
public class StreamController3 {

    private static final Logger logger = LoggerFactory.getLogger(StreamController3.class);

    @Inject
    private HttpServletResponse response;

    @BeforeAction
    public void before() {

        try {

            response.setContentType("application/xml");
            PrintWriter pw = response.getWriter();
            pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Test><hello/></Test>");
            /*
             * in a situation like this, flush() must be called to commit the
             * response, otherwise this content will get overwritten
             */
            pw.flush();

        } catch (Exception e) {
            logger.error("error", e);
        }

    }

    @Action
    public View doSomething() {

        return new JSP("param").withAttribute("var", "streaming3");
    }
}
