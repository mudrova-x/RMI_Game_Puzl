package lab4_mudrova;

import java.io.DataInputStream;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

public class StreamsHelper {

	// байтовый
	public static void outputSuppliable(Suppliable o, OutputStream out) throws IOException {
		o.output(out);
	}

	public static Suppliable inputSuppliable(InputStream in) throws PriceException, IOException {
		
		Suppliable shipment;
		DataInputStream dis = new DataInputStream(in);
		String name = dis.readUTF();
		String flavour = dis.readUTF();
		int unitPrice = dis.readInt();
		int emount = dis.readInt();
		int boxesInput[] = new int[emount];
		for (int i = 0; i < emount; i++)
			boxesInput[i] = dis.readInt();
		
		if (name!="" && flavour!="")
			shipment = new ChocolateBoxes(name, flavour, boxesInput, unitPrice);
		else throw new IOException("считаны недопустимые значения полей");
		
		return shipment;

	}

	// символьный
	public static void writeSuppliable(Suppliable o, Writer out) throws IOException {
		o.write(out);
	}

	public static Suppliable readSuppliable(Reader in) throws PriceException, IOException {
		
		StreamTokenizer st = new StreamTokenizer(in);
		Suppliable shipment;
		
		st.wordChars('_', '-');
		st.nextToken();
		
		String name = st.sval;
		st.nextToken();
		String flavour = (st.sval!=null && st.sval.contains("-")) ? st.sval.replace("-", " ") : st.sval;
		st.nextToken();
		int unitPrice = (int) st.nval;
		st.nextToken();
		int emount = (int) st.nval;
		int boxesInput[] = new int[emount];
		for (int i = 0; i < emount; i++) {
			st.nextToken();
			boxesInput[i] = (int) st.nval;
		}

		if (name!="" && flavour!="")
			shipment = new ChocolateBoxes(name, flavour, boxesInput, unitPrice);
		else throw new IOException("считаны недопустимые значения полей");
		
		return shipment;

	}
	
	public static void serializeSuppliable(Suppliable o, OutputStream out) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(o);
		oos.flush();
	};

	public static Suppliable deserializeSuppliable(InputStream in) throws ClassNotFoundException, IOException {

		ObjectInputStream ois = new ObjectInputStream(in);
		Suppliable shipment = (Suppliable) ois.readObject();
		return shipment;

	};

	public static void writeFormatSuppliable(Suppliable o, Writer out) throws IOException {

		// printf aвтоматически создает и использует экземпляр класса Formatter

		PrintWriter pw = new PrintWriter(out);

		String formatterString = "\n\nПроизводитель - \"%s\"; \n " + "Вкус - \"%s\"; \n " + "Количество упаковок: %d; \n "
				+ "Цена за плитку: %d;\n" + "В каждой коробке: ";

		pw.printf(formatterString, o.getProducerName(), o.getFlavour(), o.getBoxesEmount(), o.getUnitPrice());
		pw.flush();
		for (int i = 0; i < o.getBoxesEmount(); i++)
			pw.format("\nВ коробке %d - %d плиток шоколада", i + 1, o.getBoxsInput(i));
		pw.flush();
		
		
		
//		Formatter fw = new Formatter(out);
//
//		String formatterString = "Производитель - \"%s\"; \n " + "Вкус - \"%s\"; \n " + "Количество упаковок: %d; \n "
//						+ "Цена за плитку: %d;\n" + "В каждой коробке: %s.";
//		fw.format(formatterString, o.getProducerName(), o.getFlavour(), o.getBoxesInput().length, o.getUnitPrice());
//		fw.flush();
//
//		for (int i = 0; i < o.getBoxesInput().length; i++)
//			fw.format("\nВ коробке %d - %d плиток шоколада", i + 1, o.getBoxesInput()[i]);
//		fw.flush();

	}

	public static Suppliable readFormatSuppliable(Scanner in) throws PriceException, IOException {
		

		String name, flavour;
		int unitPrice = 0, emount = 0;
		int[] boxesInput;
		Suppliable shipment;
		
		name = (in.hasNext()) ? in.next() : "";
		flavour = (in.hasNext()) ? in.next() : "";
		
		unitPrice = (in.hasNextInt())? in.nextInt():0;
		
		emount = (in.hasNextInt()) ? in.nextInt() : 0;
		boxesInput = new int[emount];
		
		if (emount != 0) {
			
			if (emount != 0)
				for (int i = 0; i < emount; i++) {
					if (in.hasNextInt())
						boxesInput[i] = in.nextInt();

				}
			
		}
		
		if (name!="" && flavour!="")
		
			shipment = new ChocolateBoxes(name, flavour, boxesInput, unitPrice);
		else
			throw new IOException("Ошибка ввода объекта");
		
		return shipment;
	}
	
	// под видом Suppliable отдаем ссылку на объект-обертку 
	
	public static Suppliable  synchronizedSuppliable(Suppliable i)
	{
		return new SuppliableSynchronized(i);
	}

}
