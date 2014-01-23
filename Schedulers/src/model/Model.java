package model;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Model {
	private List<Scheduler> schedulerList;
	
	public Model() {
		schedulerList = Arrays.asList(new Scheduler[] { new FirstComeFirstServed(),
														new ShortestJobFirst(),
														new FixedPriority(false),
														new FixedPriority(true),
														new RoundRobin(false, 2),
														new RoundRobin(true, 5) });
	}
	
	public List<Scheduler> getSchedulerList() {
		return this.schedulerList;
	}
	
	public List<Job> getJobList(String path) throws IOException {
		JobReader reader = new JobReader(path);
		List<Job> jobList = reader.getJobList();
		reader.close();
		
		return jobList;
	}
}
