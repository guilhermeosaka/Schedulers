package controller;

import java.util.LinkedList;
import java.util.List;

import model.Job;
import model.Scheduler;
import model.Watch;

public class Processor extends Thread {
	private Scheduler scheduler;
	private Job job;
	private List<Job> terminated;
	
	private boolean finished;
	
	public Processor(Scheduler scheduler) {
		this.scheduler = scheduler;
		this.finished = false;
		this.terminated = new LinkedList<>();
	}
	
	public void run() {
		while(!finished) {
			try {
				job = scheduler.getJob();
				if (job != null) {
					job.run();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void finish(String message) {
		//Grava log
		finished = true;
	}
	
	public Job getJob() {
		return job;
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
}

