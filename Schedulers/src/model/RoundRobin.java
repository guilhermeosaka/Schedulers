package model;

import java.util.List;

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
	public synchronized Job getJob() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
