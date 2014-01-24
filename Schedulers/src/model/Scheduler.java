package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Scheduler {
	Job job;
	Queue<Job> runnable;
	List<Job> terminated;
	List<Job> waiting; 
	
	public Scheduler() {
		this.runnable = new LinkedList<>();
		this.terminated = new LinkedList<>();
	}
	
	//Enfileira a job na fila 'runnable'. As interrupções (Job.pause()) dos jobs são realizadas no escopo deste método.
	public abstract void schedule(List<Job> job) throws InterruptedException;
	
	//Obtém o primeiro job da fila 'runnable', e deve excluir se estiver com status de "TERMINATED"
	public Job getJob() throws InterruptedException {
		synchronized (this) {
			while (runnable.isEmpty()) {
				wait();
			}
			
			Job job = runnable.peek();
			
			if (job.isTerminated()) {
				runnable.remove(job);
				job = null;
			}
			
			notify();
			return job;
		}
	}
	
	public boolean isEmpty() {
		return runnable.isEmpty();
	}
	
	public boolean remove(Job job) {
		return runnable.remove(job);
	}
	
	public synchronized Queue<Job> getRunnable() {
		return runnable;
	}
	
	public synchronized List<Job> getTerminated() {
		return terminated;
	}
	
	public abstract Queue<Job> merge(Queue<Job> old, List<Job> fresh);
}
