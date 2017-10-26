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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * A mappable circular placemark.
 *
 * @author Travis Rennemann
 */
public class CirclePlacemark extends Circle implements Mappable {

    private double lat = 0;
    private double lon = 0;

    public CirclePlacemark(double radius) {
        super(radius);
    }

    public CirclePlacemark(double radius, Paint fill) {
        super(radius, fill);
    }

    public CirclePlacemark() {
    }

    public CirclePlacemark(double lon, double lat, double radius) {
        super(radius);
        this.lat = lat;
        this.lon = lon;
    }

    public CirclePlacemark(double lon, double lat, double radius, Paint fill) {
        super(radius, fill);
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public double getLat() {
        return lat;
    }

    @Override
    public double getLon() {
        return lon;
    }

    @Override
    public Node getNode() {
        return this;
    }
}
