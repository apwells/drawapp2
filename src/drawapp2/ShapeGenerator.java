package drawapp2;


import javax.swing.JPanel;
//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Text;





public class ShapeGenerator extends JPanel
{
  private BufferedImage image;
  private Graphics graphics;
  private AnchorPane group;
  private Color color = Color.BLACK;
  
  Stop[] stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.RED)};
  private LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
  private boolean gradientFill = false;
  
  private Shape shape;
  
  private int appWidth;
  private int appHeight;
  
  private Properties properties;

  public ShapeGenerator(AnchorPane group, Properties properties)
  {
      this.group = group;
      this.properties = properties;
      this.appWidth = this.properties.getAppWidth();
      this.appHeight = this.properties.getAppHeight();
      
  }

  private void setImageSize(int width, int height)
  {
      properties.setAppWidth(width);
      properties.setAppHeight(height);
            
    //image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    //graphics = image.getGraphics();
    //clear(Color.white);
    //setPreferredSize(new Dimension(width, height));
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    //g.setColor(Color.gray);
    g.fillRect(0, 0, image.getWidth(), image.getHeight());
    g.drawImage(image,0,0,this);
  }

  public void setBackgroundColour(Color colour)
  {
    Rectangle shape = new Rectangle (0,0, appWidth, appHeight);
    shape.setFill(color);
    group.getChildren().add(shape);
  }
  
  public void setGradient(Color colour1, Color colour2) {
    Stop[] stops = new Stop[] { new Stop(0, colour1), new Stop(1, colour2)};
    lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
    gradientFill = true; // The next filled shape will be with this gradient
  }
  
  public void drawImage(int x, int y, int width, int height, String fileName) throws IllegalArgumentException
  {
      ImageView iv = new ImageView();
      try {
      Image image = new Image(fileName);
      iv.setImage(image);
      } catch (IllegalArgumentException e) {
          System.out.println("Invalid URL for image!"+fileName);
      }
      
      iv.setX(x);
      iv.setY(y);
      iv.setFitWidth(width);
      iv.setFitHeight(height);
      iv.setPreserveRatio(false);
      iv.setSmooth(true); // Not sure what this does. Anti aliases perhaps?
      
      group.getChildren().add(iv);
  }

  public void clear(Color colour)
  {
    setBackgroundColour(colour);
  }

  public void setColour(Color colour)
  {
    this.color = colour;
    gradientFill = false; // The next filled shape will be with this colour.
  }

  public void drawLine(int x1, int y1, int x2, int y2)
  {
    //graphics.drawLine(x1, y1, x2, y2);
    shape = new Line(x1,y1,x2,y2);
    shape.setStroke(color);
    group.getChildren().add(shape);
  }

  public void drawRect(int x1, int y1, int x2, int y2)
  {
    //graphics.drawRect(x1, y1, x2, y2);
    shape = new Rectangle(x1, y1, x2, y2);
    shape.setStroke(Color.BLACK);
    shape.setFill(Color.TRANSPARENT);
    //shape.setStrokeWidth(2);
    group.getChildren().add(shape);
    System.out.println("Rect drawn");
  }

  public void fillRect(int x1, int y1, int x2, int y2)
  {
    shape = new Rectangle(x1, y1, x2, y2);
    if (gradientFill){
        shape.setFill(this.lg1);
    } else {
        shape.setFill(this.color);
    }
    group.getChildren().add(shape);
    System.out.println("FillRect drawn");
  }

  public void drawString(int x, int y, String s)
  {
    Text text = new Text(x, y, s);
    group.getChildren().add(text);
    //graphics.drawString(s,x,y);
  }

  public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
  {
    //graphics.drawArc(x,y,width,height,startAngle,arcAngle);
    shape = new Arc(x, y, width, height, startAngle, arcAngle);
    shape.setStroke(this.color);
    shape.setFill(color.TRANSPARENT);
    group.getChildren().add(shape);
  }

  public void drawOval(int x, int y, int width, int height)
  {
    //graphics.drawOval(x,y,width,height);
    shape = new Ellipse(x + (width/2), y + (width/2), width/2, height/2);
    shape.setStroke(color);
    shape.setFill(color.TRANSPARENT);
    group.getChildren().add(shape);
    System.out.println("Oval Drawn");
  }
}
