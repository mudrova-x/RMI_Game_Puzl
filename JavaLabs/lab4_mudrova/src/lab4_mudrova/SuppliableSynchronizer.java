package lab4_mudrova;

// объекты класса используются при взаимодействии нитей
public class SuppliableSynchronizer {
	
	private Suppliable shipment;
	private boolean newed = false;

	
	public SuppliableSynchronizer(Suppliable shipment) {
		this.shipment=shipment;
	}
	
	public int getLength() {
		return shipment.getBoxesEmount();
	}
	
	synchronized public void write(int num, int index) {
		try {
				while(newed)
					wait();
				shipment.setBoxsInput(num, index);
				System.out.println("Write: " + num + " to position " + index);
				newed=true;
				notifyAll();
			} catch (InterruptedException e) {
				// прерывание работы нити
				Thread.currentThread().interrupt();
			}
	}
	
	synchronized public void read(int index) throws InterruptedException{
		
				while(!newed)
					wait();
				System.out.println("Read: " + shipment.getBoxsInput(index) + " from position " + index +"\n");
				newed=false;
			    notifyAll();
			
	}
}





//private int index = 0;
//if (index<0||index>=shipment.getBoxesEmount()) 
		//	throw new IllegalArgumentException("Ошибка: некорректный индекс.");

//if (index<0||index>=shipment.getBoxesEmount()) 
		//	throw new IllegalArgumentException("Ошибка: некорректный индекс.");
//index++;