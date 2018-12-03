package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RankSelection;
import org.uncommons.watchmaker.framework.selection.TruncationSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.util.Arrays;
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
class HelloWorldGeneratorV2 {
    private final double probOfMutation;
    private final double probOfCrossover;
    private String initialString;
    private String targetString;

    HelloWorldGeneratorV2(String initialString, String targetString, double probOfMutation, double probOfCrossover) {
        this.initialString = initialString;
        this.targetString = targetString;
        if (initialString.length() != targetString.length())
            throw new IllegalArgumentException("Both strings need same length");
        this.probOfMutation = probOfMutation;
        this.probOfCrossover = probOfCrossover;
    }

    String generate() {
        CandidateFactory<StringCandidate> factory = new StringCandidateFactory(initialString);

        // evolutionary pipeline
        List<EvolutionaryOperator<StringCandidate>> operators = new LinkedList<>();
        operators.add(new StringCandidateCrossover(new Probability(probOfCrossover)));
        operators.add(new StringCandidateMutation(new Probability(probOfMutation)));
        EvolutionaryOperator<StringCandidate> pipeline = new EvolutionPipeline<>(operators);

        // fitness function
        FitnessEvaluator<StringCandidate> fitnessEvaluator = new StringCandidateEvaluator(targetString);

        // selection strategy
        SelectionStrategy<Object> selectionStrategy = new RankSelection();

        // random generator
        Random random = new MersenneTwisterRNG();

        // evolution engine
        EvolutionEngine<StringCandidate> engine = new GenerationalEvolutionEngine<>(factory, pipeline, fitnessEvaluator, selectionStrategy, random);
        
        // observer
        engine.addEvolutionObserver(data ->
            System.out.printf("Generation %d: %s, %s\n",
                    data.getGenerationNumber(),
                    data.getBestCandidate().getCandidate(),
                    data.getBestCandidate().toString())
        );

        // evolve
        StringCandidate result = engine.evolve(100, 10, new TargetFitness(11, true));
        System.out.println(result.getCandidate());
        return result.getCandidate();
    }
}
