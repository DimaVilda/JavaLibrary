package userinterface;

import java.util.EventObject;

/**
 * класс события изменения активной записи в таблице
 * @author Alexej
 */
public class TableRecordChangeEvent extends EventObject {
	/** Конструктор события */
	public TableRecordChangeEvent(Object source, int r) {
		super(source);
		row = r;
	}

	/**
	 * возвращает номер активной записи в таблице
	 * @return 
	 */
	public int getRow() {
		return row;
	}
	
	/** номер активной записи в таблице. */
	private int row = -1;
}
