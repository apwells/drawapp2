package drawapp2;

//import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;



public class Parser
{
  private BufferedReader reader;
  private ShapeGenerator image;
  //private MainWindow frame;
  private Shape shape;
  private AnchorPane group;
  private Properties properties;
  
  public Parser(Reader reader, AnchorPane group, Properties properties)
  {
      this.properties = properties;
    this.reader = new BufferedReader(reader);
    this.image = new ShapeGenerator(group, properties);
    this.group = group;
    //this.frame = frame;
    
    // Added by alex
    
    
  }

  public void parse(boolean all)
  {
    try
    {
        //System.out.println("parser constructor going");
        //drawOval("DO 50 50 50 50\n");
      String line = reader.readLine();
      if (all) {    // If statement to allow stepping through
        while (line != null)
        {
          parseLine(line);
          line = reader.readLine();
        }
      } else if (line != null) {
          parseLine(line);
          //line = reader.readLine();
      }
    }
    catch (IOException e)
    {
      System.out.println("Parse failed.");
      return;
    }
    catch (ParseException e)
    {
      System.out.println("Parse Exception: " + e.getMessage());
      return;
    }
    System.out.println("Drawing completed");
  }

  private void parseLine(String line) throws ParseException
  {
    if (line.length() < 2) return;
    String command = line.substring(0, 2);
    if (command.equals("DL")) { drawLine(line.substring(2,line.length())); return; }
    if (command.equals("DR")) { drawRect(line.substring(2, line.length())); return; }
    if (command.equals("FR")) { fillRect(line.substring(2, line.length())); return; }
    if (command.equals("SC")) { setColour(line.substring(3, line.length())); return; }
    if (command.equals("DS")) { drawString(line.substring(3, line.length())); return; }
    if (command.equals("DA")) { drawArc(line.substring(2, line.length())); return; }
    if (command.equals("DO")) { drawOval(line.substring(2, line.length())); return; }
    if (command.equals("SG")) { setGradient(line.substring(2, line.length())); return; }
    if (command.equals("DI")) { drawImage(line.substring(2, line.length())); return; }
    if (command.equals("SD")) { setDimensions(line.substring(2, line.length())); return; }

    throw new ParseException("Unknown drawing command");
  }

  private void setDimensions(String args) throws ParseException {
      int width = 400;
      int height = 400;
      
    StringTokenizer tokenizer = new StringTokenizer(args);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    
    properties.setAppWidth(width);
    properties.setAppHeight(height);
      
  }
  
  private void drawLine(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    image.drawLine(x1,y1,x2,y2);
    
    
  }

  private void drawRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    
    image.drawRect(x1, y1, x2, y2);
  }

  private void fillRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    image.fillRect(x1, y1, x2, y2);
  }

  private void drawArc(String args) throws ParseException
  {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    int startAngle = 0;
    int arcAngle = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    startAngle = getInteger(tokenizer);
    arcAngle = getInteger(tokenizer);
    image.drawArc(x, y, width, height, startAngle, arcAngle);
  }

  private void drawOval(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int width = 0;
    int height = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    image.drawOval(x1, y1, width, height);
//    
//    Shape shape2 = new Ellipse(x1 ,y1 ,width,height);
//    shape2.setFill(Color.YELLOW);
//    group.getChildren().add(shape2);
    
    System.out.println("draw oval called in parser");
//    shape = new Ellipse(x1,y1,width,height);
//    shape.setFill(Color.BLUE);
    
  }

  private void drawString(String args) throws ParseException
  {
    int x = 0;
    int y = 0 ;
    String s = "";
    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    int position = args.indexOf("@");
    if (position == -1) throw new ParseException("DrawString string is missing");
    s = args.substring(position+1,args.length());
    image.drawString(x,y,s);
  }
  
  private void drawImage(String args) throws ParseException
  {
      int x = 0;
      int y = 0;
      int width = 0;
      int height = 0;
      String file = "";
      StringTokenizer tokenizer = new StringTokenizer(args);
      x = getInteger(tokenizer);
      y = getInteger(tokenizer);
      width = getInteger(tokenizer);
      height = getInteger(tokenizer);
      file = tokenizer.nextToken();
    //int position = args.indexOf("@");
    //if (position == -1) throw new ParseException("DrawImage file string is missing");
    image.drawImage(x, y, width, height, file);
  }

  private void setColour(String colourName) throws ParseException
  {
    try {
    image.setColour(returnColour(colourName));
    } catch (ParseException e) {
    System.err.println("Caught illegal colour name in setColour");
    }
  }
  
    private void setGradient(String args) throws ParseException
  {
    String colour1 = "red";
    String colour2 = "blue";
      
    StringTokenizer tokenizer = new StringTokenizer(args);
    colour1 = tokenizer.nextToken();
    colour2 = tokenizer.nextToken();
    
    try {
    image.setGradient(returnColour(colour1), returnColour(colour2));
    } catch (ParseException e) {
    System.err.println("Caught illegal colour name in setGradient");
    }
  }
    
    private Color returnColour (String colourName) throws ParseException 
    {
        Color colour;
    if (colourName.equals("black")) { colour = Color.BLACK; return colour;}
    if (colourName.equals("blue")) { colour = Color.BLUE; return colour;}
    if (colourName.equals("cyan")) { colour = Color.CYAN; return colour;}
    if (colourName.equals("darkgray")) { colour = Color.DARKGRAY; return colour;}
    if (colourName.equals("gray")) { colour = Color.GRAY; return colour;}
    if (colourName.equals("green")) { colour = Color.GREEN; return colour;}
    if (colourName.equals("lightgray")) { colour = Color.LIGHTGRAY; return colour;}
    if (colourName.equals("magenta")) { colour = Color.MAGENTA; return colour;}
    if (colourName.equals("orange")) { colour = Color.ORANGE; return colour;}
    if (colourName.equals("pink")) { colour = Color.PINK; return colour;}
    if (colourName.equals("red")) { colour = Color.RED; return colour;}
    if (colourName.equals("white")) { colour = Color.WHITE; return colour;}
    if (colourName.equals("yellow")) { colour = Color.YELLOW; return colour;}
    
    throw new ParseException("Invalid colour name");
    }

  private int getInteger(StringTokenizer tokenizer) throws ParseException
  {
    if (tokenizer.hasMoreTokens())
      return Integer.parseInt(tokenizer.nextToken());
    else
      throw new ParseException("Missing Integer value");
  }
}
