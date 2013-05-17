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

import java.lang.reflect.Method;

import org.junit.Test;
import org.mojavemvc.annotations.Param;
import org.mojavemvc.util.ParamPathHelper;

/**
 * @author Luis Antunes
 */
public class TestParamPathHelper {

    @Test
    public void getParamNamesFromParamPath_singleParam() {
        
        String[] params = ParamPathHelper.getParamNamesFrom(":name");
        assertArrayEquals(new String[]{"name"}, params);        
    }
    
    @Test
    public void getParamNamesFromParamPath_twoParams() {
        
        String[] params = ParamPathHelper.getParamNamesFrom(":name/:location");
        assertArrayEquals(new String[]{"name","location"}, params);        
    }
    
    @Test
    public void getParamNamesFromParamPath_noParams() {
        
        String[] params = ParamPathHelper.getParamNamesFrom("name/location");
        assertArrayEquals(new String[]{}, params);        
    }
    
    @Test
    public void getParamNamesFromParamPath_mixedParams() {
        
        String[] params = ParamPathHelper.getParamNamesFrom("to/:name");
        assertArrayEquals(new String[]{"name"}, params);        
    }
    
    @Test
    public void getParamNamesFromParamPath_regexParams() {
        
        String[] params = ParamPathHelper.getParamNamesFrom(":name<[a-z]+>/:id<[0-9]+>");
        assertArrayEquals(new String[]{"name","id"}, params);        
    }
    
    @Test
    public void getParamNamesFromParamMethod_singleParam()  throws Exception{
        
        Method method = TestController.class.getMethod("singleParam", String.class);
        String[] params = ParamPathHelper.getParamNamesFrom(method);
        assertArrayEquals(new String[]{"name"}, params);        
    }
    
    @Test
    public void getParamNamesFromParamMethod_twoParams() throws Exception {
        
        Method method = TestController.class.getMethod("twoParams", 
                String.class, String.class);
        String[] params = ParamPathHelper.getParamNamesFrom(method);
        assertArrayEquals(new String[]{"name","location"}, params);        
    }
    
    @Test
    public void getParamNamesFromParamMethod_noParams() throws Exception {
        
        Method method = TestController.class.getMethod("noParams");
        String[] params = ParamPathHelper.getParamNamesFrom(method);
        assertArrayEquals(new String[]{}, params);        
    }
    
    @SuppressWarnings("unused")
    private static class TestController {
        
        public void noParams() {}
        
        public void singleParam(@Param("name") String name) {}
        
        public void twoParams(@Param("name") String name, 
                @Param("location") String location) {}
    }
}
