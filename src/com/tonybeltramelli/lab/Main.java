package com.tonybeltramelli.lab;

import com.tonybeltramelli.lab.config.Config;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 01/04/2014
 */
public class Main extends Application
{
    public static void main(String[] args)
    {
        if(args.length != 4)
        {
            System.out.println("Usage: \n");
            System.out.println("    ConnectedMind <population size> <generation number>\n");
            System.out.println("    ConnectedMind <population size> <generation number> <initialization strategy> <reproduction strategy>\n");
            System.out.println("        <population size>               integer, default : 10\n");
            System.out.println("        <generation number>             integer, default : 20\n");
            System.out.println("        <initialization strategy>       string(SEEDED | RANDOMIZED), default : SEEDED\n");
            System.out.println("        <reproduction strategy>         string(SEXUAL | ASEXUAL), default : ASEXUAL\n");
        } else
        {
            if(args[2].equals(Config.InitializationStrategy.SEEDED.toString()))
            {
                Config.initializationStrategy = Config.InitializationStrategy.SEEDED;
            } else if(args[2].equals(Config.InitializationStrategy.RANDOMIZED.toString()))
            {
                Config.initializationStrategy = Config.InitializationStrategy.RANDOMIZED;
            }

            if(args[3].equals(Config.ReproductionStrategy.SEXUAL.toString()))
            {
                Config.reproductionStrategy = Config.ReproductionStrategy.SEXUAL;
            } else if(args[3].equals(Config.ReproductionStrategy.ASEXUAL.toString()))
            {
                Config.reproductionStrategy = Config.ReproductionStrategy.ASEXUAL;
            }
        }

        if(args.length >= 2)
        {
            Config.populationSize = Integer.valueOf(args[0]);
            Config.generationNumber = Integer.valueOf(args[1]);
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        new Controller(stage);
    }
}
