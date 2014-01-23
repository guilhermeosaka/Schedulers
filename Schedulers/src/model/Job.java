package model;


public class Job {
	//Lock para sincronizar os jobs
	private Scheduler scheduler;
	
	//Atributos do job
	private final int id;
	private final int spawn;
	private int lifespan;
	private final int priority;
	private int started;
	private int finished;
	private State state;
	private int age;
	private int cycle;
	
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
		this.cycle = 0;
	}
	
	public void run() throws InterruptedException {
		state = State.Running;
		while (state == State.Running) {
			Thread.sleep(1000);
			cycle++;
			if (cycle == lifespan) {
				state = State.Terminated;
				System.out.println("Terminou");
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
	
	public void setState(State state) {
		this.state = state;
	}
	
	public boolean isRunning() {
		if (Watch.getTime() - started > lifespan) {
			this.state = State.Terminated;
			return false;
		} else {
			if (this.state == State.Running)
				return true;
			else
				return false;
		}
	}
	
	public boolean isTerminated() {
		if (this.state == State.Terminated) 
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
	
	public void getOlder(int age) {
		this.age += age;
	}
	
	public void pause() {
		this.state = State.Waiting;
	}
}
