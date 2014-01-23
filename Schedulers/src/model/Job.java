package model;

import controller.Controller;


public class Job {
	//Lock para sincronizar os jobs
	private Scheduler scheduler;
	
	//Atributos
	private final int id;
	private final int spawn;
	private int lifespan;
	private final int priority;
	
	//Propriedades
	private int birth;
	private int died;
	private int age;
	private State state;
	
	public enum State {
		New,
		Running,
		Waiting,
		Terminated
	}
	
	public Job(int id, int spawn, int lifespan, int priority) {
		super();
		this.id = id;
		this.spawn = spawn;
		this.lifespan = lifespan;
		this.priority = priority;
		this.age = 0;
	}
	
	public void run() throws InterruptedException {
		if (state == State.New){
			birth = Watch.getTime();
		}
		state = State.Running;
		while (state == State.Running) {
			Thread.sleep(Watch.getAmount() + Controller.SYNCHRONIZE);
			age++;
			if (age == lifespan) {
				state = State.Terminated;
				died = Watch.getTime();
			}
		}
	}
	
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	public int getSpawn() {
		return this.spawn;
	}
	
	public int getId() {
		return this.id;
	}

	public int getLifespan() {
		return this.lifespan;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public int getBirth() {
		return this.birth;
	}
	
	public int getDied() {
		return this.died;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public boolean isNew() {
		if (this.state == State.New)
			return true;
		else
			return false;
	}
	
	public boolean isRunning() {
		if (this.state == State.Running)
			return true;
		else
			return false;
	}

	public boolean isWaiting() {
		if (this.state == State.Waiting)
			return true;
		else
			return false;		
	}
	
	public boolean isTerminated() {
		if (this.state == State.Terminated) 
			return true;
		else 
			return false;
	}
	
	public void pause() {
		this.state = State.Waiting;
	}
}
