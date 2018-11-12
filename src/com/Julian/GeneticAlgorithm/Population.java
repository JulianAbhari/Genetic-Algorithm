package com.Julian.GeneticAlgorithm;

public class Population {
	// chromosomes is an Array of the Chromosome objects
	private Chromosome[] chromosomes;

	// The population object takes a population size, so it is the size of the
	// 'chromosomes' array
	public Population(int length) {
		chromosomes = new Chromosome[length];
	}

	// This initializes the population, so that it's filled with initialized
	// Chromosomes
	public Population initializePopulation() {
		// The 'chromosomes' array is filled with Chromosome objects which take
		// a length from the 'GeneticAlgorithm' class
		// The chromosomes then call their 'initializeChromosome()' method
		// The 'chromosomes' array is then sorted, and the Population is
		// returned.
		for (int i = 0; i < chromosomes.length; i += 1) {
			chromosomes[i] = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length).initializeChromosome();
		}
		sortChromosomesByFitness();
		return this;
	}

	// Chromosome object array method which returns the 'chromosomes' array
	public Chromosome[] getChromosomes() {
		return chromosomes;
	}

	// This is a simple bubble sort algorithm sorting from greatest to least.
	// What's going on is that first a for loop of int h has to run these two
	// other for loops to sort every index of the array
	// The array in this case is the 'chromosomes' array. In for loop of int i
	// it starts at the end of the array
	// to see if any value before that (at index j) might be smaller (might have
	// a worse fitness score)
	// in for loop of int j it stops at i because in for loop at i it has
	// already run j to see if index i is the smallest fitness score
	// basically the reason j stops at i is because i is already sorted so it
	// doesn't need to test the other values.
	// in for loop of int j it tests to see if the fitness score of j is < the
	// fitness score of i.
	// If the condition is true, it swaps the values.
	public void sortChromosomesByFitness() {
		for (int i = chromosomes.length - 1; i > 0; i -= 1) {
			for (int j = 0; j < i; j += 1) {
				// The condition tests to see if the fitness score of j is <
				// the fitness score i. If the condition is true
				// it has to swap the values by storing one of the values in
				// a temporary variable.
				// I would like to point out that to be 100% certain
				// that there will be no errors,
				// you might want to TEST j and i
				// but you'd want to SWAP j and j + 1, not j and i.
				if (chromosomes[j].getFitness() < chromosomes[i].getFitness()) {
					Chromosome temp = chromosomes[j];
					chromosomes[j] = chromosomes[i];
					chromosomes[i] = temp;
				}
			}
		}
	}
}
