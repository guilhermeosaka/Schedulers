package model;

import controller.Controller;


public class Job {
	//Atributos
	private final int id;
	private final int spawn;
	private final int lifespan;
	private final int priority;
	
	//Propriedades
	private int birth;
	private int died;
	private int age;
	private State state;
	
	//Estados que a job pode possuir
	public enum State {
		NEW,
		RUNNING,
		WAITING,
		TERMINATED
	}
	
	//Construtor passando os atributos da job
	public Job(int id, int spawn, int lifespan, int priority) {
		super();
		this.id = id;
		this.spawn = spawn;
		this.lifespan = lifespan;
		this.priority = priority;
		this.age = 0;
		state = State.NEW;
	}
	
	//Método invocado pelo 'processor' para 'executar' a job
	public void run() throws InterruptedException {
		if (state == State.NEW) {
			birth = Watch.getTime();
		}
		state = State.RUNNING;
		while (state == State.RUNNING) {
			System.out.println(Watch.getReal() + " - Rodando " + id);
			Thread.sleep(Watch.getAmount() + Controller.SYNCHRONIZE);
			age++;
			if (age == lifespan) {
				state = State.TERMINATED;
				died = Watch.getTime();
			}
		}
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
		if (this.state == State.NEW)
			return true;
		else
			return false;
	}
	
	public boolean isRunning() {
		if (this.state == State.RUNNING)
			return true;
		else
			return false;
	}

	public boolean isWaiting() {
		if (this.state == State.WAITING)
			return true;
		else
			return false;		
	}
	
	public boolean isTerminated() {
		if (this.state == State.TERMINATED) 
			return true;
		else 
			return false;
	}
	
	//Simula uma interrupção
	public void pause() {
		this.state = State.WAITING;
	}
}
