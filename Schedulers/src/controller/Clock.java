package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.Job;
import model.Scheduler;
import model.Watch;

public class Clock extends Thread {
	private Scheduler scheduler;
	private List<Job> jobs;
	
	public Clock(Scheduler scheduler, List<Job> jobList) {
		this.scheduler = scheduler;
		
		//Ordena a jobList através do atributo spawn
		Collections.sort(jobList, new Comparator<Job>() {
			public int compare(Job job1, Job job2) {
				Integer spawn1 = job1.getSpawn();
				Integer spawn2 = job2.getSpawn();
				return spawn1.compareTo(spawn2);
			}
		});
		
		jobs = jobList;
	}
	
	public void run() {
		while (!jobs.isEmpty()) {
			try {
				scheduler.schedule(spawn(Watch.getTime()));
				sleep(Watch.getAmount());
			} catch (InterruptedException e) {
				System.out.println("ERRO");
			}
		}
	}
	
	public List<Job> spawn(int seconds) {
		boolean stop = false;
		List<Job> fresh = new LinkedList<>();
		while (!stop && !jobs.isEmpty()) {
			if (jobs.get(0).getSpawn() == seconds)
				fresh.add(jobs.remove(0));
			else
				stop = true;
		}
		
		return fresh;
	}
}
