package lab4_mudrova;

public class TestWrite extends Thread{
	
	private Suppliable shipment;
	String name;
	
	public TestWrite(Suppliable shipment, String name) {
		this.shipment=shipment;
		this.name=name;
	}
	
	 public void run() {
		if (shipment==null) return;
		
		System.out.println("Begin => " + name);
		
		//synchronized(shipment) {
		
		for (int i=0; i<shipment.getBoxesEmount(); i++)
		{
			int num = name.equals("T2")?2:(name.equals("T3")?3:1);
			shipment.threadInputTest(num, i);
			System.out.println("Записал поток "+num);
		}
		System.out.println(shipment.getProducerName());
		
		//}
	    
		// чтобы другие потоки могли выполняться
	    try {
	      Thread.sleep(100);
	    }
	    catch (InterruptedException e) {
	      System.out.println(e.getMessage());
	    }

	    System.out.println("End => " + name);
	}
}