package com.tonybeltramelli.lab.element;

import com.tonybeltramelli.lab.display.Environment;
import com.tonybeltramelli.lib.engine.Updatable;
import com.tonybeltramelli.lib.graphics.Sprite;
import com.tonybeltramelli.lib.util.UMath;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.Point;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - 30/03/2014
 */
public class Organism extends Sprite implements Updatable
{
    private final int _SIZE = 20;
    private final int _SENSOR_SIZE = _SIZE / 4;
    private Environment _environment;
    private Circle _leftSensor;
    private Circle _rightSensor;

    public Organism(Environment environment)
    {
        _environment = environment;

        Color bodyColor = Color.web("0x4e4e4e");
        Color sensorColor = Color.web("0xffb400");

        Rectangle rectangle = new Rectangle(_SIZE, _SIZE, bodyColor);

        _leftSensor = new Circle(0, 0, _SENSOR_SIZE, sensorColor);
        _rightSensor = new Circle(_SIZE, 0, _SENSOR_SIZE, sensorColor);

        addGraphics(rectangle);
        addGraphics(_leftSensor);
        addGraphics(_rightSensor);
    }

    @Override
    public void update()
    {
        Point leftPos = _getSensorPosition(_leftSensor);
        Point rightPos = _getSensorPosition(_rightSensor);

        Image leftSensorData = _environment.getSurroundingEnvironment((int) _x + leftPos.x - _SENSOR_SIZE / 2, (int) _y + leftPos.y, _SENSOR_SIZE, _SENSOR_SIZE);
        Image rightSensorData = _environment.getSurroundingEnvironment((int) _y + rightPos.x - _SENSOR_SIZE / 2, (int) _y + rightPos.y, _SENSOR_SIZE, _SENSOR_SIZE);

        int inputLeft = _checkEnvironment(leftSensorData);
        int inputRight = _checkEnvironment(rightSensorData);

        if(inputLeft == 0 && inputRight == 0)
        {
            inputLeft = 1;
            inputRight = 1;
        }

        _move(inputLeft, inputRight);
    }

    private Point _getSensorPosition(Shape sensor)
    {
        double sensorHyp = Math.sqrt(Math.pow(sensor.getTranslateX() - _SIZE / 2, 2) + Math.pow(sensor.getTranslateY() - _SIZE / 2, 2));
        int xDistance = (int) (Math.sin(UMath.degToRad(_angle)) * sensorHyp);
        int yDistance = (int) (Math.cos(UMath.degToRad(_angle)) * sensorHyp) * -1;

        return new Point(xDistance, yDistance);
    }

    private void _move(int leftWheel, int rightWheel)
    {
        if (rightWheel == 1 && leftWheel == 1)
        {
            _moveForward();
        } else if (rightWheel == 1 && leftWheel == 0)
        {
            _turnLeft();
        } else if (rightWheel == 0 && leftWheel == 1)
        {
            _turnRight();
        }
    }

    private void _moveForward()
    {
        double xMovement = Math.sin(UMath.degToRad(_angle));
        double yMovement = Math.cos(UMath.degToRad(_angle)) * -1;

        setPosition(xMovement, yMovement);
    }

    private void _turnRight()
    {
        _setRotation(1);
    }

    private void _turnLeft()
    {
        _setRotation(-1);
    }

    private int _checkEnvironment(Image image)
    {
        PixelReader reader = image.getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        Color color;

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                color = reader.getColor(x, y);

                if(color.getRed() == 0.0 && color.getGreen() == 0.0 && color.getGreen() == 0.0)
                {
                    return 1;
                }
            }
        }

        return 0;
    }
}
