package co.project.utils;

import java.util.Timer;

public class Time {
	private Timer time;
	
	public void start()
	{
		time = new Timer();
	}
	
	public void stop()
	{
		time.cancel();
	}
}
