package com.decisionlens.gaexamples.helloworldv2;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
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
public class HelloWorldGeneratorV2 {
    private String initialString;
    private String targetString;

    public HelloWorldGeneratorV2(String initialString, String targetString) {
        this.initialString = initialString;
        this.targetString = targetString;
    }

    public String generate() {
        CandidateFactory<StringCandidate> factory = new StringCandidateFactory(initialString);

        // evolutionary pipeline
        List<EvolutionaryOperator<StringCandidate>> operators = new LinkedList<>();
        operators.add(new StringCandidateCrossover(2));
        operators.add(new StringCandidateMutation(new Probability(0.02)));
        EvolutionaryOperator<StringCandidate> pipeline = new EvolutionPipeline<>(operators);

        // fitness function
        FitnessEvaluator<StringCandidate> fitnessEvaluator = new StringCandidateEvaluator();

        // selection strategy
        SelectionStrategy<Object> selectionStrategy = new TruncationSelection(0.15);

        // random generator
        Random random = new MersenneTwisterRNG();

        // evolution engine
        EvolutionEngine<StringCandidate> engine = new GenerationalEvolutionEngine<>(factory, pipeline, fitnessEvaluator, selectionStrategy, random);
        
        // observer
        engine.addEvolutionObserver(data -> {
            System.out.printf("Generation %d: %s, %s\n",
                    data.getGenerationNumber(),
                    data.getBestCandidate().getCandidate(),
                    data.getBestCandidate().toString());
        });

        // evolve
        StringCandidate result = engine.evolve(100, 3, new TargetFitness(11, true));
        System.out.println(result.getCandidate());
        return result.getCandidate();
    }
}
