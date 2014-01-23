package controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Job;
import model.Scheduler;
import model.Watch;

public class Clock extends Thread {
	private Scheduler scheduler;
	private List<Job> jobList;
	
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
		
		this.jobList = jobList;
		
		for (Job job : jobList) {
			job.setScheduler(scheduler);
		}
	}
	
	public void run() {
		synchronized (this) {
			while (!jobList.isEmpty()) { 
				try {
					Job job = jobList.remove(0);
					int sleepTime = job.getSpawn() * 1000 - Watch.getTime();
					if (sleepTime < 0)
						sleepTime = 0;
					sleep(sleepTime);
					scheduler.schedule(job);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				notifyAll();
			}
		}
	}
}
