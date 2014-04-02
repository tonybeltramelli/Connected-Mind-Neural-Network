package com.tonybeltramelli.lib.graphics;

import com.tonybeltramelli.lib.engine.Engine;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - 30/03/2014
 */
public abstract class ViewPort
{
    protected Sprite _spriteContainer;
    protected Engine _engine;

    public ViewPort(Stage stage, String title, int width, int height)
    {
        this(stage, title, width, height, 60);
    }

    public ViewPort(Stage stage, String title, int width, int height, int fps)
    {
        _spriteContainer = new Sprite();

        Scene scene = new Scene(_spriteContainer.getGroup(), width, height, Color.BLACK);

        stage.setScene(scene);
        stage.setTitle(title);
        stage.sizeToScene();
        stage.show();

        _engine = new Engine(fps);
        _engine.run();
    }
}
