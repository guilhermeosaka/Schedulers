package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Scheduler {
	boolean preemptive;
	Job job;
	Queue<Job> runnable;
	
	public Scheduler() {
		this.runnable = new LinkedList<>();
		this.preemptive = false;
	}
	
	public Scheduler(boolean preemptive) {
		this.runnable = new LinkedList<>();
		this.preemptive = preemptive;
	}
	
	//Enfileira a job na fila 'runnable'. As interrupções (Job.pause()) dos jobs são realizadas no escopo deste método.
	//public abstract void schedule(List<Job> job) throws InterruptedException;
	
	public void schedule(List<Job> fresh) throws InterruptedException {
		synchronized (this) {
			Job job = runnable.peek();
			//Insere os jobs novos na fila 'runnable'
			runnable = merge(runnable, fresh);
			if (job != null && preemptive) {
				if (runnable.peek() != job) {
					job.pause(); //Caso alguma job nova tenha maior prioridade, pausar a job em execução
				}
			}
			notify();
		}
	}
	
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
	
	public abstract Queue<Job> merge(Queue<Job> old, List<Job> fresh);
}
