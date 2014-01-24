package model;

import java.util.Date;

public class Watch {
	private static long started;
	
	public static void start() {
		started = new Date().getTime();
	}
	
	public synchronized static int getReal() {
		return (int)((new Date().getTime()) % started);
	}
	
	public static int getAmount() {
		return Math.abs(getReal() % 1000 - 1000);
	}
	
	public static int getTime() {
		double real = getReal() / 1000;
		return (int)Math.round(real);
	}
}
