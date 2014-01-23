package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;

public class FixedPriority extends Scheduler {
	private static int debugProcess;
	
	private boolean preemptive;
	
	public FixedPriority(boolean preemptive) {
		this.preemptive = preemptive;
		debugProcess = 0;
	}

	@Override
	public void schedule(Job job) throws InterruptedException {
		synchronized (this) {
			if (!runnable.isEmpty()) {
				if (job.getPriority() < runnable.get(0).getPriority()) {
					runnable.get(0).pause();
				}
			}		
			
			if (preemptive) {
				runnable.add(job);
				Collections.sort(runnable, new Comparator<Job>() {
					public int compare(Job job1, Job job2) {
						Integer priority1 = job1.getPriority();
						Integer priority2 = job2.getPriority();
						return priority1.compareTo(priority2);
					}
				});
				
				notify();
			}
		}
	}

	@Override
	public Job getJob() throws InterruptedException {
		synchronized (this) {
			if(preemptive) {
				while (runnable.isEmpty()) {
					wait();
				}
				
				Job job = runnable.get(0);
				if (job.isTerminated()) {
					runnable.remove(job);
					job = null;
				}
				
				notify();
				return job;
			} else {
				return null;
			}
		}
	}
	
	public boolean remove(Job job) {
		return runnable.remove(job);
	}

	@Override
	public boolean isEmpty() {
		return runnable.isEmpty();
	}
}

