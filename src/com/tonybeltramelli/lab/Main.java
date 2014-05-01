package com.tonybeltramelli.lab;

import com.tonybeltramelli.lib.neural.NeuralNetworkProcessor;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 01/04/2014
 */
public class Main extends Application
{
    public static void main(String[] args)
    {
        //launch(args);

        new NeuralNetworkProcessor().merge("i1w1.0h1w1.0h2w1.0h3w1.0h4i2w1.0h1w1.0h2w1.0h3w1.0h4h1w1.0o1w1.0o2h2w1.0o1w1.0o2h3w1.0o1w1.0o2h4w1.0o1w1.0o2o1o2", "i1w1.0h1w1.0h2i2w1.0h1w1.0h2h1w1.0h3w1.0h4h2w1.0h3w1.0h4h3w1.0o1w1.0o2h4w1.0o1w1.0o2o1o2");
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        new Controller(stage);
    }
}
