package com.tonybeltramelli.lab.display;

import com.tonybeltramelli.lab.config.Config;
import com.tonybeltramelli.lab.element.Maze;
import com.tonybeltramelli.lab.element.Organism;
import com.tonybeltramelli.lab.element.brain.Brain;
import com.tonybeltramelli.lib.graphics.ImageSprite;
import com.tonybeltramelli.lib.graphics.ViewPort;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 01/04/2014
 */
public class Display extends ViewPort implements Environment
{
    private Maze _maze;
    private Organism _organism;
    private ImageSprite _leftView;
    private ImageSprite _rightView;
    private Brain _brain;

    public Display(Stage stage)
    {
        super(stage, Config.WINDOW_TITLE, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT + 20);

        stage.getScene().setFill(Color.DARKGREY);

        _maze = new Maze();
        _spriteContainer.addChild(_maze);

        _leftView = new ImageSprite();
        _rightView = new ImageSprite();

        _brain = new Brain();

        _displaySensorView(_leftView, Config.LEFT_SENSOR, 10, Config.SCREEN_HEIGHT + 6);
        _displaySensorView(_rightView, Config.RIGHT_SENSOR, 100, Config.SCREEN_HEIGHT + 6);

        organismHasCollided();
    }

    private void _displaySensorView(ImageSprite view, String text, double x, double y)
    {
        Text label = new Text();
        label.setFont(new Font(10));
        label.setText(text);
        label.setFill(Color.WHITE);
        label.setTranslateX(x);
        label.setTranslateY(y + 6);
        _spriteContainer.addGraphics(label);

        view.setPosition(x + label.getLayoutBounds().getWidth() + 10, y);
        _spriteContainer.addChild(view);
    }

    @Override
    public Image getSurroundingEnvironment(int x, int y, int width, int height)
    {
        return _maze.getSquare(x, y, width, height);
    }

    @Override
    public void displayOrganismVision(Image left, Image right)
    {
        _leftView.replaceContent(left);
        _rightView.replaceContent(right);
    }

    @Override
    public void organismHasCollided()
    {
        if(_organism != null)
        {
            _organism.getScore();

            _engine.remove(_organism);
            _spriteContainer.removeChild(_organism);

            _organism = null;
        }

        _organism = new Organism(this, _brain);
        _organism.setPosition(Config.START_POSITION_X, Config.START_POSITION_Y);

        _spriteContainer.addChild(_organism);
        _engine.add(_organism);
    }
}
