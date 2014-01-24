package model;

import java.util.List;
import java.util.Queue;

public class RoundRobin extends Scheduler {
	private boolean byPriority;
	private int quantum;

	public RoundRobin(boolean byPriority, int quantum) {
		this.byPriority = byPriority;
		this.quantum = quantum;
	}

	@Override
	public synchronized void schedule(List<Job> fresh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Queue<Job> merge(Queue<Job> old, List<Job> fresh) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
