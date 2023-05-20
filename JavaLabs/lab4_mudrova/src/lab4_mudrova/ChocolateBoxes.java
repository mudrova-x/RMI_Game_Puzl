package lab4_mudrova;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;

public class ChocolateBoxes implements Suppliable, Serializable {

	private static final long serialVersionUID = -5763339994185043646L;
	
	private String producerName;
	private String chokolateFlavour;
	private int barPrice; //за одну плитку
	private int[] barsEmount; //количество плиток в коробке=упаковке, коробки - в поставке
	
 	
	public ChocolateBoxes() {
		this.producerName="поставщик";
		this.chokolateFlavour = "мята";
		this.barsEmount = new int[] {10};
		this.barPrice = 60;
	}
	
	public ChocolateBoxes(String producerName, String chokolateFlavour,  int[] barsEmount, int barPrice) {
		this.producerName=producerName;
		this.chokolateFlavour = chokolateFlavour;
		if (barsEmount.length==0) throw new IllegalArgumentException("Длина списка упаковок должна быть строго положительной");
		this.barsEmount = barsEmount;
		if (barPrice<=0) throw new IllegalArgumentException("Цена должна быть быть строго положительной");
		this.barPrice = barPrice;
	}

	public void setProducerName(String producerName) {
		this.producerName=producerName;
	}
	
	public String getProducerName() {
		return producerName;
	}

	public void setFlavour(String flavour) {
		this.chokolateFlavour=flavour;
	}

	public String getFlavour() {
		return chokolateFlavour;
	}
	
	public void setUnitPrice(int price) {
		this.barPrice=price;
	}

	public int getUnitPrice() {
		return barPrice;
	}

	
//	public void setBoxesInput(int [] barsEmount) {
//		this.barsEmount=barsEmount;
//	}
//
//	public int [] getBoxesInput() {
//		return barsEmount;
//	}
	
	public void setBoxsInput(int boxEmount, int boxNum) {
		if (boxEmount<0)throw new IllegalArgumentException("Ошибка: количество не может быть задано отрицательным числом.");
		if (boxNum<0||boxNum>=barsEmount.length)throw new IllegalArgumentException("Ошибка: некорректный индекс.");
	//	for (int i=0; i<5; i++)
	//		System.out.println("Я номер" + boxNum + " и значение " + boxEmount);
		
		this.barsEmount[boxNum] = boxEmount;
	}

	public int getBoxsInput(int boxNum) {
		return barsEmount[boxNum];
	}
	
	public int getBoxesEmount() {
		return barsEmount.length;
	}
	
	public int getOrderPrice() throws PriceException{
		int orderPrice = 0;
		for (int element : this.barsEmount)
			orderPrice+=element*barPrice;
		if (orderPrice<=barPrice) throw new PriceException("Ошибка: Цена всей поставки должна превышать цену одной плитки шоколада.");
		return orderPrice;
	}

	@Override
    public boolean equals(Object object) {
    if (object == this) { 
        return true;
    }
    if (object == null || object.getClass() != this.getClass()) {
        return false;
    }

    ChocolateBoxes box = (ChocolateBoxes) object;
    
    for (int i=0; i<this.barsEmount.length;i++)
		if (this.barsEmount[i]!=box.barsEmount[i])
			return false;
    
    return // проверить все поля на null?
    	(this.producerName.equals(box.producerName)
        && (this.chokolateFlavour.equals( box.chokolateFlavour))
        && (this.barPrice==box.barPrice));
    }
	
	//“==” сравнивает ссылки
	
    @Override
    public int hashCode() {
        int result = producerName == null ? 0 : producerName.hashCode();
        result = 29 * result + ( chokolateFlavour == null ? 0 : chokolateFlavour.hashCode());
        if (barsEmount == null)
			result = 29 * result;
		else
			for (int element : barsEmount)
				result += element;
        result = 29 * result + barPrice;
        return result;
    }
	
	@Override
    public String toString(){
        String description;
        description = String.format("Производитель - \"%s\"; \n "
        		+ "Вкус - \"%s\"; \n "
        		+ "Количество упаковок: %d; \n "
        		+ "Цена за плитку: %d;\n"
        		+ "В каждой коробке:", 
        		producerName, chokolateFlavour, barsEmount.length, barPrice);
        for (int i=0;i<barsEmount.length;i++)
        	description+= String.format("\n В коробке № %d - %d плиток шоколада", (i+1), barsEmount[i]);
        return description;
    }


	public void output(OutputStream out)  throws IOException{
		//поток-обертка
		DataOutputStream dos = new DataOutputStream(out);
			dos.writeUTF(producerName);
			dos.writeUTF(chokolateFlavour);
			dos.writeInt(barPrice);
			dos.writeInt(barsEmount.length);
			for (int i=0;i<barsEmount.length;i++)
				dos.writeInt(barsEmount[i]);
			
		
	}

	
	public void write(Writer out)  throws IOException{
		//поток-обертка
		PrintWriter pw = new PrintWriter(out);
		pw.print("\n"+producerName+" ");
		pw.print((chokolateFlavour.contains(" ")?chokolateFlavour.replace(" ", "-"):chokolateFlavour)+" ");
		pw.print(barPrice+" ");
		pw.print(barsEmount.length);
		for (int i=0;i<barsEmount.length;i++)
			pw.print(" "+barsEmount[i]);
		pw.flush();
		
	}

	@Override
	public void threadInputTest(int boxeEmount, int boxNum) {
			for (int i=0; i<5; i++)
				System.out.println("На месте " + boxNum + " пишу значение " + boxeEmount);
			setBoxsInput(boxeEmount, boxNum);
	
	}
}