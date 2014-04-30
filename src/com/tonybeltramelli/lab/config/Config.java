package com.tonybeltramelli.lab.config;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 31/03/2014
 */
public class Config
{
    public static final String WINDOW_TITLE = "Connected Mind";
    public static final String LEFT_SENSOR = "Left sensor :";
    public static final String RIGHT_SENSOR = "Right sensor :";
    public static final String MAZE_IMAGE = "file:assets/road.png";
    public static final String DATE_MARKER = "{DATE}";
    public static final String LOG_OUTPUT = "logs/log-"+DATE_MARKER+".md";
    //
    public static final int SCREEN_WIDTH = 900;
    public static final int SCREEN_HEIGHT = 650;
    //
    public static final int START_POSITION_X = 200;
    public static final int START_POSITION_Y = 500;
    //
    public static final boolean USE_NEURAL_NETWORK = true;
    public static final boolean USE_KEYBOARD_CONTROL = false;
    public static final boolean USE_BIAS = false;
    //
    public static final int POPULATION_SIZE = 10;
    public static final int GENERATION_NUMBER = 20;
    //
    public static final InitializationStrategy INITIALIZATION_STRATEGY = InitializationStrategy.SEEDED;
    public static final ReproductionStrategy REPRODUCTION_STRATEGY = ReproductionStrategy.SEXUAL;

    public static enum InitializationStrategy
    {
        SEEDED,
        RANDOMIZED
    }

    public static enum ReproductionStrategy
    {
        SEXUAL,
        ASEXUAL
    }
}
