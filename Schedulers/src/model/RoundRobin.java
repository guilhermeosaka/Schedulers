package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin extends Scheduler {
	private boolean priority;
	private int quantum;
	
	private int currentQuantum;

	public RoundRobin(boolean priority, int quantum) {
		super();
		this.priority = priority;
		this.quantum = quantum;
		this.currentQuantum = 0;
	}

	@Override
	public Queue<Job> merge(Queue<Job> old, List<Job> fresh) {
		while(!fresh.isEmpty())// enquanto não estiver vazia
		{
			old.add(fresh.remove(0));
		}
		
		if (currentQuantum == quantum) {
			Job job = old.poll();
			if (job != null) job.pause();
			old.add(job);
			currentQuantum = 0;
		}
		
		currentQuantum++;
		return old;
	}
	
	@Override
	public Job getJob() throws InterruptedException {
		synchronized (this) {
			while (runnable.isEmpty()) {
				wait();
			}
			
			Job job = runnable.peek();
			
			if (job != null && job.isTerminated()) {
				runnable.remove(job);
				job = null;
				currentQuantum = 1;
			}
			
			notify();
			return job;
		}
	}
	
	@Override
	public String toString() {
		if (priority)
			return "Round Robin (Por prioridade - Quantum: " + quantum + ")";
		else
			return "Round Robin (Quantum: " + quantum + ")";
	}
}
