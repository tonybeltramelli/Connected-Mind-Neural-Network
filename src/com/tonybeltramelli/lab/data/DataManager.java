package com.tonybeltramelli.lab.data;

import com.tonybeltramelli.lab.config.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 27/04/2014
 */
public class DataManager
{
    private List<Score> _scoresPopulation;
    private List<List<Score>> _scoresGenerations;

    public DataManager()
    {
        super();

        _scoresGenerations = new ArrayList<List<Score>>();
        nextGeneration();
    }

    public void nextGeneration()
    {
        _scoresGenerations.add(new ArrayList<Score>());
        _scoresPopulation = _scoresGenerations.get(_scoresGenerations.size() - 1);
    }

    public void save(int fitnessScore, String dna)
    {
        System.out.println("save " + fitnessScore + ", " + dna);

        _scoresPopulation.add(new Score(fitnessScore, dna));
        _scoresPopulation = _mergeSort(_scoresPopulation);
    }

    private List<Score> _mergeSort(List<Score> list)
    {
        if(list.size() <= 1) return list;

        int middle = list.size() / 2;

        List<Score> left = list.subList(0, middle);
        List<Score> right = list.subList(middle, list.size());

        left = _mergeSort(left);
        right = _mergeSort(right);

        List<Score> sorted = new ArrayList<Score>();

        int leftPointer = 0;
        int rightPointer = 0;

        for(int k = 0; k < list.size(); k++)
        {
            if(rightPointer == right.size() || ((leftPointer < left.size()) && (left.get(leftPointer).getFitnessScore() <= right.get(rightPointer).getFitnessScore())))
            {
                sorted.add(left.get(leftPointer));
                leftPointer++;
            } else if(leftPointer == left.size() || ((rightPointer < right.size()) && (right.get(rightPointer).getFitnessScore() <= left.get(leftPointer).getFitnessScore())))
            {
                sorted.add(right.get(rightPointer));
                rightPointer++;
            }
        }

        return sorted;
    }

    public void print()
    {
        try
        {
            String content = "";

            for(int generation = 0; generation < _scoresGenerations.size(); generation++)
            {
                content += "## Generation " + (generation + 1) + "\n";

                for(int individual = 0; individual < _scoresGenerations.get(generation).size(); individual++)
                {
                    content += "    Organism " + individual + " : " + _scoresGenerations.get(generation).get(individual).getFitnessScore() + "\n";
                    content += "    DNA : " + _scoresGenerations.get(generation).get(individual).getDna() + "\n\n";
                }

                content += "\n";
            }

            File file = new File(Config.LOG_OUTPUT);

            if(!file.exists()) file.createNewFile();

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getBestDna()
    {
        return _scoresPopulation.get(_scoresPopulation.size() - 1).getDna();
    }

    public String getSecondBestDna()
    {
        return _scoresPopulation.get(_scoresPopulation.size() - 2).getDna();
    }

    public int getGenerationNumber()
    {
        return _scoresGenerations.size();
    }
}
