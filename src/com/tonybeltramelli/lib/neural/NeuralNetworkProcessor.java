package com.tonybeltramelli.lib.neural;

import com.tonybeltramelli.lib.util.RegExp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 30/04/2014
 */
public class NeuralNetworkProcessor
{
    public String merge(String dna1, String dna2)
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
            }else if(dnaGroup.indexOf(Encodable.HIDDEN) != -1)
            {
                dnaGroup = dnaGroup.substring(0, dnaGroup.indexOf(Encodable.HIDDEN) + 1) + String.valueOf(Integer.parseInt(dnaGroup.substring(dnaGroup.indexOf(Encodable.HIDDEN) + 1, dnaGroup.indexOf(Encodable.WEIGHT))) + leftNetwork.getHiddenNeuronNumber()) + dnaGroup.substring(dnaGroup.indexOf(Encodable.WEIGHT), dnaGroup.length());

                dna1Groups.add(dnaGroup);

                counter = dnaMatcher.end();
            }
        }

        String mergedDna = "";

        for(int i = 0; i < dna1Groups.size(); i++)
        {
            mergedDna += dna1Groups.get(i);
        }

        mergedDna += dna1.substring(counter, dna1.length());

        return mergedDna;
    }
}
