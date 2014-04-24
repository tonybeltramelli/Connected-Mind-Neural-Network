package com.tonybeltramelli.lib.neural;

import com.tonybeltramelli.lib.util.FlexArray;
import com.tonybeltramelli.lib.util.RegExp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public abstract class NeuralNetwork implements Encodable
{
    private FlexArray<InputNeuron> _inputNeurons;
    private FlexArray<OutputNeuron> _outputNeurons;
    private FlexArray<Neuron> _hiddenNeurons;

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
            System.out.println(neurons.get(i).getEncoding());
            encoding += neurons.get(i).getEncoding();
        }

        return encoding;
    }

    public void generate(String encoding)
    {
        _clear();

        Matcher dnaMatcher = RegExp.parse("([iho][0-9]+([w][0-9]+[ho][0-9]+))([w][0-9]+[ho][0-9]+)*", encoding);
        Matcher connectionMatcher;
        Matcher weightMatcher;
        String dnaGroup;
        String dnaSegment;
        int val;

        while(dnaMatcher.find())
        {
            dnaGroup = dnaMatcher.group();

            //start neuron
            dnaSegment = dnaGroup.substring(0, dnaGroup.indexOf('w'));
            val = Integer.parseInt(dnaSegment.substring(1, dnaSegment.length()));

            _createNeuron(dnaSegment.charAt(0), val);

            //connected neurons
            connectionMatcher = RegExp.parse("([w][0-9]+[ho][0-9]+)", dnaGroup.substring(dnaGroup.indexOf('w'), dnaGroup.length()));

            while(connectionMatcher.find())
            {
                dnaGroup = connectionMatcher.group();

                weightMatcher = RegExp.parse("([who][0-9]+)", dnaGroup);

                String[] connection = new String[2];
                int counter = 0;

                while(weightMatcher.find())
                {
                    connection[counter] = weightMatcher.group();
                    counter ++;
                }

                System.out.println("connection "+connection[1]+" "+connection[0]);
            }
        }
    }

    private void _createNeuron(char ch, int id)
    {
        Neuron neuron;

        switch(ch)
        {
            case INPUT:
                System.out.println("create input neuron " + id);

                //if(_inputNeurons.size() >)

                //_inputNeurons.add(id - 1, ch);

                break;
            case HIDDEN:
                System.out.println("create hidden neuron " + id);
                break;
            case OUTPUT:
                System.out.println("create output neuron " + id);
                break;
        }
    }
}
