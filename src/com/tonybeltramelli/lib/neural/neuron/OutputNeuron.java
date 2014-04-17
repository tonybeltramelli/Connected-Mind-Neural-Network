package com.tonybeltramelli.lib.neural.neuron;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public class OutputNeuron extends Neuron
{
    public OutputNeuron()
    {
        super();
    }

    public double read()
    {
        double result = 0;

        for(int i = 0; i < _inputs.size(); i++)
        {
            result += _inputs.get(i).getValue();
        }

        return result;
    }

    @Override
    public void setId(int id)
    {
        super.setId(id);

        _id = "o" + _id;
    }
}
