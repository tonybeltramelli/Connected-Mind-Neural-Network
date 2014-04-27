package com.tonybeltramelli.lab.display;

import com.tonybeltramelli.lab.brain.Brain;
import com.tonybeltramelli.lab.config.Config;
import com.tonybeltramelli.lib.engine.Updatable;
import com.tonybeltramelli.lib.graphics.Sprite;
import com.tonybeltramelli.lib.util.UMath;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.Point;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 30/03/2014
 */
public class Organism extends Sprite implements Updatable
{
    private final int _SIZE = 30;
    private final int _SENSOR_SIZE = _SIZE / 4;
    private final int _IMMOBILITY_STEP_THRESHOLD = 100;
    private final int _IMMOBILITY_DISTANCE_THRESHOLD = 5;
    //
    private Environment _environment;
    private Circle _leftSensor;
    private Circle _rightSensor;
    private Circle _centerSensor;
    private Brain _brain;
    private int _lifeTime = 0;
    private int _stepCounter = 0;
    private Point _lastPosition;

    public Organism(Environment environment, Brain brain)
    {
        _environment = environment;

        Color bodyColor = Color.web("0x4e4e4e"); //Color.rgb(78, 78, 78, 0.5);
        Color sensorColor = Color.web("0xffb400"); //Color.rgb(255, 180, 0, 0.5)

        Rectangle rectangle = new Rectangle(_SIZE, _SIZE, bodyColor);

        _leftSensor = new Circle(0, 0, _SENSOR_SIZE, sensorColor);
        _leftSensor.setTranslateX(-_SENSOR_SIZE);
        _leftSensor.setTranslateY(-_SENSOR_SIZE);

        _rightSensor = new Circle(0, 0, _SENSOR_SIZE, sensorColor);
        _rightSensor.setTranslateX(_SIZE + _SENSOR_SIZE);
        _rightSensor.setTranslateY(-_SENSOR_SIZE);

        _centerSensor = new Circle(0, 0, _SENSOR_SIZE, sensorColor);
        _centerSensor.setTranslateX(_SIZE / 2);
        _centerSensor.setTranslateY(_SIZE / 2);

        addGraphics(rectangle);
        addGraphics(_leftSensor);
        addGraphics(_rightSensor);
        addGraphics(_centerSensor);

        _brain = brain;

        _lastPosition = getPosition();
        //_displayVisionZones();

        if(Config.USE_KEYBOARD_CONTROL) _handleEvents();
    }

    private void _handleEvents()
    {
        getGroup().setFocusTraversable(true);
        getGroup().setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent key)
            {
                switch(key.getCode())
                {
                    case UP:
                        _move(1, 1);
                        break;
                    case DOWN:
                        _move(0, 0);
                        break;
                    case LEFT:
                        _move(1, 0);
                        break;
                    case RIGHT:
                        _move(0, 1);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void update()
    {
        _lifeTime++;

        _checkCollision();

        Image dataLeft = _getDataFromSensor(_leftSensor);
        Image dataRight = _getDataFromSensor(_rightSensor);

        _environment.displayOrganismVision(dataLeft, dataRight);

        if(Config.USE_KEYBOARD_CONTROL) return;

        int inputLeft = _checkEnvironment(dataLeft);
        int inputRight = _checkEnvironment(dataRight);

        if(Config.USE_NEURAL_NETWORK)
        {
            double[] outputs = _brain.run(new double[]{(double) inputLeft, (double) inputRight});
            _move((int) outputs[0], (int) outputs[1]);
        } else
        {
            _move(inputLeft, inputRight);
        }
    }

    private void _checkCollision()
    {
        Image dataCenter = _getDataFromSensor(_centerSensor);

        if(_checkEnvironment(dataCenter) == 0)
        {
            _environment.organismHasCollided();
        }

        _stepCounter++;

        if(_stepCounter == _IMMOBILITY_STEP_THRESHOLD)
        {
            _stepCounter = 0;

            Point currentPosition = new Point((int) _x, (int) _y);

            if((_lastPosition.x >= currentPosition.x - _IMMOBILITY_DISTANCE_THRESHOLD
                    && _lastPosition.x <= currentPosition.x + _IMMOBILITY_DISTANCE_THRESHOLD)
                    && (_lastPosition.y >= currentPosition.y - _IMMOBILITY_DISTANCE_THRESHOLD
                    && _lastPosition.y <= currentPosition.y + _IMMOBILITY_DISTANCE_THRESHOLD))
            {
                _environment.organismHasCollided();
            } else
            {
                _lastPosition = currentPosition;
            }
        }
    }

    private Image _getDataFromSensor(Shape sensor)
    {
        Point position = _getSensorPosition(sensor);
        Image sensorData = _environment.getSurroundingEnvironment((int) _x + position.x, (int) _y + position.y, _SENSOR_SIZE, _SENSOR_SIZE);

        return sensorData;
    }

    private void _displayVisionZones()
    {
        Point leftPos = _getSensorPosition(_leftSensor);
        Point rightPos = _getSensorPosition(_rightSensor);

        Rectangle leftZone = new Rectangle(_SENSOR_SIZE, _SENSOR_SIZE, Color.BLACK);
        Rectangle rightZone = new Rectangle(_SENSOR_SIZE, _SENSOR_SIZE, Color.BLACK);

        leftZone.setTranslateX(_x + leftPos.x);
        leftZone.setTranslateY(_y + leftPos.y);

        rightZone.setTranslateX(_x + rightPos.x);
        rightZone.setTranslateY(_y + rightPos.y);

        addGraphics(leftZone);
        addGraphics(rightZone);
    }

    private Point _getSensorPosition(Shape sensor)
    {
        Point centerPoint = new Point(_SIZE / 2, _SIZE / 2);

        double xDec = sensor.getTranslateX() - centerPoint.x;
        double yDec = sensor.getTranslateY() - centerPoint.y;
        double sensorHyp = Math.sqrt(Math.pow(xDec, 2) + Math.pow(yDec, 2));

        int ang = (xDec < 0 ? -1 : 1) * 45;

        int x = (int) (Math.sin(UMath.degToRad(_angle + ang)) * sensorHyp);
        int y = (int) (Math.cos(UMath.degToRad(_angle + ang)) * sensorHyp) * -1;

        return new Point((x + _SIZE / 2 - _SENSOR_SIZE / 2), (y + _SIZE / 2 - _SENSOR_SIZE / 2));
    }

    private void _move(int leftWheel, int rightWheel)
    {
        if(rightWheel == 1 && leftWheel == 1)
        {
            _moveForward();
        } else if(rightWheel == 0 && leftWheel == 1)
        {
            _turnLeft();
        } else if(rightWheel == 1 && leftWheel == 0)
        {
            _turnRight();
        } else if(rightWheel == 0 && leftWheel == 0)
        {
            _moveBackward();
        }
    }

    private void _moveForward()
    {
        setPosition(_getXMovement(), _getYMovement());
    }

    private void _moveBackward()
    {
        setPosition(_getXMovement() * -1, _getYMovement() * -1);
    }

    private void _turnRight()
    {
        _setRotation(1);
    }

    private void _turnLeft()
    {
        _setRotation(-1);
    }

    private double _getXMovement()
    {
        return Math.sin(UMath.degToRad(_angle));
    }

    private double _getYMovement()
    {
        return Math.cos(UMath.degToRad(_angle)) * -1;
    }

    private int _checkEnvironment(Image image)
    {
        PixelReader reader = image.getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        Color color;

        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                color = reader.getColor(x, y);

                if(color.getRed() == 0.0 && color.getGreen() == 0.0 && color.getGreen() == 0.0)
                {
                    return 0;
                }
            }
        }

        return 1;
    }

    public int getFitnessScore()
    {
        double xDec = _x - Config.START_POSITION_X;
        double yDec = _y - Config.START_POSITION_Y;
        double travel = Math.sqrt(Math.pow(xDec, 2) + Math.pow(yDec, 2));

        return (int) travel + _lifeTime;
    }
}
