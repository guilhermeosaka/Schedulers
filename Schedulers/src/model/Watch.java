package model;

import java.util.Date;

public class Watch {
	private static long started;
	private static long finished;
	
	public static void start() {
		started = new Date().getTime();
	}
	
	public static int getTime() {
		return (int)((new Date().getTime()) % started);
	}
	
	public static int remaining() {
		return Math.abs(getTime() % 1000 - 1000);
	}
}
