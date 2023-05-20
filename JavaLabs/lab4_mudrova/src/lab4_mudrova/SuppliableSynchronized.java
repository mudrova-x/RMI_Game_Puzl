package lab4_mudrova;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class SuppliableSynchronized implements Suppliable{


	// оборачивает полученный в конструкторе объект и вызывает методы обернутого объекта
	// т.е. блокирует метод, не позволяя другому потоку им воспользоваться, пока не освободит
	
	private Suppliable shipment;
	
	public SuppliableSynchronized(Suppliable shipment){
		this.shipment=shipment; 
	}
	
	@Override
	synchronized public void setProducerName(String name) {
		this.shipment.setProducerName(name);
		
	}

	@Override
	synchronized public String getProducerName() {
		return this.shipment.getProducerName();
	}

	@Override
	synchronized public void setFlavour(String flavour) {
		this.shipment.setFlavour(flavour);
		
	}

	@Override
	synchronized public String getFlavour() {
		return this.shipment.getFlavour();
	}

	@Override
	synchronized public void setUnitPrice(int price) {
		this.shipment.setUnitPrice(price);
		
	}

	@Override
	synchronized public int getUnitPrice() {
		return this.shipment.getUnitPrice();
	}

	@Override
	synchronized public void setBoxsInput(int boxeEmount, int boxNum) {
		this.shipment.setBoxsInput(boxeEmount, boxNum);
		
	}

	@Override
	synchronized public int getBoxsInput(int boxNum) {
		return this.shipment.getBoxsInput(boxNum);
	}

	@Override
	synchronized public int getBoxesEmount() {
		return this.shipment.getBoxesEmount();
	}

	@Override
	synchronized public int getOrderPrice() throws PriceException {
		return this.shipment.getOrderPrice();
	}

	@Override
	synchronized public void output(OutputStream out) throws IOException {
		this.shipment.output(out);
		
	}

	@Override
	synchronized public void write(Writer out) throws IOException {
		this.shipment.write(out);
		
	}

	@Override
	synchronized public void threadInputTest(int boxeEmount, int boxNum) {
		this.shipment.threadInputTest(boxeEmount, boxNum);
		
	}
	

}
