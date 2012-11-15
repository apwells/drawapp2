/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawapp2;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author alexwells
 */
public class Test {
    
    Test(AnchorPane group) {
        Ellipse test = new Ellipse(50,50,50,50);
        test.setFill(Color.RED);
        group.getChildren().add(test);
    }
    
}
