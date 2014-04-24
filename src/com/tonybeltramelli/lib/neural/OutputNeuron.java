package com.tonybeltramelli.lib.neural;

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
    public void setName(int id)
    {
        super.setName(id);

        _name = _name.replace(HIDDEN, OUTPUT);
    }
}
