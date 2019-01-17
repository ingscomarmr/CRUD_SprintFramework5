package com.comr;

public class ThreadExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("INICIA EL PROGRAMA QUE CORRE LOS THREADS");
		
		//Declaro la tarea y luego se la paso al thread
		Runnable taskToThread = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("RU inicia");
				try {
					for(int i=1; i<11; i++) {
						System.out.println("RU:" + i);
						Thread.sleep(4000);
					}
				}catch (Exception e) {
					System.err.println("Error:" + e.getMessage());
					Thread.interrupted();					
				}
				System.out.println("RU finaliza");
			}
		};
		
		//para que el metodo runable funcione se le pasa como parametro a la clase thread
		Thread thread1 = new Thread(taskToThread, "Tarea RU 1");
		
		//declaramos una instancia de la clase que implementa el thread
		MiThreadClass thread2 = new MiThreadClass();
		
		//implementamos una instancia con la misma tarea en el mismo
		new Thread(new Runnable() {			
			@Override
			public void run() {
				System.out.println("RT inicia");
				try {
					for(int i=1; i<11; i++) {
						System.out.println("RT:" + i);
						Thread.sleep(2000);
					}
				}catch (Exception e) {
					System.err.println("Error:" + e.getMessage());
					Thread.interrupted();					
				}
				System.out.println("RT finaliza");
			}
		}).start();
		
		thread1.start();
		thread2.start();
		
		System.out.println("TERMINA EL PROGRAMA QUE CORRE LOS THREADS");
	}

}
