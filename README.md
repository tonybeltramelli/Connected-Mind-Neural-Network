Connected-Mind-Neural-Network
=============================

Neuroevolution project implementing Evolutionary Algorithm and Genetic Algorithm

Project demo video  
<https://www.youtube.com/watch?v=vfZGyZp81aI&feature=youtu.be>

##Usage

```bash
ConnectedMindNeuralNetwork <population size> <generation number>
ConnectedMindNeuralNetwork <population size> <generation number> <initialization strategy> <reproduction strategy>
    <population size>               integer, default : 10
    <generation number>             integer, default : 20
    <initialization strategy>       string(SEEDED | RANDOMIZED), default : SEEDED
    <reproduction strategy>         string(SEXUAL | ASEXUAL), default : ASEXUAL
```

##Lib

### FlexList data structure usage
**com.tonybeltramelli.lib.util.FlexList**  
Custom data structure allowing ArrayList to be filled at any index even if there is no element at the said index.

```java

FlexList<String> flexList = new FlexList<String>();
flexList.add("value 1");
flexList.add("value 2");

System.out.println(flexList);
// output: [value 1, value 2]

flexList.add(4, "value 4");
flexList.add("value 5");
flexList.add(6, "value 6");

System.out.println(flexList);
// output: [value 1, value 2, null, null, value 4, value 5, value 6]

flexList.add(4, "new 4", false);

System.out.println(flexList);
// output: [value 1, value 2, null, null, value 4, value 5, value 6]

flexList.add(4, "new 4", true);

System.out.println(flexList);
// output: [value 1, value 2, null, null, new 4, value 5, value 6]

```

### Neural Network usage
**com.tonybeltramelli.lib.neural.NeuralNetwork**  
**com.tonybeltramelli.lib.neural.Neuron**  
**com.tonybeltramelli.lib.neural.InputNeuron**  
**com.tonybeltramelli.lib.neural.OutputNeuron**  

```java

NeuralNetwork neuralNetwork = new NeuralNetwork();

//Manual creation
InputNeuron leftInput = new InputNeuron();
InputNeuron rightInput = new InputNeuron();
neuralNetwork.addInputNeuron(leftInput);
neuralNetwork.addInputNeuron(rightInput);

OutputNeuron leftOutput = new OutputNeuron();
OutputNeuron rightOutput = new OutputNeuron();
neuralNetwork.addOutputNeuron(leftOutput);
neuralNetwork.addOutputNeuron(rightOutput);

Neuron hiddenNeuron1 = new Neuron();
Neuron hiddenNeuron2 = new Neuron();
Neuron hiddenNeuron3 = new Neuron();

neuralNetwork.addHiddenNeuron(hiddenNeuron1);
neuralNetwork.addHiddenNeuron(hiddenNeuron2);
neuralNetwork.addHiddenNeuron(hiddenNeuron3);

leftInput.connectTo(hiddenNeuron1);
hiddenNeuron1.connectTo(hiddenNeuron3);
hiddenNeuron3.connectTo(leftOutput);

rightInput.connectTo(hiddenNeuron2);
hiddenNeuron2.connectTo(hiddenNeuron3);
hiddenNeuron3.connectTo(rightOutput);

System.out.println(neuralNetwork.getEncoding());
// output: i1w1.0h1i2w1.0h2h1w1.0h3h2w1.0h3h3w1.0o1w1.0o2o1o2

// Automatic creation
neuralNetwork.generate("i1w1h1i2w1h2h1w1h3h2w1h3h3w1o1w1o2o1o2");

System.out.println(neuralNetwork.getEncoding());
// output: i1w1.0h1i2w1.0h2h1w1.0h3h2w1.0h3h3w1.0o1w1.0o2o1o2

//Induce mutations
neuralNetwork.generate("i1w1.0h1i2w1.0h2h1w1.0h3h2w1.0h3h3w1.0o1w1.0o2o1o2", true);

System.out.println(neuralNetwork.getEncoding());
// output: i1w1.0h1i2w1.77621132977439h2h1w1.115626470054826h3h2w1.0h3h3w1.0o1w1.0o2o1o2

// Running
double input1 = 1.0;
double input2 = 0.56;
double[] outputs = neuralNetwork.run(new double[]{input1, input2});

// Network with DNA merging
NeuralNetwork neuralNetworkA = new NeuralNetwork();
neuralNetworkA.generate("i1w1.0h1i2w1.0h2h1w1.0h3h2w1.0h3h3w1.0o1w1.0o2o1o2");

NeuralNetwork neuralNetworkB = new NeuralNetwork();
neuralNetworkB.generate("i1w1.0h1i2w1.77621132977439h2h1w1.115626470054826h3h2w1.0h3h3w1.0o1w1.0o2o1o2");

// Mating
neuralNetwork.mate(neuralNetworkA.getEncoding(), neuralNetworkB.getEncoding());

System.out.println(neuralNetwork.getEncoding());
// output: i1w1.0h1i2w1.0h2h1w1.115626470054826h3h2w1.0h3h3w1.0o1w1.0o2o1o2

```