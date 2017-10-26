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

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.WindowEvent;

/**
 * Simple Earth Map controller.
 *
 * @author Travis Rennemann
 */
public class EarthMapPane extends StackPane {

    private final ObservableList<Mappable> mapNodes = FXCollections.<Mappable>observableArrayList();

    /**
     * Constructs the controller.
     */
    public EarthMapPane() {
        this.load();
    }

    /**
     * Constructs the controller with overlays and/or placemarks.
     *
     * @param mapNodes The nodes to add to the map as placemarks or overlays
     */
    public EarthMapPane(Mappable... mapNodes) {
        this.mapNodes.addAll(Arrays.asList(mapNodes));
        this.load();
    }

    private void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(EarthMapPane.class.getResource("/views/EarthMapPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        URL css = EarthMapPane.class.getResource("/styles/earthmap.css");
        if (css != null) {
            getStylesheets().add(css.toExternalForm());
        }
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            //constructor exceptions must be unchecked
            throw new RuntimeException(ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        mapNodes.addListener((Observable c) -> plotNode((Mappable) c));
        super.widthProperty().addListener((Observable observable) -> {
            if (mapNodes.size() > 0) {
                // plot initial nodes
                mapNodes.forEach((node) -> plotNode(node));
            }
        });
    }

    /**
     * Place the node in a specific location on the map.
     *
     * @param mapNode The mappable node to put on the map
     */
    public void plotNode(Mappable mapNode) {
        final double halfHeight = super.getHeight() / 2;
        final double halfWidth = super.getWidth() / 2;

        // translate the lat lon coordinate to the stackpane's coordinate system
        double latGridCoord = ((mapNode.getLat() / 90.0) * halfHeight) * -1;
        double lonGridCoord = ((mapNode.getLon() / 180.0) * halfWidth);

        // try to find the node
        Node node;
        int idx = super.getChildren().indexOf(mapNode.getNode());
        if (idx > -1) {
            // the node is already displayed so just move it
            node = super.getChildren().get(idx);
        } else {
            // the node hasn't been added yet, so add it now
            node = mapNode.getNode();
            super.getChildren().add(node);
        }
        // position the node on the map
        node.setTranslateY(latGridCoord);
        node.setTranslateX(lonGridCoord);
    }
}
