package scannerbarcode;

import java.util.EventObject;

/**
 * класс события чтения кода сканером штрих-кода
 * @author Alexej
 */
public class BarcodeReadEvent extends EventObject {
	/** Конструктор события */
	public BarcodeReadEvent(Object source, String c) {
		super(source);
		barcode = c;
	}

	/**
	 * возвращает штрих-код, прочитанный сканером
	 * @return 
	 */
	public String getCode() {
		return barcode;
	}
	
	/** штрих-код, прочитанный сканером. */
	private String barcode = null;
}
