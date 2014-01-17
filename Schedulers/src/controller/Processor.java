package controller;

import java.util.ArrayList;
import java.util.List;

import model.Job;
import model.Scheduler;

public class Processor extends Thread {
	private Scheduler scheduler;
	private List<Job> jobList;
	
	public Processor(Scheduler scheduler, List<Job> jobList) {
		this.scheduler = scheduler;
		this.jobList = jobList;
	}
	
	public void run() {
		scheduler.start();
		
		
	}
}
