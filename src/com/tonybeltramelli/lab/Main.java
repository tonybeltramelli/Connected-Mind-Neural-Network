package com.tonybeltramelli.lab;

import com.tonybeltramelli.lab.display.Display;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 01/04/2014
 */
public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        new Display(stage);
    }
}
