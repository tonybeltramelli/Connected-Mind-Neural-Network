package com.tonybeltramelli.lib.neural;

import com.tonybeltramelli.lib.neural.neuron.Neuron;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public class Synapse
{
    private int _weigth = 1;
    private double _value = 0;
    private Neuron _neuronFrom;
    private Neuron _neuronTo;

    public Synapse(Neuron neuronFrom)
    {
        this(neuronFrom, 1);
    }

    public Synapse(Neuron neuronFrom, int weight)
    {
        super();

        _neuronFrom = neuronFrom;
        _weigth = weight;
    }

    public void connectTo(Neuron neuronTo)
    {
        _neuronTo = neuronTo;
    }

    public void fire(double value)
    {
        if(_neuronTo == null) return;

        _value = value * _weigth;

        _neuronTo.activate(_value);
    }

    public double getValue()
    {
        return _value;
    }
}
