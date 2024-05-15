package com.wipro.threads;

public class Thread1 extends Thread{

	@Override
	public void run() {
		
		
		for(int i = 0; i < 2 ; i++) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Name of the thread running is -->"+Thread.currentThread().getName()+" and i ="+i);	
		}
		
		
		
	}

}
