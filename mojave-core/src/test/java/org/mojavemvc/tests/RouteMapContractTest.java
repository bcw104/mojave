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
package org.mojavemvc.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mojavemvc.core.Route;
import org.mojavemvc.core.RouteMap;

/**
 * 
 * @author Luis Antunes
 */
public abstract class RouteMapContractTest {

    private RouteMap rm;
    
    @Before
    public void beforeEachTest() {
        rm = newRouteMap();
    }
    
    protected abstract RouteMap newRouteMap();
    
    @Test
    public void getRoute_Root() {
        Route r1 = new Route(null, null, null);
        rm.add(r1);
        assertEquals(r1, rm.getRoute("/"));
    }
    
    @Test
    public void getRoute_SimilarMatchesConstant() {
        Route r1 = new Route(null, null, "clients/all");
        Route r2 = new Route(null, null, "clients/:id");
        rm.add(r1);
        rm.add(r2);
        assertEquals(r1, rm.getRoute("/clients/all"));
    }
    
    @Test
    public void getRoute_SimilarMatchesParam() {
        Route r1 = new Route(null, null, "clients/all");
        Route r2 = new Route(null, null, "clients/:id");
        rm.add(r1);
        rm.add(r2);
        assertEquals(r2, rm.getRoute("/clients/123"));
    }
    
    @Test
    public void getRoute_IgnoresParamRegion() {
        Route r1 = new Route("cntrl", null, null);
        Route r2 = new Route("cntrl", null, "clients/:id");
        rm.add(r1);
        rm.add(r2);
        assertEquals(r1, rm.getRoute("/cntrl"));
    }
    
    @Test
    public void getRoute_FindsParamRegion() {
        Route r1 = new Route("cntrl", null, null);
        Route r2 = new Route("cntrl", null, "clients/:id");
        rm.add(r1);
        rm.add(r2);
        assertEquals(r2, rm.getRoute("/cntrl/clients/23455"));
    }
    
    @Test
    public void getRoute_DistinguishesBetweenControllerAndAction() {
        Route r1 = new Route("cntrl", null, null);
        Route r2 = new Route(null, "actn", null);
        rm.add(r1);
        rm.add(r2);
        assertEquals(r2, rm.getRoute("/actn"));
    }
    
    @Test
    public void getRoute_NotFound() {
        Route r1 = new Route("cntrl", null, null);
        Route r2 = new Route(null, "actn", null);
        rm.add(r1);
        rm.add(r2);
        assertNull(rm.getRoute("/test"));
    }
    
    @Test
    public void getRoute_MultiParamRegions_Multiple() {
        Route r1 = new Route("cntrl", "actn", ":id");
        Route r2 = new Route("cntrl", "actn", ":id/:name");
        rm.add(r1);
        rm.add(r2);
        assertEquals(r2, rm.getRoute("/cntrl/actn/123/bob"));
    }
    
    @Test
    public void getRoute_MultiParamRegions_Single() {
        Route r1 = new Route("cntrl", "actn", ":id");
        Route r2 = new Route("cntrl", "actn", ":id/:name");
        rm.add(r1);
        rm.add(r2);
        assertEquals(r1, rm.getRoute("/cntrl/actn/123"));
    }
    
    @Test
    public void getRoute_CustomRegexAlpha() {
        Route r1 = new Route("cntrl", "actn", ":id<[0-9]+>");
        Route r2 = new Route("cntrl", "actn", ":id<[a-z]+>");
        rm.add(r1);
        rm.add(r2);
        assertEquals(r2, rm.getRoute("/cntrl/actn/bob"));
    }
    
    @Test
    public void getRoute_CustomRegexNumeric() {
        Route r1 = new Route("cntrl", "actn", ":id<[0-9]+>");
        Route r2 = new Route("cntrl", "actn", ":id<[a-z]+>");
        rm.add(r1);
        rm.add(r2);
        assertEquals(r1, rm.getRoute("/cntrl/actn/123"));
    }
}
