package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JobReader {
	private FileInputStream file;
	private BufferedReader reader;
	
	public JobReader(String path) throws FileNotFoundException {
		file = new FileInputStream(path);
		InputStreamReader streamReader = new InputStreamReader(file);
		reader = new BufferedReader(streamReader);}
	
	public List<Job> getJobList() throws IOException {
		List<Job> jobList = new ArrayList<>();
		
		String line;
		
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(" ");
			
			int id = Integer.parseInt(tokens[0]);
			int spawn = Integer.parseInt(tokens[1]);
			int lifespan = Integer.parseInt(tokens[2]);
			int priority = Integer.parseInt(tokens[3]);
			 
			jobList.add(new Job(id, spawn, lifespan, priority));
		}
		
		return jobList;
	}
	
	public void close() throws IOException {
		reader.close();
		file.close();
	}
}
