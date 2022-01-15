import random
import math
import copy

# Parent selection functions---------------------------------------------------
def uniform_random_selection(population, n, **kwargs):
	# TODO: select n individuals uniform randomly
		uniform_population = []
		for i in range(n):   
			uniform_population.append(random.choice(population))
		return uniform_population

def k_tournament_with_replacement(population, n, **kwargs):
	# TODO: perform n k-tournaments with replacement to select n individuals
	if 'k' in kwargs.keys():
		k = kwargs['k']
	else:
		k = 2
	#throw exception if k is out of range
	if k > len(population):
		raise Exception("error")
	new_population = []
	#pick random k individual to compare, add the winner in parent population
	for i in range(n):
		k_ind = random.sample(population,k=k)

		best = sorted(k_ind, key=lambda x:x.fitness)[-1]
		new_population.append(best)

	return new_population
 
    
def fitness_proportionate_selection(population, n, **kwargs):
	# TODO: select n individuals using fitness proportionate selection
	proportion_population = [] 
	Dict = {}    
	minFitness = min([individual.fitness for individual in population])
	sub = 1.5 * minFitness
	circum = []
	sum = 0
#assign proportion to every individual
	for idx,individual in enumerate(population):
		x = individual.fitness - sub
		d = {individual : x}
		Dict.update(d)
		sum += x
		circum.append(sum)
	circum2 = []
	for ind,item in enumerate(circum):
		circum2.append(item*1.0/sum)
	proportion_population2 = []
	#randomly have a number between 0 to 1, if the number smaller than proportion, add the individual into parent list.    
	for i in range(n):
		r = random.random()
		ii = 0
		while r>circum2[ii] and ii<len(circum2):
			ii+=1
		proportion_population.append(population[ii])
		proportion_population2.append(ii)
	return proportion_population
        
# Survival selection functions-------------------------------------------------
def truncation(population, n, **kwargs):
	# TODO: perform truncation selection to select n individuals 
	#check for duplicate, only copy unique items to new list.
	temp_pop = []    
	for x in population:
		if x not in temp_pop:
			temp_pop.append(x)
	population = temp_pop     
	truncation_population = []    
	Dict = {}
	#pair fitness with idividual    
	for individual in population:        
		d = {individual : individual.fitness}
		Dict.update(d) 
	#from high to low, sort individuals by fitness
	sort_order = sorted(Dict.items(), key=lambda x: x[1], reverse=True)
	#select the child when there's position avaliable    
	for individual in sort_order:
			if len(truncation_population) < n:       
				truncation_population.append(individual[0])        
	return truncation_population

def k_tournament_without_replacement(population, n, **kwargs):
	# TODO: perform n k-tournaments without replacement to select n individuals
	#		Note: an individual should never be cloned from surviving twice!

	if 'k' in kwargs.keys():
		k = kwargs['k']
	else:
		k = 2
	#throw exception if k large than range
	if k>len(population):
		raise Exception("error")
#geting child until meet n
	new_population = []
	number = 0
	while True:
		if number==n:
			break
#choose k individual,compare them and let the individual with best fitness survive. 
		population_sample = []
		for item in population:
			if item not in new_population:
				population_sample.append(item)

		k_ind = random.sample(population_sample, k=k)

		best = sorted(k_ind, key=lambda x: x.fitness)[-1]

		number+=1
		new_population.append(best)
	return new_population


# Yellow deliverable parent selection function---------------------------------
def stochastic_universal_sampling(population, n, **kwargs):
	# Recall that yellow deliverables are required for students in the grad
	# section but bonus for those in the undergrad section.
	# TODO: select n individuals using stochastic universal sampling
	pass
