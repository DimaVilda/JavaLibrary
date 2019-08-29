package userinterface;

import java.util.EventListener;

/**
 * Интерфейс слушателя события изменения активной записи в таблице
 * @author Alexej
 */
public interface TableRecordChangeListener extends EventListener {
	/**
	 * Событие чтения изменения активной записи в таблице
	 * @param e Параметры события.
	 */
	public void recordChange(TableRecordChangeEvent e);
}
