package com.tonybeltramelli.lib.neural.neuron;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public class InputNeuron extends Neuron
{
    public InputNeuron()
    {
        super();
    }

    public void feed(double inputValue)
    {
        for(int i = 0; i < _outputs.size(); i++)
        {
            _outputs.get(i).fire(inputValue);
        }
    }

    @Override
    public void setId(int id)
    {
        super.setId(id);

        _id = "i" + _id;
    }
}
