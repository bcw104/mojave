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
package org.mojavemvc.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * @author Luis Antunes
 */
public class XML extends StreamView {

    /*
     * XmlMapper is thread-safe
     */
    private static final XmlMapper mapper = new XmlMapper();
    
    private String payload;

    public XML(String payload) {

        this.payload = payload;
    }
    
    public XML(Object pojo) {
        
        try {
            payload = mapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("could not construct XML View", e);
        }
    }   

    @Override
    public String getContentType() {

        return "application/xml";
    }

    @Override
    public byte[] getPayload() {

        return payload.getBytes();
    }
    
    @Override
    public String toString() {
        return payload;
    }
}
