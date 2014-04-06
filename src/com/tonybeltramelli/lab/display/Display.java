package com.tonybeltramelli.lab.display;

import com.tonybeltramelli.lab.config.Config;
import com.tonybeltramelli.lab.element.Maze;
import com.tonybeltramelli.lab.element.Organism;
import com.tonybeltramelli.lib.graphics.ViewPort;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 01/04/2014
 */
public class Display extends ViewPort implements Environment
{
    private Maze _maze;
    private Organism _organism;

    public Display(Stage stage)
    {
        super(stage, Config.WINDOW_TITLE, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT + 20);

        _maze = new Maze();
        _spriteContainer.addChild(_maze);

        _organism = new Organism(this);
        _organism.setPosition(200, 360);
        _spriteContainer.addChild(_organism);
        _engine.add(_organism);
    }

    @Override
    public Image getSurroundingEnvironment(int x, int y, int width, int height)
    {
        return _maze.getSquare(x, y, width, height);
    }

    @Override
    public void displayOrganismVision(Image left, Image right)
    {
        ImageView leftView = new ImageView();
        leftView.setImage(left);
        leftView.setTranslateX(10);
        leftView.setTranslateY(Config.SCREEN_HEIGHT + 10);

        ImageView rightView = new ImageView();
        rightView.setImage(right);
        rightView.setTranslateX(100);
        rightView.setTranslateY(Config.SCREEN_HEIGHT + 10);

        _spriteContainer.addGraphics(leftView);
        _spriteContainer.addGraphics(rightView);
    }
}
