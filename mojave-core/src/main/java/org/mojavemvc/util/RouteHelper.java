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
package org.mojavemvc.util;

/**
 * @author Luis Antunes
 */
public class RouteHelper {

    public static final String PATH_ELEMENT_SEPARATOR = "/";
    public static final String PARAM_PREFIX = ":";
    public static final String CUSTOM_REGEX_START = "<";
    public static final String CUSTOM_REGEX_END = ">";
    
    public static String[] getPathElements(String path) {
        if (path == null) throw new IllegalArgumentException("path cannot be null");
        path = path.trim();
        if (path.length() == 0) throw new IllegalArgumentException("path cannot be empty");
        return path.substring(1).split(PATH_ELEMENT_SEPARATOR);
    }
}
