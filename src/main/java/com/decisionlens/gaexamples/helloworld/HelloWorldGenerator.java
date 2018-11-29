package com.decisionlens.gaexamples.helloworld;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.factories.StringFactory;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.StringCrossover;
import org.uncommons.watchmaker.framework.operators.StringMutation;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.selection.TruncationSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Generate HELLO WORLD string using WatchMaker EvolutionEngine
 * The initial population is 10 randomly generated strings using the
 * 26 upper case letters and a space character.
 *
 * Use HelloWorldGeneratorTest to run.
 *
 */
public class HelloWorldGenerator {

    public String generate() {
        // initial population
        char[] chars = new char[27];
        for(char c = 'A'; c <= 'Z'; c++) {
            chars[c - 'A'] = c;
        }
        chars[26] = ' ';
        CandidateFactory<String> factory = new StringFactory(chars, 11);

        // evolutionary pipeline
        List<EvolutionaryOperator<String>> operators = new LinkedList<EvolutionaryOperator<String>>();
        operators.add(new StringCrossover());
        operators.add(new StringMutation(chars, new Probability(0.02)));
        EvolutionaryOperator<String> pipeline = new EvolutionPipeline<>(operators);

        // fitness function
        FitnessEvaluator<String> fitnessEvaluator = new StringEvaluator();

        // selection strategy
        SelectionStrategy<Object> selectionStrategy = new TruncationSelection(0.15);

        // random generator
        Random random = new MersenneTwisterRNG();

        // evolution engine
        EvolutionEngine<String> engine = new GenerationalEvolutionEngine<>(factory, pipeline, fitnessEvaluator, selectionStrategy, random);
        
        // observer
        engine.addEvolutionObserver(data -> {
            System.out.printf("Generation %d: %s\n",
                    data.getGenerationNumber(),
                    data.getBestCandidate());
        });

        // evolve
        String result = engine.evolve(10, 0, new TargetFitness(11, true));
        System.out.println(result);
        return result;
    }
}
