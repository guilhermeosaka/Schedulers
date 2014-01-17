package controller;

import java.util.Arrays;
import java.util.List;

import model.FirstComeFirstServed;
import model.FixedPriority;
import model.Job;
import model.Model;
import model.RoundRobin;
import model.ShortestJobFirst;
import view.View;

public class Controller {
	private List<Processor> processorList;
	
	public Controller(Model model, View view) {
		processorList = Arrays.asList( new Processor[] { new Processor(model.getFirstComeFirstServed(), model.getJobList()),
														 new Processor(model.getShortestJobFirst(), model.getJobList()),
														 new Processor(model.getFixedPriority(), model.getJobList()),
														 new Processor(model.getFixedPriorityPreemptive(), model.getJobList()),
														 new Processor(model.getRoundRobin(), model.getJobList()) });
		
		for (Processor processor : processorList) {
			processor.start();
		}
	}
}
