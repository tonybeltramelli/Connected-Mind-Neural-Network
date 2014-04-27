package com.tonybeltramelli.lab;

import com.tonybeltramelli.lab.brain.Brain;
import com.tonybeltramelli.lab.config.Config;
import com.tonybeltramelli.lab.data.DataManager;
import com.tonybeltramelli.lab.display.Display;
import javafx.stage.Stage;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 27/04/2014
 */
public class Controller
{
    private Display _display;
    private Brain _brain;
    private DataManager _dataManager;
    //
    private int _individualCounter = 0;

    public Controller(Stage stage)
    {
        super();

        _display = new Display(this, stage);

        _dataManager = new DataManager();

        _brain = new Brain();

        _createIndividual();
    }

    public void _createIndividual()
    {
        _individualCounter++;

        _display.build(_brain);
    }

    public void saveFitnessScore(int fitnessScore)
    {
        _dataManager.save(fitnessScore, _brain.getEncoding());

        if(_individualCounter < Config.POPULATION_SIZE)
        {
            _brain.mutate();
            _createIndividual();
        }else{
            _individualCounter = 0;

            if(_dataManager.getGenerationNumber() < Config.GENERATION_NUMBER)
            {
                _brain.merge(_dataManager.getBestDna(), _dataManager.getSecondBestDna());
                _dataManager.nextGeneration();

                _createIndividual();
            }
        }
    }
}
