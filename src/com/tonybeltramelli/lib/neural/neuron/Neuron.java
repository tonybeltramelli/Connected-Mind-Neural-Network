package com.tonybeltramelli.lib.neural.neuron;

import com.tonybeltramelli.lib.neural.Synapse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public class Neuron
{
    protected String _id;
    protected List<Synapse> _inputs;
    protected List<Synapse> _outputs;
    //
    private int _signalCounter = 0;
    private double _valueSum = 0;

    public Neuron()
    {
        super();

        _inputs = new ArrayList<Synapse>();
        _outputs = new ArrayList<Synapse>();
    }

    private Synapse _createNewOutput()
    {
        Synapse synapse = new Synapse(this);
        _outputs.add(synapse);

        return synapse;
    }

    public void linkInput(Synapse synapseInput)
    {
        _inputs.add(synapseInput);
    }

    public void connectTo(Neuron neuronTo)
    {
        Synapse synapse = _createNewOutput();
        synapse.connectTo(neuronTo);

        neuronTo.linkInput(synapse);
    }

    public void activate(double value)
    {
        _signalCounter++;
        _valueSum += value;

        if(_signalCounter == _inputs.size())
        {
            //System.out.println("-- activate " + _id + ", valueSum : " + _valueSum + ", sig " + UMath.sigmoid(_valueSum) + ", input size : " + _inputs.size() + ", output size : " + _outputs.size());

            for(int i = 0; i < _outputs.size(); i++)
            {
                _outputs.get(i).fire(_valueSum); //UMath.sigmoid(_valueSum)
            }
        }
    }

    public void reset()
    {
        _signalCounter = 0;
        _valueSum = 0;
    }

    public void setId(int id)
    {
        _id = String.valueOf(id);
    }
}