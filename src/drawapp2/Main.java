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
import javafx.scene.layout.VBox;
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
    
    private int appWidth;
    private int appHeight;
    
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
        
        Properties properties = new Properties();
        appWidth = properties.getAppWidth();
        appHeight = properties.getAppHeight();

        AnchorPane topgroup = new AnchorPane();
        
        int topWidth = appHeight - 140;
        
        topgroup.setMinWidth(appWidth);
        topgroup.setMinHeight(topWidth);
        
        
        // Let's try and remove these below. I don't know if i need the clip if done properly.
        Rectangle clip = new Rectangle(0,0, appWidth,topWidth);    // We leave 20 pixels for the bar at the bottom
        topgroup.setClip(clip);

        BorderPane border = new BorderPane();
        VBox vertBox = new VBox(0); // spacing = 8
        HBox buttonBox = new HBox(5);
        // Text area setting up
        TextArea textarea = new TextArea("test");
        textarea.setMinHeight(100);
        // Setup the buttons
        Button next = new Button("Next step");
        Button finish = new Button("Finish draw");
        Button close = new Button("Close");
        //close.setMinHeight(30);
        buttonBox.getChildren().addAll(next,finish,close);
        buttonBox.alignmentProperty().set(Pos.CENTER);
        vertBox.getChildren().addAll(topgroup, textarea, buttonBox);

        
        // UNCOMMENT THESE! Or comment if you want to run not through terminal.
        // ShapeGenerator shapes = new ShapeGenerator(topgroup, appHeight, appWidth); // This isn't used. used in parser.
        Reader reader = new InputStreamReader(System.in);
        final Parser parser = new Parser(reader, topgroup, properties);
        // parser.parse(true); // This simply parses the whole drawing. Disable this for step through
        System.out.println("parser instantiated");
        
        // TODO : Delete me. this is just a test for imagedraw
        //ShapeGenerator test = new ShapeGenerator(topgroup, properties);
        //test.drawImage(50,50,100,200,"image.jpeg");
        
        //test.setGradient(Color.AQUA, Color.GREEN);
        //test.fillRect(150, 150, 50, 50);
        
        // Give the buttons actions
        next.setOnAction(new EventHandler<ActionEvent>() {
        @Override 
        public void handle(ActionEvent e) {
            parser.parse(false);
            }
        });
        finish.setOnAction(new EventHandler<ActionEvent>() {
        @Override 
        public void handle(ActionEvent e) {
            parser.parse(true);
            }
        });
        close.setOnAction(new EventHandler<ActionEvent>() {
        @Override 
        public void handle(ActionEvent e) {
            System.exit(0);
            }
        });
        
        border.toFront();
        textarea.setText("Drawing finished");
        
        primaryStage.setScene(new Scene(vertBox, appWidth, appHeight));
        
        primaryStage.show();
    }
  
  public HBox addHBox() {
    HBox hbox = new HBox();
    
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setSpacing(10);
    hbox.setStyle("-fx-background-color: #336699;");

    TextArea textarea = new TextArea("test");

    
    Button buttonCurrent = new Button("Current");
    buttonCurrent.setPrefSize(100, 20);

    Button buttonProjected = new Button("Projected");
    buttonProjected.setPrefSize(100, 20);
    hbox.getChildren().addAll(textarea, buttonCurrent );

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
