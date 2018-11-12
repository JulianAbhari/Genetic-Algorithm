package com.Julian.GeneticAlgorithm;

import java.util.Arrays;

public class Chromosome {
	private int[] genes;
	private int fitness = 0;

	// The Chromosome takes in a length input, which is now the number of gene
	// elements in the "genes" array
	public Chromosome(int length) {
		genes = new int[length];
	}

	// This allows every chromosome to have a random gene.
	public Chromosome initializeChromosome() {
		for (int i = 0; i < genes.length; i += 1) {
			genes[i] = (int) (Math.random() * 10);
		}
		return this; // Returns Chromosome object
	}

	// Returns the chromosomes Genes, in order to Instantiate the genes and
	// random numbers
	public int[] getGenes() {
		return genes;
	}

	// This return's the FINAL Fitness level
	public int getFitness() {
		fitness = recalculateFitness();
		return fitness;
	}

	public int recalculateFitness() {
		int chromosomeFitness = 0;
		// This calculates the fitness score
		for (int i = 0; i < genes.length; i += 1) {
			if (genes[i] == GeneticAlgorithm.TARGET_CHROMOSOME[i]) {
				chromosomeFitness += 1;
			}
		}
		return chromosomeFitness;
	}

	// This overrides the premade "toString()" method which gets called when a
	// "System.out.println()" is called
	// This is for printing an array, so it only prints out "this.genes" and not
	// some nonesense
	public String toString() {
		return Arrays.toString(this.genes);
	}
}
