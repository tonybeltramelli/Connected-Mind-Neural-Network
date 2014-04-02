package com.tonybeltramelli.lab.display;

import javafx.scene.image.Image;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - 01/04/2014
 */
public interface Environment
{
    public Image getSurroundingEnvironment(int x, int y, int width, int height);
}
