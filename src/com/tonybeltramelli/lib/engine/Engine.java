package com.tonybeltramelli.lib.engine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - 30/03/2014
 */
public class Engine
{
    private boolean _isRunning = false;
    private boolean _isPaused = false;
    private Timeline _loop;
    private int _fps;
    private List<Updatable> _updatables;

    public Engine(int fps)
    {
        _fps = fps;
        _updatables = new ArrayList<Updatable>();
    }

    public void add(Updatable updatable)
    {
        _updatables.add(updatable);
    }

    public void run()
    {
        _isRunning = true;

        final Duration oneFrameAmt = Duration.millis(1000 / _fps);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
                new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        _update();
                    }
                }
        );

        _loop = TimelineBuilder.create().cycleCount(Animation.INDEFINITE).keyFrames(oneFrame).build();

        play();
    }

    private void _update()
    {
        for (int i = 0; i < _updatables.size(); i++)
        {
            _updatables.get(i).update();
        }
    }

    public void pause()
    {
        _isPaused = true;
        _loop.pause();
    }

    public void play()
    {
        _isPaused = false;
        _loop.play();
    }

    public void stop()
    {
        _isRunning = false;
        _loop.stop();
    }

    public boolean isRunning()
    {
        return _isRunning;
    }

    public boolean isPaused()
    {
        return _isPaused;
    }
}
