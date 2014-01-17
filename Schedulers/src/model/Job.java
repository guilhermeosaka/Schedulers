package model;

public class Job {
	private int priority;
	private int lifespan;
	
	public Job(int priority, int lifespan) {
		this.priority = priority;
		this.lifespan = lifespan;
	}
	
	public void decreaseLifespan(int elapsed) {
		this.lifespan -= elapsed;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public int getLifespan() {
		return this.lifespan;
	}
}
