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
package org.mojavemvc.core;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Luis Antunes
 */
public class HttpRequestRouter implements RequestRouter {

    private static final String SEPARATOR = "/";
    private static final String QUERY_STRING = "?";
    
    private final HttpServletRequest req;
    
    public HttpRequestRouter(HttpServletRequest req) {
        this.req = req;
    }
    
    @Override
    public RoutedRequest route() {
        
        String controller = null;
        String action = null;
        String path = req.getPathInfo();
        
        if (path != null && path.startsWith(SEPARATOR)) {
            
            String[] pathTokens = path.split(SEPARATOR);
            if (pathTokens.length > 1) {
                controller = pathTokens[1];
                if (pathTokens.length > 2) {
                    action = pathTokens[2];
                    if (action.contains(QUERY_STRING)) {
                        action = action.substring(0, action.indexOf(QUERY_STRING));
                    }
                }
            }
        }
        
        return new RoutedRequest(controller, action);
    }
}
