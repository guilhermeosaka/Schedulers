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
		
		while(!fresh.isEmpty())// enquanto n�o estiver vazia
		{
			old.add(fresh.remove(0));
		}
		
		// agora fresh est� vazia
		
		while(!old.isEmpty())
		{
			Job jobToPlace = old.poll();// retiro o 1 elemento
			for(Job currentJob : old)
			{
				if(jobToPlace.getLifespan() > currentJob.getLifespan())// supondo q isto seja a dura��o
				{
					old.add(jobToPlace);
					jobToPlace = currentJob;// aqui fa�o uma c�pia ou uso a referencia do elemento q esta na fila
					old.remove(currentJob);
				}
			}
			sorted.add(jobToPlace);
		}
		
		return sorted;
	}

	public String toString() {
		if (preemptive) 
			return "Por dura��o (SJF - preemptivo)";
		else
			return "Por dura��o (SJF - n�o preemptivo)";
	}
}
