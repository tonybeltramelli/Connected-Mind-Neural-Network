package com.tonybeltramelli.lib.neural;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public abstract class NeuralNetwork implements Encodable
{
    private List<InputNeuron> _inputNeurons;
    private List<OutputNeuron> _outputNeurons;
    private List<Neuron> _hiddenNeurons;

    public NeuralNetwork()
    {
        super();

        _clear();
    }

    protected void _addInputNeuron(InputNeuron inputNeuron)
    {
        _inputNeurons.add(inputNeuron);
        inputNeuron.setName(_inputNeurons.size());
    }

    protected void _addOutputNeuron(OutputNeuron outputNeuron)
    {
        _outputNeurons.add(outputNeuron);
        outputNeuron.setName(_outputNeurons.size());
    }

    protected void _addHiddenNeuron(Neuron hiddenNeuron)
    {
        _hiddenNeurons.add(hiddenNeuron);
        hiddenNeuron.setName(_hiddenNeurons.size());
    }

    public int[] run(int[] inputValues)
    {
        _reset();

        if(_inputNeurons.size() != inputValues.length || _outputNeurons.size() == 0) return null;

        for(int input = 0; input < inputValues.length; input++)
        {
            _inputNeurons.get(input).feed(inputValues[input]);
        }

        int[] outputValues = new int[_outputNeurons.size()];

        for(int output = 0; output < outputValues.length; output++)
        {
            outputValues[output] = _outputNeurons.get(output).read() > 0.8 ? 1 : 0;
        }

        return outputValues;
    }

    private void _clear()
    {
        if(_inputNeurons != null) _inputNeurons.clear();
        if(_outputNeurons != null) _outputNeurons.clear();
        if(_hiddenNeurons != null) _hiddenNeurons.clear();

        _inputNeurons = new ArrayList<InputNeuron>();
        _outputNeurons = new ArrayList<OutputNeuron>();
        _hiddenNeurons = new ArrayList<Neuron>();
    }

    private void _reset()
    {
        List<Neuron> neurons = _getAllNeurons();

        for(int i = 0; i < neurons.size(); i++)
        {
            neurons.get(i).reset();
        }
    }

    private List<Neuron> _getAllNeurons()
    {
        List<Neuron> neurons = new ArrayList<Neuron>();

        neurons.addAll(_inputNeurons);
        neurons.addAll(_hiddenNeurons);
        neurons.addAll(_outputNeurons);

        return neurons;
    }

    @Override
    public String getEncoding()
    {
        List<Neuron> neurons = _getAllNeurons();
        String encoding = "";

        for(int i = 0; i < neurons.size(); i++)
        {
            System.out.println(neurons.get(i).getEncoding());
            encoding += neurons.get(i).getEncoding();
        }

        return encoding;
    }

    public void generate(String encoding)
    {
        _clear();

        Neuron neuron;
        char ch;
        int val;

        for(int i = 0; i < encoding.length(); i++)
        {
            ch = encoding.charAt(i);

            if(ch == INPUT)
            {
                if(i + 3 <= encoding.length() && encoding.substring(i + 1, i + 3).matches("([0-9]+)"))
                {
                    val = Integer.valueOf(encoding.substring(i + 1, i + 3));

                    System.out.println("here 1 : "+val);
                }else{
                    val = Integer.valueOf(encoding.substring(i + 1, i + 2));

                    System.out.println("here 2 : "+val);
                }

                if(val > _inputNeurons.size())
                {
                    System.out.println("create input neuron "+val);

                    neuron = new InputNeuron();
                    _inputNeurons.add((InputNeuron) neuron);
                }
            }

            if(ch == OUTPUT)
            {
                val = Integer.valueOf(encoding.charAt(i + 1));

                if(val > _outputNeurons.size())
                {
                    neuron = new OutputNeuron();
                    _outputNeurons.add((OutputNeuron) neuron);
                }
            }
        }
    }
}
