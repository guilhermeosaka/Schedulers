package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.Timer;

import model.Job;
import model.Model;
import model.Scheduler;
import model.Watch;
import view.View;


public class Controller extends JApplet implements ActionListener {

	public final static int SYNCHRONIZE = 5;
	
	Model model;
	View view;
	List<Processor> processors;
	List<Clock> clocks;
	Timer timer;
	
	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public void init() {
		model = new Model();
		view = new View();
		
		try {
			processors = new ArrayList<>();
			clocks = new ArrayList<>();
			String path = "C:\\Users\\Guilherme\\git\\SchedulersRepository\\Schedulers\\repository\\jobs.txt";
			for (Scheduler scheduler : model.getSchedulerList()) {
				//Gera a lista de jobs a partir do arquivo (repository/jobs.txt)
				List<Job> jobList = model.getJobList(path);
				
				clocks.add(new Clock(scheduler, jobList));
				processors.add(new Processor(scheduler));
			}
		} catch (IOException e) {
			//Grava log e mostra na tela
			System.out.println(e.getMessage());
		}
		
		setLayout(new BorderLayout());
		
		add(view, BorderLayout.CENTER);
		
		System.out.println("init");
		stop();
	}

	@Override
	public void start() {
		timer = new Timer(19, this);
		
		Watch.start();
		for (int i = 0; i < 6; i++) {
			processors.get(i).start();
			clocks.get(i).start();
		}
		timer.start();
		
		System.out.println("start");
	}

	@Override
	public void stop() {
		System.out.println("stop");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!view.isFinished()) {
			view.update(processors);
		} else { 
			//terminou!
		}
	
	}
}
