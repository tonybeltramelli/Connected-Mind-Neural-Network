package com.tonybeltramelli.lab.brain;

import com.tonybeltramelli.lab.config.Config;
import com.tonybeltramelli.lib.neural.InputNeuron;
import com.tonybeltramelli.lib.neural.NeuralNetwork;
import com.tonybeltramelli.lib.neural.Neuron;
import com.tonybeltramelli.lib.neural.OutputNeuron;
import com.tonybeltramelli.lib.util.UMath;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 01/04/2014
 */
public class Brain extends NeuralNetwork
{
    private Brain[] _brains;
    private boolean _isMainBrain;

    public Brain()
    {
        this(true);
    }

    public Brain(boolean isMainBrain)
    {
        super();

        _isMainBrain = isMainBrain;

        if(Config.USE_MERGED_NEURAL_NETWORK)
        {
            merge(Config.DNAS_TO_MERGE);
        } else if(Config.USE_SUPER_NEURAL_NETWORK && _isMainBrain)
        {
            _brains = new Brain[Config.DNAS_SUPER_BRAIN.length];
            Brain brain;

            for(int i = 0; i < Config.DNAS_SUPER_BRAIN.length; i ++)
            {
                brain = new Brain(false);
                brain.generate(Config.DNAS_SUPER_BRAIN[i]);

                _brains[i] = brain;
            }

            _simpleNetwork();
        } else
        {
            if(!Config.USE_BIAS)
            {
                _simpleNetwork();
            } else
            {
                _networkWithBias();
            }
        }
    }

    @Override
    public double[] run(double[] inputValues)
    {
        double[] outputs = super.run(inputValues);

        if(Config.USE_SUPER_NEURAL_NETWORK && _isMainBrain)
        {
            if(outputs[0] >= 1.0)
            {
                outputs = _brains[0].run(inputValues);
            }else if(outputs[1] >= 1.0)
            {
                outputs = _brains[1].run(inputValues);
            }
        }

        for(int output = 0; output < outputs.length; output++)
        {
            outputs[output] = outputs[output] >= 1.0 ? 1.0 : 0.0;
        }

        return outputs;
    }

    private void _simpleNetwork()
    {
        InputNeuron leftInput = new InputNeuron();
        InputNeuron rightInput = new InputNeuron();
        addInputNeuron(leftInput);
        addInputNeuron(rightInput);

        OutputNeuron leftOutput = new OutputNeuron();
        OutputNeuron rightOutput = new OutputNeuron();
        addOutputNeuron(leftOutput);
        addOutputNeuron(rightOutput);

        Neuron hiddenNeuron1 = new Neuron();
        Neuron hiddenNeuron2 = new Neuron();
        Neuron hiddenNeuron3 = new Neuron();
        Neuron hiddenNeuron4 = new Neuron();

        addHiddenNeuron(hiddenNeuron1);
        addHiddenNeuron(hiddenNeuron2);
        addHiddenNeuron(hiddenNeuron3);
        addHiddenNeuron(hiddenNeuron4);

        leftInput.connectTo(hiddenNeuron1, _getWeight());
        leftInput.connectTo(hiddenNeuron2, _getWeight());
        leftInput.connectTo(hiddenNeuron3, _getWeight());
        leftInput.connectTo(hiddenNeuron4, _getWeight());

        rightInput.connectTo(hiddenNeuron1, _getWeight());
        rightInput.connectTo(hiddenNeuron2, _getWeight());
        rightInput.connectTo(hiddenNeuron3, _getWeight());
        rightInput.connectTo(hiddenNeuron4, _getWeight());

        hiddenNeuron1.connectTo(leftOutput, _getWeight());
        hiddenNeuron2.connectTo(leftOutput, _getWeight());
        hiddenNeuron3.connectTo(leftOutput, _getWeight());
        hiddenNeuron4.connectTo(leftOutput, _getWeight());

        hiddenNeuron1.connectTo(rightOutput, _getWeight());
        hiddenNeuron2.connectTo(rightOutput, _getWeight());
        hiddenNeuron3.connectTo(rightOutput, _getWeight());
        hiddenNeuron4.connectTo(rightOutput, _getWeight());
    }

    private void _networkWithBias()
    {
        InputNeuron leftInput = new InputNeuron();
        InputNeuron biasInput = new InputNeuron();
        InputNeuron rightInput = new InputNeuron();
        addInputNeuron(leftInput);
        addInputNeuron(biasInput);
        addInputNeuron(rightInput);

        OutputNeuron leftOutput = new OutputNeuron();
        OutputNeuron rightOutput = new OutputNeuron();
        addOutputNeuron(leftOutput);
        addOutputNeuron(rightOutput);

        Neuron hiddenNeuron1 = new Neuron();
        Neuron hiddenNeuron2 = new Neuron();
        Neuron hiddenNeuron3 = new Neuron();
        Neuron hiddenNeuron4 = new Neuron();

        addHiddenNeuron(hiddenNeuron1);
        addHiddenNeuron(hiddenNeuron2);
        addHiddenNeuron(hiddenNeuron3);
        addHiddenNeuron(hiddenNeuron4);

        leftInput.connectTo(hiddenNeuron1, _getWeight());
        leftInput.connectTo(hiddenNeuron2, _getWeight());
        leftInput.connectTo(hiddenNeuron3, _getWeight());
        leftInput.connectTo(hiddenNeuron4, _getWeight());

        biasInput.connectTo(hiddenNeuron1, _getWeight());
        biasInput.connectTo(hiddenNeuron2, _getWeight());
        biasInput.connectTo(hiddenNeuron3, _getWeight());
        biasInput.connectTo(hiddenNeuron4, _getWeight());

        rightInput.connectTo(hiddenNeuron1, _getWeight());
        rightInput.connectTo(hiddenNeuron2, _getWeight());
        rightInput.connectTo(hiddenNeuron3, _getWeight());
        rightInput.connectTo(hiddenNeuron4, _getWeight());

        hiddenNeuron1.connectTo(leftOutput, _getWeight());
        hiddenNeuron2.connectTo(leftOutput, _getWeight());
        hiddenNeuron3.connectTo(leftOutput, _getWeight());
        hiddenNeuron4.connectTo(leftOutput, _getWeight());

        hiddenNeuron1.connectTo(rightOutput, _getWeight());
        hiddenNeuron2.connectTo(rightOutput, _getWeight());
        hiddenNeuron3.connectTo(rightOutput, _getWeight());
        hiddenNeuron4.connectTo(rightOutput, _getWeight());
    }

    private double _getWeight()
    {
        double weight = 0.0;

        if(Config.initializationStrategy == Config.InitializationStrategy.RANDOMIZED)
        {
            weight = UMath.random(-1.0, 2.0);
            weight = weight < 0.0 ? 0.0 : weight;
        } else if(Config.initializationStrategy == Config.InitializationStrategy.SEEDED)
        {
            weight = 1.0;
        }

        return weight;
    }
}
