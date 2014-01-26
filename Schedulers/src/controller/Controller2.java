package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Job;
import model.Model;
import model.Scheduler;
import model.Watch;
import view.View;

public class Controller2 extends Thread {
	private Model model;
	private View view;
	public final static int SYNCHRONIZE = 5;
	
	private List<Processor> processor;
	private List<Clock> clock;
	
	public Controller2(Model model, View view) {
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
		
		Watch.start();
		processor.get(3).start();
		clock.get(3).start();
	}
	
	public void run() {
		while (true) {
			
			try {
				sleep(10);
				System.out.print(Watch.getTime() + " - ");
				String message;
				if (processor.get(3).getJob() != null) 
					message = Integer.toString(processor.get(3).getJob().getId());
				else
					message = "Não há processos";
				System.out.println(message);
				
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
	
	public void pause() {
		
	}
}
