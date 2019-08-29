package scannerbarcode;

import java.util.EventListener;

/**
 * Интерфейс слушателя события чтения кода сканером штрих-кода
 * @author Alexej
 */
public interface BarcodeReadListener extends EventListener {
	/**
	 * Событие чтения кода сканером штрих-кода
	 * @param e Параметры события.
	 */
	public void readBarCode(BarcodeReadEvent e);
}
