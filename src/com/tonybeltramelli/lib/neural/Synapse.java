package com.tonybeltramelli.lib.neural;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public class Synapse implements Encodable
{
    public static final int DEFAULT_WEIGHT = 1;
    //
    private int _weigth;
    private double _value = 0;
    private Neuron _neuronFrom;
    private Neuron _neuronTo;

    public Synapse(Neuron neuronFrom)
    {
        this(neuronFrom, DEFAULT_WEIGHT);
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

    @Override
    public String getEncoding()
    {
        return WEIGHT + "" + _weigth + _neuronTo.getName();
    }
}
