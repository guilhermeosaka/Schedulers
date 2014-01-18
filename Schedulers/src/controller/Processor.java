package controller;

import java.util.ArrayList;
import java.util.List;

import model.Job;
import model.Scheduler;

public class Processor extends Thread {
	private Clock clock;
	private List<Job> jobList;
	
	public Processor(Clock clock) {
		this.clock = clock;
	}
	
	public void run() {
		
	}
}
