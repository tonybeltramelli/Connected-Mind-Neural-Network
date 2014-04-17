package com.tonybeltramelli.lib.neural;

import com.tonybeltramelli.lib.neural.neuron.InputNeuron;
import com.tonybeltramelli.lib.neural.neuron.Neuron;
import com.tonybeltramelli.lib.neural.neuron.OutputNeuron;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public abstract class NeuralNetwork
{
    private List<InputNeuron> _inputNeurons;
    private List<OutputNeuron> _outputNeurons;
    private List<Neuron> _hiddenNeurons;

    public NeuralNetwork()
    {
        super();

        _inputNeurons = new ArrayList<InputNeuron>();
        _outputNeurons = new ArrayList<OutputNeuron>();
        _hiddenNeurons = new ArrayList<Neuron>();
    }

    protected void _addInputNeuron(InputNeuron inputNeuron)
    {
        _inputNeurons.add(inputNeuron);
        inputNeuron.setId(_inputNeurons.size());
    }

    protected void _addOutputNeuron(OutputNeuron outputNeuron)
    {
        _outputNeurons.add(outputNeuron);
        outputNeuron.setId(_outputNeurons.size());
    }

    protected void _addHiddenNeuron(Neuron hiddenNeuron)
    {
        _hiddenNeurons.add(hiddenNeuron);
        hiddenNeuron.setId(_hiddenNeurons.size());
    }

    public int[] run(int[] inputValues)
    {
        _reset();

        if(_inputNeurons.size() != inputValues.length || _outputNeurons.size() == 0) return null;

        for(int input = 0; input < inputValues.length; input ++)
        {
            _inputNeurons.get(input).feed(inputValues[input]);
        }

        int[] outputValues = new int[_outputNeurons.size()];

        for(int output = 0; output < outputValues.length; output ++)
        {
            outputValues[output] = _outputNeurons.get(output).read() > 0.8 ? 1 : 0;
        }

        return outputValues;
    }

    private void _reset()
    {
        List<Neuron> neurons = new ArrayList<Neuron>();

        neurons.addAll(_inputNeurons);
        neurons.addAll(_outputNeurons);
        neurons.addAll(_hiddenNeurons);

        for(int i = 0; i < neurons.size(); i ++)
        {
            neurons.get(i).reset();
        }
    }
}
