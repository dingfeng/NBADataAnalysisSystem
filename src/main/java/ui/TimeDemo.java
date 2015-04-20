package ui;

import java.util.Timer;
import java.util.TimerTask;

public class TimeDemo {
       public static void main(String[] args)
       {
    	   Timer timer = new Timer();
    	   //timer 第一个任务
    	   TimerTask task1 = new TimerTask()
    	   {
			public void run() 
			{
			 System.out.println("Task1");	
			}
    	   };
    	   //timer 的第2个任务
    	   TimerTask task2 = new TimerTask()
    	   {
			public void run() 
			{
			 System.out.println("Task2");	
			}
    	   };
    	   //代表2ms后开始执行task1，没100ms执行一次
    	   //100即为刷新频率，界面设为10秒一次
    	   timer.schedule(task1, 2,100);
    	   //5秒钟后结束task1。开始task2
    	   try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	 timer.cancel();
    	 timer = new Timer();
    	 timer.schedule(task2, 2,100);
    	 //5秒钟后结束task2
    	 try {
 			Thread.sleep(5000);
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}
    	 timer.cancel();
       }
       
       
}
