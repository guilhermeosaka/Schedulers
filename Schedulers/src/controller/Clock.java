package controller;

import java.util.List;

import model.Job;
import model.Scheduler;

public class Clock extends Thread {

	private Scheduler scheduler;
	private List<Job> jobList;
	
	public Clock(Scheduler scheduler, List<Job> jobList) {
		this.scheduler = scheduler;
		this.jobList = jobList;
	}
	
	public void run() {
		
	}
}
