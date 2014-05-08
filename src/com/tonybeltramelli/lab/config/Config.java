package com.tonybeltramelli.lab.config;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 31/03/2014
 */
public class Config
{
    public static final String WINDOW_TITLE = "Connected Mind";
    public static final String LEFT_SENSOR = "Left sensor :";
    public static final String RIGHT_SENSOR = "Right sensor :";
    public static final String MAZE_IMAGE = "file:assets/road1.png";
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
    public static final boolean USE_MERGED_NEURAL_NETWORK = false;
    public static final boolean USE_SUPER_NEURAL_NETWORK = false;
    //
    public static int populationSize = 10;
    public static int generationNumber = 20;
    //
    public static InitializationStrategy initializationStrategy = InitializationStrategy.SEEDED;
    public static ReproductionStrategy reproductionStrategy = ReproductionStrategy.ASEXUAL;

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

    public static final String[] DNAS_TO_MERGE = new String[]{
            "i1w2.938439637344335h1w1.0h2w0.0h3w0.0h4i2w1.0204171807132862h1w0.0h2w1.0h3w0.14204336412992902h4h1w1.0o1w0.3968569905053294o2h2w0.12628746187099615o1w1.0o2h3w0.0o1w4.063536547952453o2h4w0.0o1w0.42215656438888116o2o1o2",
            "i1w3.074657730083155h1w2.0690541621825305h2w1.667235535746976h3w4.66009216855542h4i2w0.0h1w1.0h2w0.0h3w3.4224108916892226h4h1w0.0o1w0.6927707959338774o2h2w0.0o1w1.0o2h3w1.9508770484143305o1w3.496327377351406o2h4w0.0o1w1.5780375288413073o2o1o2",
            "i1w1.2263189033379978h1w0.0h2w0.0h3w4.416779212882293h4i2w0.0h1w0.9490673570811157h2w0.6560221183555179h3w1.2337205808026002h4h1w1.0o1w1.0o2h2w0.40940050950079954o1w1.8168849817731179o2h3w0.0o1w4.080929516285027o2h4w0.0o1w4.627214644070196o2o1o2",
            "i1w1.5243587067628859h1w0.0h2w0.0h3w4.217322365862669h4i2w7.693251016336075h1w3.3003687461134246h2w8.889643795463268h3w3.216044531729215h4h1w1.0o1w0.07722794393085675o2h2w0.0o1w0.36138547593300396o2h3w1.4248987245947808o1w1.0o2h4w1.0o1w0.0o2o1o2"
    };

    public static final String[] DNAS_SUPER_BRAIN = new String[]{
            "i1w3.1215754921976004h1w0.0h2w1.6427635843595194h3w1.7451173112588565h4i2w1.0h1w1.2610712343770532h2w0.0h3w0.0h4h1w0.3347776599065755o1w2.00041574581104o2h2w0.8871415878536828o1w0.16086450749337633o2h3w0.0o1w0.9366941950226497o2h4w0.0o1w0.0o2o1o2",
            "i1w1.2263189033379978h1w0.0h2w0.0h3w4.416779212882293h4i2w0.0h1w0.9490673570811157h2w0.6560221183555179h3w1.2337205808026002h4h1w1.0o1w1.0o2h2w0.40940050950079954o1w1.8168849817731179o2h3w0.0o1w4.080929516285027o2h4w0.0o1w4.627214644070196o2o1o2"
    };
}
