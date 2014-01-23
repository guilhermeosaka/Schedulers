package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Job;
import model.Model;
import model.Scheduler;
import model.Watch;
import view.View;

public class Controller extends Thread {
	public final static int SYNCHRONIZE = 50;
	private Model model;
	private View view;
	
	private List<Processor> processor;
	private List<Clock> clock;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		try {
			processor = new ArrayList<>();
			clock = new ArrayList<>();
			String path = "repository/jobs.txt";
			for (Scheduler scheduler : model.getSchedulerList()) {
				//Gera a lista de jobs a partir do arquivo (repository/jobs.txt)
				List<Job> jobList = model.getJobList(path);
				
				clock.add(new Clock(scheduler, jobList));
				processor.add(new Processor(scheduler));
			}
		} catch (IOException e) {
			//Grava log e mostra na tela
			System.out.println(e.getMessage());
		}
	}
	
	public void run() {
		Watch.start();
		processor.get(3).start();
		clock.get(3).start();
		while (true) {
			StringBuilder log = new StringBuilder();
			log.append("[");
			log.append(displayTime(Watch.getTime()));
			log.append("] - ");
			
			try {
				sleep(SYNCHRONIZE);
				
				Job job = processor.get(3).getJob();
				
				if (job == null) {
					log.append("Não há processos");
				} else {
					log.append("Processando Job " + job.getId());
				}
				
				System.out.println(log);
				sleep(Watch.getAmount());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void startAll() {
		Watch.start();
		for (Thread c : clock)
			c.start();
		
		for (Thread p : processor)
			p.start();
	}
	
	public String displayTime(int time) {
		if (time == 0)
			return "0:00";
		
		int seconds = time % 60;
		int minutes = time / 60;
		
		StringBuilder result = new StringBuilder();
		
		result.append(minutes);
		result.append(":");
		if (seconds < 10)
			result.append("0" + seconds);
		else
			result.append(seconds);
		
		return result.toString();
	}
}
