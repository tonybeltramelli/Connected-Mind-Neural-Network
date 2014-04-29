package com.tonybeltramelli.lab.display;

import com.tonybeltramelli.lab.Controller;
import com.tonybeltramelli.lab.brain.Brain;
import com.tonybeltramelli.lab.config.Config;
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
    private Controller _controller;
    private Text _progress;

    public Display(Controller controller, Stage stage)
    {
        super(stage, Config.WINDOW_TITLE, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT + 20);

        stage.getScene().setFill(Color.DARKGREY);

        _maze = new Maze();
        _spriteContainer.addChild(_maze);

        _leftView = new ImageSprite();
        _rightView = new ImageSprite();

        _displaySensorView(_leftView, Config.LEFT_SENSOR, 10, Config.SCREEN_HEIGHT + 12);
        _displaySensorView(_rightView, Config.RIGHT_SENSOR, 100, Config.SCREEN_HEIGHT + 12);

        _controller = controller;

        _progress = new Text();
        _setTextStyle(_progress);
        _progress.setTranslateX(Config.SCREEN_WIDTH / 2);
        _progress.setTranslateY(Config.SCREEN_HEIGHT + 12);
        _spriteContainer.addGraphics(_progress);
    }

    public void build(Brain brain)
    {
        _organism = null;
        _organism = new Organism(this, brain);
        _organism.setPosition(Config.START_POSITION_X, Config.START_POSITION_Y);

        _spriteContainer.addChild(_organism);
        _engine.add(_organism);
    }

    private void _displaySensorView(ImageSprite view, String text, double x, double y)
    {
        Text label = new Text();
        _setTextStyle(label);
        label.setText(text);
        label.setTranslateX(x);
        label.setTranslateY(y);
        _spriteContainer.addGraphics(label);

        view.setPosition(x + label.getLayoutBounds().getWidth() + 10, y - 6);
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
        _engine.remove(_organism);
        _spriteContainer.removeChild(_organism);

        _controller.saveFitnessScore(_organism.getFitnessScore());
    }

    private void _setTextStyle(Text text)
    {
        text.setFont(new Font(10));
        text.setFill(Color.BLACK);
    }

    public void setProgress(String progress)
    {
        _progress.setText(progress);
    }
}
