package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestJobFirst extends Scheduler {

	@Override
	public Queue<Job> merge(Queue<Job> old, List<Job> fresh) {
		Queue<Job> sorted = new LinkedList<>();
		
		while(!fresh.isEmpty())// enquanto não estiver vazia
		{
			old.add(fresh.remove(0));
		}
		
		// agora fresh está vazia
		
		while(!old.isEmpty())
		{
			Job jobToPlace = old.poll();// retiro o 1 elemento
			for(Job currentJob : old)
			{
				if(jobToPlace.getLifespan() > currentJob.getLifespan())// supondo q isto seja a duração
				{
					old.add(jobToPlace);
					jobToPlace = currentJob;// aqui faço uma cópia ou uso a referencia do elemento q esta na fila
					old.remove(currentJob);
				}
			}
			sorted.add(jobToPlace);
		}
		
		return sorted;
	}

	public String toString() {
		if (preemptive) 
			return "Por duração (SJF - preemptivo)";
		else
			return "Por duração (SJF - não preemptivo)";
	}
}
