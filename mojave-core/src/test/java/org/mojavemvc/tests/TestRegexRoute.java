package org.mojavemvc.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mojavemvc.core.RegexRoute;
import org.mojavemvc.core.Route;

public class TestRegexRoute {

    @Test
    public void pattern_NoController_NoAction_NoParamPath() {
        RegexRoute r = new RegexRoute(new Route(null, null, null));
        String expected = "^/$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_WithController_NoAction_NoParamPath() {
        RegexRoute r = new RegexRoute(new Route("cntrl", null, null));
        String expected = "^/cntrl$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_WithController_WithAction_NoParamPath() {
        RegexRoute r = new RegexRoute(new Route("cntrl", "actn", null));
        String expected = "^/cntrl/actn$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_NoController_WithAction_NoParamPath() {
        RegexRoute r = new RegexRoute(new Route(null, "actn", null));
        String expected = "^/actn$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_WithController_WithAction_WithParamPath() {
        RegexRoute r = new RegexRoute(new Route("cntrl", "actn", "clients/:id"));
        String expected = "^/cntrl/actn/clients/([^/]+)$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_NoController_WithAction_WithParamPath() {
        RegexRoute r = new RegexRoute(new Route(null, "actn", "clients/:id"));
        String expected = "^/actn/clients/([^/]+)$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_WithController_NoAction_WithParamPath() {
        RegexRoute r = new RegexRoute(new Route("cntrl", null, "clients/:id"));
        String expected = "^/cntrl/clients/([^/]+)$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_NoController_NoAction_WithParamPath() {
        RegexRoute r = new RegexRoute(new Route(null, null, "clients/:id"));
        String expected = "^/clients/([^/]+)$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_WithController_WithAction_WithRegexParamPath() {
        RegexRoute r = new RegexRoute(new Route("cntrl", "actn", "clients/:id<[0-9]+>"));
        String expected = "^/cntrl/actn/clients/([0-9]+)$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_WithController_WithAction_WithMultiRegexParamPath() {
        RegexRoute r = new RegexRoute(new Route("cntrl", "actn", "clients/:id<[0-9]+>/:name"));
        String expected = "^/cntrl/actn/clients/([0-9]+)/([^/]+)$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void pattern_WithController_WithAction_WithRegexSymbols() {
        RegexRoute r = new RegexRoute(new Route("cntrl", "actn", "a+b/:id<[0-9]+>/:name"));
        String expected = "^/cntrl/actn/a\\+b/([0-9]+)/([^/]+)$";
        String actual = r.pattern().toString();
        assertEquals(expected, actual);
    }
}
