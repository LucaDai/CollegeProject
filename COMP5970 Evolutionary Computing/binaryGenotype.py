import random
from random import randint
import math
class binaryGenotype():
	def __init__(self):
		self.fitness = None
		self.gene = None

	def randomInitialization(self, length):
		# TODO: Add random initialization of fixed-length binary gene
		list = []
		for i in range(length):
			num = randint(0, 1)
			list.append(num)
		self.gene = list
		return list
    
	def gene(self):
		return self

	def recombine(self, mate, method, **kwargs):
		child = binaryGenotype()
		
		# TODO: Recombine genes of self with mate and assign to child's gene member variable
		assert method.casefold() in {'uniform', '1-point crossover', 'multi-dimensional'}
		if method.casefold() == 'uniform':
			# perform uniform recombination
			child_gene = []            
			for index in range(len(self.gene)):            
				if(randint(0, 1) == 0):            
					child_gene.append(self.gene[index])             
				else:
					child_gene.append(mate.gene[index])
			child.gene = child_gene                    
		elif method.casefold() == '1-point crossover':
			# perform 1-point crossover
			index = random.randint(1,len(self.gene)-1)         
			if(randint(0, 1) == 0):            
				child.gene = self.gene[:index] + mate.gene[index:]            
			else:
				child.gene = mate.gene[:index] + self.gene[index:]    
		elif method.casefold() == 'multi-dimensional':
			# this is a red deliverable (i.e., bonus for anyone)
			
			height, width = kwargs['height'], kwargs['width']
			# transform the linear gene of both parents to a 2-dimensional representation.
			# Recombine 2D parent genes into 2D child gene using the method of your choice.
			# Convert child gene back down to 1-dimension.
			pass

		return child

	def mutate(self, **kwargs):            
		copy = binaryGenotype()
		copy.gene = self.gene.copy()
		
		# TODO: mutate gene of copy
        
		if 'val' in kwargs:
			val = kwargs['val']
		else:
			val = 0.2
		#random generate a rate between 0 to 1, compare it with mutation rate.
		for i in range(len(copy.gene)):
			if randint(0, 100)/100 <= val:                  
				copy.gene[i] = abs(copy.gene[i] - 1)        
		return copy

	@classmethod
	def initialization(cls, mu, *args, **kwargs):
		population = [cls() for _ in range(mu)]
		for i in range(len(population)):
			population[i].randomInitialization(*args, **kwargs)
		return population