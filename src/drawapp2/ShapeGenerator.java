package drawapp2;


import javax.swing.JPanel;
//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;





public class ShapeGenerator extends JPanel
{
  private BufferedImage image;
  private Graphics graphics;
  private AnchorPane group;
  private Color color = Color.BLACK;
  private Shape shape;
  
  private int appWidth;
  private int appHeight;

  public ShapeGenerator(AnchorPane group, Properties properties)
  {
      this.group = group;
      this.appWidth = properties.getAppWidth();
      this.appHeight = properties.getAppHeight();
  }

  private void setImageSize(int width, int height)
  {
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    graphics = image.getGraphics();
    //clear(Color.white);
    setPreferredSize(new Dimension(width, height));
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
    //graphics.setColor(colour);
    Rectangle background = new Rectangle (0,0, appWidth, appHeight);
    background.setFill(color);
    //graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
    //graphics.setColor(Color.black);
  }

  public void clear(Color colour)
  {
    setBackgroundColour(colour);
  }

  public void setColour(Color colour)
  {
    this.color = colour;
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
    shape.setFill(this.color);
    group.getChildren().add(shape);
    System.out.println("FillRect drawn");
  }

  public void drawString(int x, int y, String s)
  {
    graphics.drawString(s,x,y);
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
