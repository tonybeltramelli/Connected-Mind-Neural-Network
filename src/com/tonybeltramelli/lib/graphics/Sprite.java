package com.tonybeltramelli.lib.graphics;

import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 31/03/2014
 */
public class Sprite
{
    private Group _group;
    private List<Sprite> _sprites;
    //
    protected double _x = 0;
    protected double _y = 0;
    protected int _angle = 0;

    public Sprite()
    {
        _sprites = new ArrayList<Sprite>();

        _group = new Group();
        _group.setAutoSizeChildren(false);

        _cache(_group);
    }

    private void _cache(Node node)
    {
        node.setCache(true);
        node.setCacheHint(CacheHint.SPEED);
        node.setManaged(false);
    }

    public void addGraphics(Node graphics)
    {
        _cache(graphics);

        _group.getChildren().add(graphics);
    }

    public void addChild(Sprite sprite)
    {
        _sprites.add(sprite);

        _cache(sprite.getGroup());

        _group.getChildren().add(sprite.getGroup());
    }

    public void addChildAt(Sprite sprite, int index)
    {
        _sprites.add(index, sprite);

        _cache(sprite.getGroup());

        _group.getChildren().add(index, sprite.getGroup());
    }

    public void addChilds(Sprite... sprites)
    {
        for (int i = 0; i < sprites.length; i++)
        {
            addChild(sprites[i]);
        }
    }

    public void removeChild(Sprite sprite)
    {
        _sprites.remove(sprite);
        _group.getChildren().remove(sprite.getGroup());
    }

    public void removeChildAt(int index)
    {
        _sprites.remove(index);
        _group.getChildren().remove(index);
    }

    public void removeChilds(Sprite... sprites)
    {
        for (int i = 0; i < sprites.length; i++)
        {
            removeChild(sprites[i]);
        }
    }

    public Sprite getChildAt(int index)
    {
        return _sprites.get(index);
    }

    public void clear()
    {
        for (int i = 0; i < _sprites.size(); i++)
        {
            removeChild(_sprites.get(i));
        }

        _sprites.clear();
    }

    public void setPosition(double x, double y)
    {
        _x = _x + x;
        _y = _y + y;

        _group.setTranslateX(_x);
        _group.setTranslateY(_y);
    }

    protected void _setRotation(int angle)
    {
        _angle = _angle + angle;

        _group.setRotate(_angle);

        _angle = _angle == 360 || _angle == -360 ? 0 : _angle;
    }

    public Group getGroup()
    {
        return _group;
    }

    public boolean isVisible()
    {
        return _group.isVisible();
    }

    public double getX()
    {
        return _x;
    }

    public double getY()
    {
        return _y;
    }

    public int getAngle()
    {
        return _angle;
    }
}
