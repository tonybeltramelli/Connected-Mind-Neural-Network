package com.tonybeltramelli.lib.neural;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public class Synapse implements Encodable
{
    public static final double DEFAULT_WEIGHT = 1.0;
    //
    private double _weight;
    private double _value = 0;
    private Neuron _neuronFrom;
    private Neuron _neuronTo;

    public Synapse(Neuron neuronFrom)
    {
        this(neuronFrom, DEFAULT_WEIGHT);
    }

    public Synapse(Neuron neuronFrom, double weight)
    {
        super();

        _neuronFrom = neuronFrom;
        _weight = weight;
    }

    public void connectTo(Neuron neuronTo)
    {
        _neuronTo = neuronTo;
    }

    public void fire(double value)
    {
        if(_neuronTo == null) return;

        _value = value * _weight;

        _neuronTo.activate(_value);
    }

    public double getValue()
    {
        return _value;
    }

    @Override
    public String getEncoding()
    {
        return WEIGHT + "" + _weight + _neuronTo.getName();
    }
}
