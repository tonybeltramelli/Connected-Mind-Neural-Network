package com.tonybeltramelli.lab.element;

import com.tonybeltramelli.lab.config.Config;
import com.tonybeltramelli.lib.graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


/**
 * @author Tony Beltramelli www.tonybeltramelli.com - 31/03/2014
 */
public class Maze extends Sprite
{
    private int _width;
    private int _height;
    private PixelReader _pixels;

    public Maze()
    {
        Image image = new Image(Config.MAZE_IMAGE, false);

        ImageView imageView = new ImageView();
        imageView.setImage(image);

        addGraphics(imageView);

        _pixels = image.getPixelReader();

        _width = (int) image.getWidth();
        _height = (int) image.getHeight();
    }

    public Image getSquare(int xOrigin, int yOrigin, int width, int height)
    {
        WritableImage image = new WritableImage(width, height);
        PixelWriter writer = image.getPixelWriter();

        for (int x = xOrigin; x < xOrigin + width; x++)
        {
            for (int y = yOrigin; y < yOrigin + height; y++)
            {
                Color color = _pixels.getColor(x, y);
                writer.setColor(x - xOrigin, y - yOrigin, color);
            }
        }

        return image;
    }
}
