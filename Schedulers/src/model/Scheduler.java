package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Scheduler {
	Job job;
	List<Job> runnable;
	List<Job> terminated;
	List<Job> waiting; 
	
	public Scheduler() {
		this.runnable = new LinkedList<>();
		this.terminated = new LinkedList<>();
	}
	
	public abstract void schedule(Job job) throws InterruptedException;
	
	public abstract Job getJob() throws InterruptedException;
	
	public abstract boolean isEmpty();
	
	public boolean remove(Job job) {
		return runnable.remove(job);
	}
	
	public synchronized List<Job> getRunnable() {
		return runnable;
	}
	
	public synchronized List<Job> getTerminated() {
		return terminated;
	}
}
