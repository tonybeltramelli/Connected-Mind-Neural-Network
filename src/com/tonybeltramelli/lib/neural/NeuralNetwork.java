package com.tonybeltramelli.lib.neural;

import com.tonybeltramelli.lib.util.FlexList;
import com.tonybeltramelli.lib.util.RegExp;
import com.tonybeltramelli.lib.util.UMath;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 13/04/2014
 */
public class NeuralNetwork implements Encodable
{
    private FlexList<InputNeuron> _inputNeurons;
    private FlexList<OutputNeuron> _outputNeurons;
    private FlexList<Neuron> _hiddenNeurons;

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

    protected void _clear()
    {
        if(_inputNeurons != null) _inputNeurons.clear();
        if(_outputNeurons != null) _outputNeurons.clear();
        if(_hiddenNeurons != null) _hiddenNeurons.clear();

        _inputNeurons = new FlexList<InputNeuron>();
        _outputNeurons = new FlexList<OutputNeuron>();
        _hiddenNeurons = new FlexList<Neuron>();
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

    public void mutate()
    {
        generate(getEncoding(), true);
    }

    public void mate(String dna1, String dna2)
    {
        List<int[]> positionWeight1 = new ArrayList<int[]>();
        List<int[]> positionWeight2 = new ArrayList<int[]>();
        String dna = "";
        int lastPosition = 0;

        Matcher matcher = RegExp.parse(WEIGHT_REG_EXP, dna1);
        while(matcher.find())
        {
            positionWeight1.add(new int[]{matcher.start(), matcher.end()});
        }

        matcher = RegExp.parse(WEIGHT_REG_EXP, dna2);
        while(matcher.find())
        {
            positionWeight2.add(new int[]{matcher.start(), matcher.end()});
        }

        if(positionWeight1.size() != positionWeight2.size()) throw new RuntimeException();

        for(int position = 0; position < positionWeight1.size(); position++)
        {
            dna += dna1.substring(lastPosition, positionWeight1.get(position)[0]);
            lastPosition = positionWeight1.get(position)[1];

            if(Math.random() < 0.5)
            {
                dna += dna1.substring(positionWeight1.get(position)[0], positionWeight1.get(position)[1]);
            } else
            {
                dna += dna2.substring(positionWeight2.get(position)[0], positionWeight2.get(position)[1]);
            }
        }

        dna += dna1.substring(lastPosition, dna1.length());

        generate(dna);
    }

    public void merge(String dnas[])
    {
        if(dnas.length < 2) return;

        String lastMergedDna = dnas[0];

        for(int i = 1; i < dnas.length; i++)
        {
            lastMergedDna = _getMergedDna(lastMergedDna, dnas[i]);
        }

        generate(lastMergedDna);
    }

    private String _getMergedDna(String dna1, String dna2)
    {
        NeuralNetwork leftNetwork = new NeuralNetwork();
        leftNetwork.generate(dna1);

        NeuralNetwork rightNetwork = new NeuralNetwork();
        rightNetwork.generate(dna2);

        if((leftNetwork.getInputNeuronNumber() != rightNetwork.getInputNeuronNumber()) ||
                (leftNetwork.getOutputNeuronNumber() != rightNetwork.getOutputNeuronNumber()))
        {
            throw new RuntimeException();
        }

        Matcher dnaMatcher = RegExp.parse(Encodable.DNA_REG_EXP, dna1);
        Matcher connectionMatcher;
        String dnaGroup;
        List<String> dna1Groups = new ArrayList<String>();
        int counter = 0;

        while(dnaMatcher.find())
        {
            dna1Groups.add(dnaMatcher.group());
        }

        dnaMatcher = RegExp.parse(Encodable.DNA_REG_EXP, dna2);

        while(dnaMatcher.find())
        {
            dnaGroup = dnaMatcher.group();

            connectionMatcher = RegExp.parse(Encodable.CONNECTION_REG_EXP, dnaGroup.substring(dnaGroup.indexOf(Encodable.WEIGHT), dnaGroup.length()));

            if(dnaGroup.indexOf(Encodable.INPUT) != -1)
            {
                while(connectionMatcher.find())
                {
                    dnaGroup = connectionMatcher.group();

                    dnaGroup = dnaGroup.substring(0, dnaGroup.indexOf(Encodable.HIDDEN) + 1) + String.valueOf(Integer.parseInt(dnaGroup.substring(dnaGroup.indexOf(Encodable.HIDDEN) + 1, dnaGroup.length())) + leftNetwork.getHiddenNeuronNumber());
                    dna1Groups.set(counter, dna1Groups.get(counter) + dnaGroup);
                }

                counter++;
            } else if(dnaGroup.indexOf(Encodable.HIDDEN) != -1)
            {
                dnaGroup = dnaGroup.substring(0, dnaGroup.indexOf(Encodable.HIDDEN) + 1) + String.valueOf(Integer.parseInt(dnaGroup.substring(dnaGroup.indexOf(Encodable.HIDDEN) + 1, dnaGroup.indexOf(Encodable.WEIGHT))) + leftNetwork.getHiddenNeuronNumber()) + dnaGroup.substring(dnaGroup.indexOf(Encodable.WEIGHT), dnaGroup.length());

                dna1Groups.add(dnaGroup);
            }
        }

        String mergedDna = "";

        for(int i = 0; i < dna1Groups.size(); i++)
        {
            mergedDna += dna1Groups.get(i);
        }

        return mergedDna;
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
        generate(encoding, false);
    }

    public void generate(String encoding, boolean induceMutation)
    {
        _clear();

        Matcher dnaMatcher = RegExp.parse(DNA_REG_EXP, encoding);
        Matcher connectionMatcher;
        Matcher weightMatcher;
        String dnaGroup;
        String neuronName;
        double weight;

        while(dnaMatcher.find())
        {
            dnaGroup = dnaMatcher.group();

            neuronName = dnaGroup.substring(0, dnaGroup.indexOf(WEIGHT));
            _createNeuron(neuronName);

            connectionMatcher = RegExp.parse(CONNECTION_REG_EXP, dnaGroup.substring(dnaGroup.indexOf(WEIGHT), dnaGroup.length()));

            while(connectionMatcher.find())
            {
                dnaGroup = connectionMatcher.group();

                weightMatcher = RegExp.parse(NEURON_REG_EXP, dnaGroup);

                String[] connection = new String[2];
                int counter = 0;

                while(weightMatcher.find())
                {
                    connection[counter] = weightMatcher.group();
                    counter++;
                }

                _createNeuron(connection[1]);

                weight = Double.parseDouble(connection[0].substring(1, connection[0].length()));

                if(induceMutation)
                {
                    double rand = Math.random();

                    if(rand < 0.5)
                    {
                        weight += UMath.random(-2, 1);
                    } else if(rand > 0.9)
                    {
                        if(Math.random() < 0.5)
                        {
                            weight = 0.0;
                        } else
                        {
                            weight = 1.0;
                        }
                    }
                    weight = weight < 0.0 ? 0.0 : weight > 10.0 ? 10.0 : weight;
                }

                _getNeuron(neuronName).connectTo(_getNeuron(connection[1]), weight);
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
                throw new RuntimeException("The Neuron type " + neuronName.getKey() + " is not supported");
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
                throw new RuntimeException("The Neuron type " + neuronName.getKey() + " is not supported");
        }

        neuron.setName(neuronName.getValue());
    }

    private Pair<Character, Integer> _getProcessedName(String name)
    {
        char ch = name.charAt(0);
        int id = Integer.parseInt(name.substring(1, name.length()));

        return new Pair<Character, Integer>(ch, id);
    }

    public int getInputNeuronNumber()
    {
        return _inputNeurons.size();
    }

    public int getOutputNeuronNumber()
    {
        return _outputNeurons.size();
    }

    public int getHiddenNeuronNumber()
    {
        return _hiddenNeurons.size();
    }
}
