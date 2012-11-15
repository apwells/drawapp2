package drawapp2;

//import javax.swing.SwingUtilities;
//import java.awt.Color;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application
{
  public static void main(String[] args)
  {
//    final MainWindow main = new MainWindow();
//
//    SwingUtilities.invokeLater(
//    new Runnable()
//    {
//      public void run()
//      {
//        ImagePanel imagePanel = main.getImagePanel();
//        Reader reader = new InputStreamReader(System.in);
//        Parser parser = new Parser(reader,imagePanel,main);
//        parser.parse();
//        imagePanel.repaint(); 
//      }
//    }
//  );
      
      launch(args);
      

  }
  
  @Override
  public void start(Stage primaryStage) {
        primaryStage.setTitle("DrawApp");
        primaryStage.setResizable(false);
//        primaryStage.setHeight(400);
//        primaryStage.setWidth(400);

        Button btn = new Button();
        btn.setText("Say 'Hello Worldddd'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello Worldd!");
            }
        });
        
//        Group root = new Group();
//        Scene s;
//        s = new Scene(root, 20, 20, Color.RED);
//        
        //Rectangle r = new Rectangle(25,25,250,250);
        //r.setFill(Color.BLUE);
//
//        root.getChildren().add(r);
        
        
        
        //final Canvas canvas = new Canvas(500,300);
        //GraphicsContext gc = canvas.getGraphicsContext2D();

        //gc.setFill(Color.BLUE);
        //gc.fillRect(75,75,100,100);
        System.out.println("test");
        
        AnchorPane topgroup = new AnchorPane();

        
        Rectangle clip = new Rectangle(500,440);    // We leave 20 pixels for the bar at the bottom
        topgroup.setClip(clip);
        
        topgroup.toBack();
        //topgroup.getChildren().add(canvas);
        
        
        
        BorderPane border = new BorderPane();
        HBox statusbar = addHBox();
        statusbar.setMinHeight(60);
        border.setBottom(statusbar);
        
        addStackPane(statusbar); 
        
        
        border.setTop(topgroup);
        
        ShapeGenerator shapes = new ShapeGenerator(topgroup);
        Reader reader = new InputStreamReader(System.in);
        Parser parser = new Parser(reader, topgroup);
        parser.parse();
        System.out.println("parser instantiated");
        
        
        
        //Test test = new Test(topgroup);
        //Parser parser = new Parser(reader,imagePanel,main);
        //parser.parse();

//root.getChildren().add(canvas);
        
        //Reader reader = new InputStreamReader(System.in);
        //Parser parser = new Parser(reader,imagePanel,main);
        //parser.parse();
        //imagePanel.repaint(); 
        
        border.toFront();
        StackPane root = new StackPane();
        //root.getChildren().add(btn);
        primaryStage.setScene(new Scene(border, 500, 500));
        
        primaryStage.show();
    }
  
  public HBox addHBox() {
    HBox hbox = new HBox();
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setSpacing(10);
    hbox.setStyle("-fx-background-color: #336699;");

    Button buttonCurrent = new Button("Current");
    buttonCurrent.setPrefSize(100, 20);

    Button buttonProjected = new Button("Projected");
    buttonProjected.setPrefSize(100, 20);
    hbox.getChildren().addAll(buttonCurrent, buttonProjected);

    return hbox;
}
  
      private void addStackPane(HBox hb) {

        StackPane stack = new StackPane();
        TextArea text = new TextArea();
        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
            new Stop[]{
            new Stop(0,Color.web("#4977A3")),
            new Stop(0.5, Color.web("#B0C6DA")),
            new Stop(1,Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);
        
        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0")); 
        
        stack.getChildren().addAll(helpIcon, helpText);
        stack.setAlignment(Pos.CENTER_RIGHT);
        // Add offset to right for question mark to compensate for RIGHT 
        // alignment of all nodes
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));
        
        hb.getChildren().add(stack);
        HBox.setHgrow(stack, Priority.ALWAYS);
                
    }
  
  

}
