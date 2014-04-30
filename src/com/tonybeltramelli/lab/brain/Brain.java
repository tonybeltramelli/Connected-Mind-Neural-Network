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
    public Brain()
    {
        super();
        
        if(!Config.USE_BIAS)
        {
            _simpleNetwork();
        }else{
            _networkWithBias();
        }

        //generate("i1w1o1i2w1o2o1o2", false);

        //System.out.println(getEncoding());
    }

    @Override
    public double[] run(double[] inputValues)
    {
        double[] outputs = super.run(inputValues);

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

        if(Config.INITIALIZATION_STRATEGY == Config.InitializationStrategy.RANDOMIZED)
        {
            weight = UMath.random(-1.0, 2.0);
            weight = weight < 0.0 ? 0.0 : weight;
        }else if(Config.INITIALIZATION_STRATEGY == Config.InitializationStrategy.SEEDED){
            weight = 1.0;
        }

        return weight;
    }
}
