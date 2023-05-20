package lab4_mudrova;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;

public class BiscuitBoxes implements Suppliable, Serializable {

	
	private static final long serialVersionUID = 7896934944811048859L;
	
	private String producerName;
	private String biscuitFlavour;
	private int gramsPrice; // на сто грамм
	private int[] boxesWeight;
	

	public BiscuitBoxes() {
		this.producerName = "поставщик";
		this.biscuitFlavour = "карамель";
		this.boxesWeight = new int[] { 100 };
		this.gramsPrice = 100;
	}

	public BiscuitBoxes(String name, String biscuitFlavour, int[] boxesWeight, int gramsPrice) {
		this.producerName = name;
		this.biscuitFlavour = biscuitFlavour;
		if (boxesWeight.length == 0)
			throw new IllegalArgumentException("Длина списка упаковок должна быть строго положительной");
		this.boxesWeight = boxesWeight;
		if (gramsPrice <= 0 || gramsPrice % 100 != 0)
			throw new IllegalArgumentException("Цена должна быть быть строго положительной и кратной 100");
		this.gramsPrice = gramsPrice;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getProducerName() {
		return producerName; 
	}

	public void setFlavour(String flavour) {
		this.biscuitFlavour = flavour;
	}

	public String getFlavour() {
		return biscuitFlavour;
	}

	public void setUnitPrice(int price) {
		this.gramsPrice = price;
	}

	public int getUnitPrice() {
		return gramsPrice;
	}

//	public void setBoxesInput(int[] boxesWeight) {
//		this.boxesWeight = boxesWeight;
//	}
//
//	public int[] getBoxesInput() {
//		return boxesWeight;
//	}
	
	public void setBoxsInput(int boxWeight, int boxNum) {
		if (boxWeight<0)throw new IllegalArgumentException("Ошибка: вес не может быть задан отрицательным числом.");
		if (boxNum<0||boxNum>=boxesWeight.length)throw new IllegalArgumentException("Ошибка: некорректный индекс.");
			
		this.boxesWeight[boxNum] = boxWeight;
	}

	public int getBoxsInput(int boxNum) {
		return boxesWeight[boxNum];
	}
	
	public int getBoxesEmount() {
		return boxesWeight.length;
	}

	public int getOrderPrice() throws PriceException {
		int orderPrice = 0;
		for (int element : this.boxesWeight)
			orderPrice += element * gramsPrice / 100;
		if (orderPrice <= gramsPrice)
			throw new PriceException("Ошибка: Цена всей поставки должна превышать цену 100 грамм печенья.");
		return orderPrice;
	}

	// сначала именно ссылку  “==” сравнивает ссылки
	
	@Override
	public boolean equals(Object object) {
		if (object == this) { 
			return true;
		}
		if (object == null || object.getClass() != this.getClass()) {
			return false;
		}

		BiscuitBoxes box = (BiscuitBoxes) object;

		for (int i=0; i<this.boxesWeight.length;i++)
			if (this.boxesWeight[i]!=box.boxesWeight[i])
				return false;
		
		return
				(this.producerName.equals(box.producerName) 
				&& (this.biscuitFlavour.equals(box.biscuitFlavour))
				&& (this.gramsPrice == box.gramsPrice));
	}

	@Override
	public int hashCode() {
		int result = producerName == null ? 0 : producerName.hashCode();
		result = 29 * result + (biscuitFlavour == null ? 0 : biscuitFlavour.hashCode());
		
		if (boxesWeight == null)
			result = 29 * result;
		else
			for (int element : boxesWeight)
				result += element;
		
		result = 29 * result + gramsPrice;
		return result;
	}

	@Override
	public String toString() {
		String description;
		description = String.format(
				"Производитель - \"%s\";" + 
						" \n Вкус - \"%s\";" + 
						" \n Количество упаковок: %d;"
						+ " \n Цена за 100 грамм: %d.\n" 
						+ "В каждой коробке:",
				producerName, biscuitFlavour, boxesWeight.length, gramsPrice);
		for (int i = 0; i < boxesWeight.length; i++)
			description += String.format("\n В коробке № %d - %d грамм печенья", (i + 1), boxesWeight[i]);
		return description;
	}
	

	public void output(OutputStream out) throws IOException {
		DataOutputStream dos = new DataOutputStream(out);
			dos.writeUTF(producerName);
			dos.writeUTF(biscuitFlavour);
			dos.writeInt(gramsPrice);
			dos.writeInt(boxesWeight.length);
			for (int i=0;i<boxesWeight.length;i++)
				dos.writeInt(boxesWeight[i]);
			
		
	}

	public void write(Writer out) throws IOException{
		PrintWriter pw = new PrintWriter(out);
		pw.print("\n"+producerName+" ");
		pw.print((biscuitFlavour.contains(" ")?biscuitFlavour.replace(" ", "-"):biscuitFlavour)+" ");
		pw.print(gramsPrice+" ");
		pw.print(boxesWeight.length);
		for (int i=0;i<boxesWeight.length;i++)
			pw.print(" "+boxesWeight[i]);
		pw.flush();
	}

	@Override
	public void threadInputTest(int boxWeight, int boxNum) {
		this.setBoxsInput(boxWeight, boxNum);
		for (int i=0; i<5; i++)
			System.out.println(i+". На месте " + boxNum + " пишу значение " + boxWeight);
		
	}
	
}