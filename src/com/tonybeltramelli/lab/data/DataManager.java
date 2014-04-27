package com.tonybeltramelli.lab.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 27/04/2014
 */
public class DataManager
{
    private List<Score> _scores;

    public DataManager()
    {
        super();

        _scores = new ArrayList<Score>();
    }

    public void save(int fitnessScore, String dna)
    {
        System.out.println("save " + fitnessScore + ", " + dna);

        _scores.add(new Score(fitnessScore, dna));
        _scores = _mergeSort(_scores);
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

    public Score getBestDna()
    {
        return _scores.get(_scores.size() - 1);
    }
}
