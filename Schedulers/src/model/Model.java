package model;

import java.util.List;

public class Model {
	private Scheduler firstComeFirstServed;
	private Scheduler shortestJobFirst;
	private Scheduler fixedPriority;
	private Scheduler fixedPriorityPreemptive;
	private Scheduler roundRobin;
	
	public Model() {
		firstComeFirstServed = new FirstComeFirstServed();
		shortestJobFirst = new ShortestJobFirst();
		fixedPriority = new FixedPriority(false);
		fixedPriorityPreemptive = new FixedPriority(true);
		roundRobin = new RoundRobin();
	}

	public Scheduler getFirstComeFirstServed() {
		return firstComeFirstServed;
	}

	public Scheduler getShortestJobFirst() {
		return shortestJobFirst;
	}

	public Scheduler getFixedPriority() {
		return fixedPriority;
	}

	public Scheduler getFixedPriorityPreemptive() {
		return fixedPriorityPreemptive;
	}

	public Scheduler getRoundRobin() {
		return roundRobin;
	}
	
	public List<Job> getJobList() {
		return null; //Lê um arquivo conforme o requisito
	}
}
