/*
 * Copyright 2017 Travis Rennemann.
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
package org.rennemann.javafx.simpleearthmap;

import javafx.scene.Node;

/**
 * Objects that can be displayed on a map by latitude and longitude values.
 *
 * @author Travis Rennemann
 */
public interface Mappable {

    /**
     * Gets the object's latitude value.
     *
     * @return
     */
    public double getLat();

    /**
     * Gets the object's longitude value.
     *
     * @return
     */
    public double getLon();

    /**
     * Gets the object's node value.
     *
     * @return
     */
    public Node getNode();
}
