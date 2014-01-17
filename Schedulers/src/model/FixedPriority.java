package model;

public class FixedPriority extends Scheduler {
	private boolean preemtive;
	
	public FixedPriority(boolean preemptive) {
		this.preemtive = preemptive;
	}
	
	public void run() {
		//Consome os job da jobList
	}	
}
