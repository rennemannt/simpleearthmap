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

import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.framework.junit.ApplicationTest;

/**
 * Simple Earth Map Controller tests.
 *
 * @author Travis Rennemann
 */
// tests may need to be performed in specific order when working with TestFX.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EarthMapPaneTest extends ApplicationTest {

    private static final Double STAGE_WIDTH = 800D;
    private static final Double STAGE_HEIGHT = 400D;
    private final EarthMapPane earthMapPane;

    /**
     * Construct.
     *
     */
    public EarthMapPaneTest() {
        Mappable circle = new CirclePlacemark(0.0, 0.0, 10, Paint.valueOf("green"));
        this.earthMapPane = new EarthMapPane(circle);
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (stage.getScene() == null) {
            Scene scene = new Scene(earthMapPane);
            stage.setScene(scene);
            stage.setTitle(earthMapPane.getClass().getSimpleName() + " Sample");
            stage.setWidth(STAGE_WIDTH);
            stage.setHeight(STAGE_HEIGHT);
            stage.show();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        javafx.application.Platform.exit();
        System.exit(0);
    }

    /**
     * Test the text fields for visibility.
     */
    @Test
    public void testMapVisible() {
        verifyThat("#earthMapPane", isVisible());
    }

    /**
     * Test of plotNode method, of class SimpleEarthMapPane.
     */
    @Test
    public void testPlotNode() {
        Mappable circle = new CirclePlacemark(-82.0, 25.0, 55, Paint.valueOf("red"));
        circle.getNode().setId("placemark");
        earthMapPane.plotNode(circle);
        verifyThat("#placemark", isVisible());
    }

}
