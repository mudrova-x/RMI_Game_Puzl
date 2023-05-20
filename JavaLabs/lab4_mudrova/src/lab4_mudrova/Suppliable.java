package lab4_mudrova;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface Suppliable {
	// "поставляемый" (товар)
	
		void setProducerName (String name); 
		String getProducerName ();
		
		public void setFlavour(String flavour);
		String getFlavour();
		
		void setUnitPrice (int price);
		int getUnitPrice();
		
		void setBoxsInput(int boxeEmount, int boxNum); 
		int  getBoxsInput (int boxNum);
		int getBoxesEmount();
		
		int getOrderPrice() throws PriceException; //за всю поставку со всеми в ней коробками
	
		void output(OutputStream out) throws IOException;
		void write(Writer out) throws IOException;
		
		void threadInputTest(int boxeEmount, int boxNum);
}
