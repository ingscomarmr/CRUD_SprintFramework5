package com.comr;

//una clase puede ser de tipo thread
public class MiThreadClass extends Thread{
	
	@Override
	public void run() { //en este metodo se incluye la tarea que se realiza
		try {
			
			for(int i=0; i<10; i++) {
				System.out.println("TH1:" + i);
				Thread.sleep(1000);
			}
			System.out.println("FIN TH1");
		}catch(Exception ex) {
			System.out.println("Error al correr Thread" + ex.getMessage());			
		}
		
	}

	//se pueden sobreescribir otros metodos del thread
	@Override
	public synchronized void start() {
		System.out.println("MiThreadClass inicia");
		super.start();
	}	

	@Override
	public void interrupt() {
		System.out.println("MiThreadClass finaliza");
		super.interrupt();
	}
	
	
}
