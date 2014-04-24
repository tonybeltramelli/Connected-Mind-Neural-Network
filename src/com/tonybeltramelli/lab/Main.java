package com.tonybeltramelli.lab;

import com.tonybeltramelli.lab.display.Display;
import com.tonybeltramelli.lib.neural.NeuralNetwork;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 01/04/2014
 */
public class Main extends Application
{
    public static void main(String[] args)
    {
        //NeuralNetwork.parse("i1w1h1w1h2i2w1h3w1h4h1w1h5w1h6h2w1h6h3w1h6w1h7h4w1h7h5w1h8h6w1h8w1h9h7w1h10w1h9h8w1h11h9w1h11w1h12h10w1h12h11w1o1h12w1o2o1o2", "");
        //launch(args);

        NeuralNetwork.generation("i1w1h1w1h2i2w1h3w1h4h1w1h5w1h6h2w1h6h3w1h6w1h7h4w1h7h5w1h8h6w1h8w1h9h7w1h10w1h9h8w1h11h9w1h11w1h12h10w1h12h11w1o1h12w1o2o1o2");
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        new Display(stage);
    }
}
