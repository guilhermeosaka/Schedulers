package model;

import java.util.List;
import java.util.Queue;

public class FirstComeFirstServed extends Scheduler {

	@Override
	public Queue<Job> merge(Queue<Job> old, List<Job> fresh) {
		while(!fresh.isEmpty())// enquanto não estiver vazia
		{
			old.add(fresh.remove(0));
		}
		
		return old;
	}
	
	@Override
	public String toString() {
		return "Por chegada";
	}
}
