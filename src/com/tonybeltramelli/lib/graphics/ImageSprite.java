package com.tonybeltramelli.lib.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 15/04/2014
 */
public class ImageSprite extends Sprite
{
    private ImageView _imageView;

    public ImageSprite()
    {
        this(null, 0.0, 0.0);
    }

    public ImageSprite(Image image)
    {
        this(image, 0.0, 0.0);
    }

    public ImageSprite(Image image, double x, double y)
    {
        super();

        _imageView = new ImageView();
        addGraphics(_imageView);

        replaceContent(image);

        setPosition(x, y);
    }

    public void replaceContent(Image image)
    {
        if(image == null) return;

        _imageView.setImage(image);
    }
}
