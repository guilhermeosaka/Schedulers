package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FixedPriority extends Scheduler {
	private static int debugProcess;
	
	private boolean preemptive;
	
	public FixedPriority(boolean preemptive) {
		this.preemptive = preemptive;
		debugProcess = 0;
	}
 
	@Override
	public void schedule(List<Job> fresh) throws InterruptedException {
		synchronized (this) {
			Job job = runnable.peek();
			//Insere os jobs novos na fila 'runnable'
			runnable = merge(runnable, fresh);
			if (job != null) {
				if (runnable.peek() != job) {
					job.pause(); //Caso alguma job nova tenha maior prioridade, pausar a job em execução
				}
			}
			notify();
		}
	}
	
	public boolean remove(Job job) {
		return runnable.remove(job);
	}
	
	public Queue<Job> merge(Queue<Job> old, List<Job> fresh) {
		//Comparator específico de cada scheduler
		Collections.sort(fresh, new Comparator<Job>() {
			public int compare(Job job1, Job job2) {
				Integer priority1 = job1.getPriority();
				Integer priority2 = job2.getPriority();
				return priority1.compareTo(priority2);
			}
		});
		
		Queue<Job> result = new LinkedList<>();
		
		if (old.isEmpty()) {
			for (Job job : fresh) {
				result.add(job);
				return result;
			}
		}
		
		if (fresh.isEmpty()) {
			return old;
		}
		
		while (!fresh.isEmpty() || !old.isEmpty()) {
			if (fresh.get(0).getPriority() < old.peek().getPriority())
				result.add(fresh.remove(0));
			else
				result.add(old.poll());
			
			if (fresh.isEmpty() || old.isEmpty()) {
				if (fresh.isEmpty()) {
					for (Job job : old) { 
						result.add(job);
						old.remove(job);
					}
				} else if (old.isEmpty()) {
					for (Job job: fresh) {
						result.add(job);
						fresh.remove(job);
					}
				}
			}
			
		}
		
		return result;
	}
}

