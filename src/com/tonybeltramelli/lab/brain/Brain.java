package com.tonybeltramelli.lab.brain;

import com.tonybeltramelli.lib.neural.InputNeuron;
import com.tonybeltramelli.lib.neural.NeuralNetwork;
import com.tonybeltramelli.lib.neural.Neuron;
import com.tonybeltramelli.lib.neural.OutputNeuron;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 01/04/2014
 */
public class Brain extends NeuralNetwork
{
    public Brain()
    {
        super();

        //_complexNetwork();
        _simpleNetwork();

        //generate("i1w1o1i2w1o2o1o2", false);

        //System.out.println(" ----> "+getEncoding());
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

        leftInput.connectTo(hiddenNeuron1);
        leftInput.connectTo(hiddenNeuron2);
        leftInput.connectTo(hiddenNeuron3);
        leftInput.connectTo(hiddenNeuron4);

        rightInput.connectTo(hiddenNeuron1);
        rightInput.connectTo(hiddenNeuron2);
        rightInput.connectTo(hiddenNeuron3);
        rightInput.connectTo(hiddenNeuron4);

        hiddenNeuron1.connectTo(leftOutput);
        hiddenNeuron2.connectTo(leftOutput);
        hiddenNeuron3.connectTo(leftOutput);
        hiddenNeuron4.connectTo(leftOutput);

        hiddenNeuron1.connectTo(rightOutput);
        hiddenNeuron2.connectTo(rightOutput);
        hiddenNeuron3.connectTo(rightOutput);
        hiddenNeuron4.connectTo(rightOutput);
    }

    private void _complexNetwork()
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
        Neuron hiddenNeuron5 = new Neuron();
        Neuron hiddenNeuron6 = new Neuron();
        Neuron hiddenNeuron7 = new Neuron();
        Neuron hiddenNeuron8 = new Neuron();
        Neuron hiddenNeuron9 = new Neuron();
        Neuron hiddenNeuron10 = new Neuron();
        Neuron hiddenNeuron11 = new Neuron();
        Neuron hiddenNeuron12 = new Neuron();

        addHiddenNeuron(hiddenNeuron1);
        addHiddenNeuron(hiddenNeuron2);
        addHiddenNeuron(hiddenNeuron3);
        addHiddenNeuron(hiddenNeuron4);
        addHiddenNeuron(hiddenNeuron5);
        addHiddenNeuron(hiddenNeuron6);
        addHiddenNeuron(hiddenNeuron7);
        addHiddenNeuron(hiddenNeuron8);
        addHiddenNeuron(hiddenNeuron9);
        addHiddenNeuron(hiddenNeuron10);
        addHiddenNeuron(hiddenNeuron11);
        addHiddenNeuron(hiddenNeuron12);

        leftInput.connectTo(hiddenNeuron1);
        hiddenNeuron1.connectTo(hiddenNeuron5);
        hiddenNeuron5.connectTo(hiddenNeuron8);
        hiddenNeuron8.connectTo(hiddenNeuron11);
        hiddenNeuron11.connectTo(leftOutput);

        hiddenNeuron1.connectTo(hiddenNeuron6);
        hiddenNeuron6.connectTo(hiddenNeuron8);

        leftInput.connectTo(hiddenNeuron2);
        hiddenNeuron2.connectTo(hiddenNeuron6);

        rightInput.connectTo(hiddenNeuron3);
        hiddenNeuron3.connectTo(hiddenNeuron6);
        hiddenNeuron6.connectTo(hiddenNeuron9);
        hiddenNeuron9.connectTo(hiddenNeuron11);

        rightInput.connectTo(hiddenNeuron4);
        hiddenNeuron4.connectTo(hiddenNeuron7);
        hiddenNeuron7.connectTo(hiddenNeuron10);
        hiddenNeuron10.connectTo(hiddenNeuron12);
        hiddenNeuron12.connectTo(rightOutput);

        hiddenNeuron3.connectTo(hiddenNeuron7);
        hiddenNeuron7.connectTo(hiddenNeuron9);
        hiddenNeuron9.connectTo(hiddenNeuron12);
    }
}
