/*
 * Copyright (C) 2011-2012 Mojavemvc.org
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mojavemvc.annotations.Action;
import org.mojavemvc.annotations.BeforeAction;
import org.mojavemvc.annotations.Param;
import org.mojavemvc.annotations.StatelessController;
import org.mojavemvc.aop.RequestContext;
import org.mojavemvc.views.JspView;
import org.mojavemvc.views.View;

@StatelessController("beforectx")
public class BeforeWithContextController {

    @BeforeAction
    public View beforeAction(RequestContext ctx) {

        HttpServletRequest req = ctx.getRequest();
        if (req == null) {
            throw new RuntimeException("request null");
        }
        HttpServletResponse resp = ctx.getResponse();
        if (resp == null) {
            throw new RuntimeException("response null");
        }
        Object[] parameters = ctx.getParameters();
        if (parameters == null || parameters.length != 1) {
            throw new RuntimeException("parameters not set");
        }
        if (!(parameters[0] instanceof String)) {
            throw new RuntimeException("parameter is of wrong type");
        }
        String action = ctx.getAction();
        if (!"index".equals(action)) {
            throw new RuntimeException("action incorrect");
        }
        String controller = ctx.getController();
        if (!"beforectx".equals(controller)) {
            throw new RuntimeException("controller incorrect");
        }

        return new JspView("param.jsp").withAttribute("var", parameters[0]);
    }

    @Action("index")
    public View someAction(@Param("p1") String name) {

        return new JspView("index.jsp");
    }

}
