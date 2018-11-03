/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.libertymutualgroup.herman.aws.ecs;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PropertyHandler {

    static final Pattern PROPERTY_PATTERN = Pattern.compile("\\$\\{([a-zA-Z0-9\\.\\_\\-]+)\\}");
    private Set<String> propertyKeysUsed = new HashSet<>();

    public abstract void addProperty(String key, String value);

    public abstract String mapInProperties(String template);

    public abstract Properties lookupProperties(String... propList);

    public abstract String lookupVariable(String inputKey);

    public Set<String> getPropertiesToMatch(String template) {
        Set<String> propertiesToMatch = new HashSet<>();
        Matcher propMatcher = PROPERTY_PATTERN.matcher(template);
        while (propMatcher.find()) {
            String propVal = propMatcher.group();
            propertiesToMatch.add(propVal);
        }
        return propertiesToMatch;
    }

    public Set<String> getPropertyKeysUsed() {
        return propertyKeysUsed;
    }

}