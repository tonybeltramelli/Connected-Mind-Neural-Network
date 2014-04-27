package com.tonybeltramelli.lib.neural;

import com.tonybeltramelli.lib.util.UMath;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public class Neuron implements Encodable
{
    protected String _name;
    protected List<Synapse> _inputs;
    protected List<Synapse> _outputs;
    //
    private int _signalCounter = 0;
    private float _valueSum = 0;

    public Neuron()
    {
        super();

        _inputs = new ArrayList<Synapse>();
        _outputs = new ArrayList<Synapse>();
    }

    private Synapse _createNewOutput(double weight)
    {
        Synapse synapse = new Synapse(this, weight);
        _outputs.add(synapse);

        return synapse;
    }

    public void linkInput(Synapse synapseInput)
    {
        _inputs.add(synapseInput);
    }

    public void connectTo(Neuron neuronTo)
    {
        connectTo(neuronTo, Synapse.DEFAULT_WEIGHT);
    }

    public void connectTo(Neuron neuronTo, double weight)
    {
        Synapse synapse = _createNewOutput(weight);
        synapse.connectTo(neuronTo);

        neuronTo.linkInput(synapse);
    }

    public void activate(double value)
    {
        _signalCounter++;
        _valueSum += value;

        if(_signalCounter == _inputs.size())
        {
            for(int i = 0; i < _outputs.size(); i++)
            {
                _outputs.get(i).fire(UMath.sigmoid(_valueSum));
            }
        }
    }

    public void reset()
    {
        _signalCounter = 0;
        _valueSum = 0;
    }

    public void setName(int id)
    {
        _name = HIDDEN + String.valueOf(id);
    }

    public String getName()
    {
        return _name;
    }

    @Override
    public String getEncoding()
    {
        String encoding = _name;

        for(int i = 0; i < _outputs.size(); i++)
        {
            encoding += _outputs.get(i).getEncoding();
        }

        return encoding;
    }
}
