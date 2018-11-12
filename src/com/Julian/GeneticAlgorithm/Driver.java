package com.Julian.GeneticAlgorithm;

import java.util.Arrays;

public class Driver {
	public static void main(String[] args) {
		Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

		System.out.println("------------------------------------------------");
		System.out.println(
				"Generation # 0 " + " | Fittest chromosome fitness: " + population.getChromosomes()[0].getFitness());
		printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));

		// This calls the evolve(population) method, sorts the chromosomes and
		// prints out the generation number
		// along with the target chromosome
		int generationNumber = 0;
		while (population.getChromosomes()[0].getFitness() < GeneticAlgorithm.TARGET_CHROMOSOME.length) {
			generationNumber += 1;
			System.out.println("------------------------------------------------");
			population = geneticAlgorithm.evolve(population);
			population.sortChromosomesByFitness();

			System.out.println("Generation # " + generationNumber + " | Fittest chromosome fitness: "
					+ population.getChromosomes()[0].getFitness());
			printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));

		}
	}

	// This prints out the population in order of chromosomes
	public static void printPopulation(Population population, String heading) {
		System.out.println(heading);
		System.out.println("------------------------------------------------");
		for (int i = 0; i < population.getChromosomes().length; i += 1) {
			System.out.println("Chromosome # " + i + "  : " + Arrays.toString(population.getChromosomes()[i].getGenes())
					+ " | Fitness: " + population.getChromosomes()[i].getFitness());
		}
	}
}
