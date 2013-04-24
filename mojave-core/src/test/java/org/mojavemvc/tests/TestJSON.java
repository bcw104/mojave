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

import org.junit.Test;
import org.mojavemvc.views.JSON;

/**
 * @author Luis Antunes
 */
public class TestJSON {

    @Test
    public void returnsCorrectContentType() {
        JSON json = new JSON("");
        assertEquals("application/json", json.getContentType());
    }
    
    @Test
    public void toStringAfterObjectConstructorReturnsJSON() {
        JSON json = new JSON(new SimplePojo("test"));
        assertEquals("{\"val\":\"test\"}", json.toString());
    }
    
    /*------------------------------------*/
    
    public static class SimplePojo {
        private String val;
        
        public SimplePojo(String val) {
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        
        public void setVal(String val) {
            this.val = val;
        }
    }
}
