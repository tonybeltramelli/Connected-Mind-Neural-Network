package com.tonybeltramelli.lib.neural;

import com.tonybeltramelli.lib.util.FlexArray;
import com.tonybeltramelli.lib.util.RegExp;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public class NeuralNetwork implements Encodable
{
    private FlexArray<InputNeuron> _inputNeurons;
    private FlexArray<OutputNeuron> _outputNeurons;
    private FlexArray<Neuron> _hiddenNeurons;

    public NeuralNetwork()
    {
        super();

        _clear();
    }

    public void addInputNeuron(InputNeuron inputNeuron)
    {
        _inputNeurons.add(inputNeuron);
        inputNeuron.setName(_inputNeurons.size());
    }

    public void addOutputNeuron(OutputNeuron outputNeuron)
    {
        _outputNeurons.add(outputNeuron);
        outputNeuron.setName(_outputNeurons.size());
    }

    public void addHiddenNeuron(Neuron hiddenNeuron)
    {
        _hiddenNeurons.add(hiddenNeuron);
        hiddenNeuron.setName(_hiddenNeurons.size());
    }

    public double[] run(double[] inputValues)
    {
        _reset();

        if(_inputNeurons.size() != inputValues.length || _outputNeurons.size() == 0) return null;

        for(int input = 0; input < inputValues.length; input++)
        {
            _inputNeurons.get(input).feed(inputValues[input]);
        }

        double[] outputValues = new double[_outputNeurons.size()];

        for(int output = 0; output < outputValues.length; output++)
        {
            outputValues[output] = _outputNeurons.get(output).read();
        }

        return outputValues;
    }

    private void _clear()
    {
        if(_inputNeurons != null) _inputNeurons.clear();
        if(_outputNeurons != null) _outputNeurons.clear();
        if(_hiddenNeurons != null) _hiddenNeurons.clear();

        _inputNeurons = new FlexArray<InputNeuron>();
        _outputNeurons = new FlexArray<OutputNeuron>();
        _hiddenNeurons = new FlexArray<Neuron>();
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
            encoding += neurons.get(i).getEncoding();
        }

        return encoding;
    }

    public void generate(String encoding)
    {
        _clear();

        Matcher dnaMatcher = RegExp.parse(DNA_REG_EXP, encoding);
        Matcher connectionMatcher;
        Matcher weightMatcher;
        String dnaGroup;
        String neuronName;

        while(dnaMatcher.find())
        {
            dnaGroup = dnaMatcher.group();

            neuronName = dnaGroup.substring(0, dnaGroup.indexOf(WEIGHT));
            _createNeuron(neuronName);

            connectionMatcher = RegExp.parse(CONNECTION_REG_EXP, dnaGroup.substring(dnaGroup.indexOf(WEIGHT), dnaGroup.length()));

            while(connectionMatcher.find())
            {
                dnaGroup = connectionMatcher.group();

                weightMatcher = RegExp.parse(WEIGHT_REG_EXP, dnaGroup);

                String[] connection = new String[2];
                int counter = 0;

                while(weightMatcher.find())
                {
                    connection[counter] = weightMatcher.group();
                    counter++;
                }

                _createNeuron(connection[1]);

                _getNeuron(neuronName).connectTo(_getNeuron(connection[1]), _getProcessedName(connection[0]).getValue());
            }
        }
    }

    private Neuron _getNeuron(String name)
    {
        Pair<Character, Integer> neuronName = _getProcessedName(name);
        Neuron neuron;

        switch(neuronName.getKey())
        {
            case INPUT:
                neuron = _inputNeurons.get(neuronName.getValue() - 1);
                break;
            case HIDDEN:
                neuron = _hiddenNeurons.get(neuronName.getValue() - 1);
                break;
            case OUTPUT:
                neuron = _outputNeurons.get(neuronName.getValue() - 1);
                break;
            default:
                throw new RuntimeException("The Neuron type "+neuronName.getKey()+" is not supported");
        }

        return neuron;
    }

    private void _createNeuron(String name)
    {
        Pair<Character, Integer> neuronName = _getProcessedName(name);
        Neuron neuron;

        switch(neuronName.getKey())
        {
            case INPUT:
                neuron = new InputNeuron();
                _inputNeurons.add(neuronName.getValue() - 1, (InputNeuron) neuron, false);
                break;
            case HIDDEN:
                neuron = new Neuron();
                _hiddenNeurons.add(neuronName.getValue() - 1, neuron, false);
                break;
            case OUTPUT:
                neuron = new OutputNeuron();
                _outputNeurons.add(neuronName.getValue() - 1, (OutputNeuron) neuron, false);
                break;
            default:
                throw new RuntimeException("The Neuron type "+neuronName.getKey()+" is not supported");
        }

        neuron.setName(neuronName.getValue());
    }

    private Pair<Character, Integer> _getProcessedName(String name)
    {
        char ch = name.charAt(0);
        int id = Integer.parseInt(name.substring(1, name.length()));

        return new Pair<Character, Integer>(ch, id);
    }
}
