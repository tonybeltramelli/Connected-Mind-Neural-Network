package com.tonybeltramelli.lab.element.brain;

import com.tonybeltramelli.lib.neural.NeuralNetwork;
import com.tonybeltramelli.lib.neural.neuron.InputNeuron;
import com.tonybeltramelli.lib.neural.neuron.Neuron;
import com.tonybeltramelli.lib.neural.neuron.OutputNeuron;

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
    }

    private void _simpleNetwork()
    {
        InputNeuron leftInput = new InputNeuron();
        InputNeuron rightInput = new InputNeuron();
        _addInputNeuron(leftInput);
        _addInputNeuron(rightInput);

        OutputNeuron leftOutput = new OutputNeuron();
        OutputNeuron rightOutput = new OutputNeuron();
        _addOutputNeuron(leftOutput);
        _addOutputNeuron(rightOutput);

        Neuron hiddenNeuron1 = new Neuron();
        Neuron hiddenNeuron2 = new Neuron();
        Neuron hiddenNeuron3 = new Neuron();

        /*_addHiddenNeuron(hiddenNeuron1);
        _addHiddenNeuron(hiddenNeuron2);
        _addHiddenNeuron(hiddenNeuron3);

        leftInput.connectTo(hiddenNeuron1);
        hiddenNeuron1.connectTo(hiddenNeuron3);
        hiddenNeuron3.connectTo(leftOutput);
        //hiddenNeuron1.connectTo(leftOutput);

        rightInput.connectTo(hiddenNeuron2);
        hiddenNeuron2.connectTo(hiddenNeuron3);
        hiddenNeuron3.connectTo(rightOutput);
        //hiddenNeuron2.connectTo(rightOutput);*/

        leftInput.connectTo(leftOutput);
        rightInput.connectTo(rightOutput);
    }

    private void _complexNetwork()
    {
        InputNeuron leftInput = new InputNeuron();
        InputNeuron rightInput = new InputNeuron();
        _addInputNeuron(leftInput);
        _addInputNeuron(rightInput);

        OutputNeuron leftOutput = new OutputNeuron();
        OutputNeuron rightOutput = new OutputNeuron();
        _addOutputNeuron(leftOutput);
        _addOutputNeuron(rightOutput);

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

        _addHiddenNeuron(hiddenNeuron1);
        _addHiddenNeuron(hiddenNeuron2);
        _addHiddenNeuron(hiddenNeuron3);
        _addHiddenNeuron(hiddenNeuron4);
        _addHiddenNeuron(hiddenNeuron5);
        _addHiddenNeuron(hiddenNeuron6);
        _addHiddenNeuron(hiddenNeuron7);
        _addHiddenNeuron(hiddenNeuron8);
        _addHiddenNeuron(hiddenNeuron9);
        _addHiddenNeuron(hiddenNeuron10);
        _addHiddenNeuron(hiddenNeuron11);
        _addHiddenNeuron(hiddenNeuron12);

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
