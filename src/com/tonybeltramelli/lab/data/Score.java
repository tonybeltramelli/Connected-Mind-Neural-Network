package com.tonybeltramelli.lab.data;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 27/04/2014
 */
public class Score
{
    private int _fitnessScore;
    private String _dna;

    public Score(int fitnessScore, String dna)
    {
        super();

        _fitnessScore = fitnessScore;
        _dna = dna;
    }

    public int getFitnessScore()
    {
        return _fitnessScore;
    }

    public String getDna()
    {
        return _dna;
    }

    @Override
    public String toString()
    {
        return "(Score : " + _fitnessScore + ")";
    }
}
