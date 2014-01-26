package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FixedPriority extends Scheduler {
	
	public FixedPriority(boolean preemptive) {
		super(preemptive);
	}
	
	@Override
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
		
		while (!fresh.isEmpty() && !old.isEmpty()) {
			if (fresh.get(0).getPriority() < old.peek().getPriority())
				result.add(fresh.remove(0));
			else
				result.add(old.poll());
		}
		

		if (old.isEmpty()) {
			for (Job job : fresh)
				result.add(job);
		}
		
		if (fresh.isEmpty()) {
			for (Job job : old)
				result.add(job);
		}
		
		return result;
	}
	
	public String toString() {
		if (preemptive) 
			return "Por tempo (preemptivo)";
		else
			return "Por tempo (não preemptivo)";
	}
}

