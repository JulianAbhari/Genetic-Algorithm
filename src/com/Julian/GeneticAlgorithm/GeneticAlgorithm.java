package com.Julian.GeneticAlgorithm;

import com.Julian.math.JMath;

public class GeneticAlgorithm {
	public static final int POPULATION_SIZE = 8;
	public static final int[] TARGET_CHROMOSOME = { 1, 0, 2, 8, 9, 1, 1, 6, 0, 0 };
	public static final float MUTATION_RATE = (float) 0.1;
	public static final int NUMB_OF_ELITE_CHROMOSOMES = 0;
	public static final int TOURNAMENT_SELECTION_SIZE = 4;
	// This maxFitness variable is used to see what the best fitness in the
	// population is, it will be instantiated in the crossoverPopulation, and
	// used in the 'pickChromoByFitness' function.
	public static float maxFitness = 0;

	// This calls a mutatedPopulation with a crossoverPopulation inside
	public Population evolve(Population population) {
		return mutatePopulation(crossoverPopulation(population));
		// This applies mutation to the crossoverPopulation
	}

	// This is a crossover population which crossovers the chromosomes except
	// for the ELITE_CHROMOSOMES
	// It gets the chromosomes from the selectTournamentPopulation
	private Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getChromosomes().length);
		// This is setting the max fitness to 0 so it's cleared whenever a new
		// population has started. The reason for this is because a population
		// might go down in highest fitness.
		maxFitness = 0;
		// This is looping through the population and setting the maxFitness to
		// the highest fitness found in the population. The maxFitness will be
		// used in the pickChromoByFitness function.
		for (int i = 0; i < population.getChromosomes().length; i += 1) {
			if (population.getChromosomes()[i].getFitness() > maxFitness) {
				maxFitness = population.getChromosomes()[i].getFitness();
			}
		}
		for (int i = 0; i < NUMB_OF_ELITE_CHROMOSOMES; i += 1) {
			crossoverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}
		for (int i = NUMB_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i += 1) {
			Chromosome chromosome1 = matingPoolByFitness(population).getChromosomes()[0];
			Chromosome chromosome2 = matingPoolByFitness(population).getChromosomes()[0];
			crossoverPopulation.getChromosomes()[i] = crossoverChromosome(chromosome1, chromosome2);
		}
		return crossoverPopulation;
	}

	// This mutates the population that it takes in, and mutates every
	// chromosome except for the ELITE_CHROMOSOME
	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getChromosomes().length);
		for (int i = 0; i < NUMB_OF_ELITE_CHROMOSOMES; i += 1) {
			mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}
		for (int i = NUMB_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i += 1) {
			mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
		}
		return mutatePopulation;
	}

	// This takes two chromosomes and blends them together by randomly choosing
	// to take genes from chromosome 1 or 2
	private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
		Chromosome crossoverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
		for (int i = 0; i < chromosome1.getGenes().length; i += 1) {
			if (Math.random() < 0.5) {
				crossoverChromosome.getGenes()[i] = chromosome1.getGenes()[i];
			} else {
				crossoverChromosome.getGenes()[i] = chromosome2.getGenes()[i];
			}
		}
		return crossoverChromosome;
	}

	// This randomly mutates the genes in the chromosomes
	private Chromosome mutateChromosome(Chromosome chromosome) {
		Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);
		for (int i = 0; i < chromosome.getGenes().length; i += 1) {
			if (Math.random() <= MUTATION_RATE) {
				mutateChromosome.getGenes()[i] = (int) (Math.random() * 10);
			} else {
				mutateChromosome.getGenes()[i] = chromosome.getGenes()[i];
			}
		}
		return mutateChromosome;
	}

	private Population matingPoolByFitness(Population population) {
		Population matingPool = new Population(TOURNAMENT_SELECTION_SIZE);
		for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i += 1) {
			Chromosome randomChromosome = population.getChromosomes()[0];
			float randomNumber = JMath.map((float) Math.random(), 0, 1, 0, maxFitness);

			while (true) {
				if (randomNumber <= randomChromosome.getFitness()) {
					matingPool.getChromosomes()[i] = randomChromosome;
					break;
				}
				System.out.println("Max Fitness: " + maxFitness + " | Random Number: " + randomNumber
						+ " | Chromosome's Fitness: " + randomChromosome.getFitness());
				randomChromosome = population.getChromosomes()[0];
			}
		}
		matingPool.sortChromosomesByFitness();
		return matingPool;
	}
}
