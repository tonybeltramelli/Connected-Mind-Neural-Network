package com.tonybeltramelli.lib.neural;

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
    public void setName(int id)
    {
        super.setName(id);

        _name = _name.replace(HIDDEN, INPUT);
    }
}
