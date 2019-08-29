package scannerbarcode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Класс реализации работы со сканером штрих-кодов
 * @author alexej
 */
public class ScannerBarcode extends JComponent 
		implements ActionListener {
	
	public ScannerBarcode() {
		super();
        setSize(1, 1);
		setVisible(false);
	}
	
	public void init() {//настройка перехвата нажатий 
		addBarcodeReadListener((BarcodeReadListener) form);
		
		InputMap imap = form.getInputMap(WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = form.getActionMap();
		java.util.List<Action> actions = new ArrayList<Action>();
		// ";"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
					(char)KeyEvent.VK_SEMICOLON, KeyEvent.VK_SEMICOLON, 0, this));
		// "?"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
					'?', KeyEvent.VK_SLASH, KeyEvent.SHIFT_DOWN_MASK, this));
		// "!"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
					'!', KeyEvent.VK_EXCLAMATION_MARK, 0, this));
		actions.add(createAction("", ACTION_DIGIT + "_" + 
				'@', KeyEvent.VK_2, KeyEvent.SHIFT_DOWN_MASK, this));
		// "#"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
				'#', KeyEvent.VK_3, KeyEvent.SHIFT_DOWN_MASK, this));
		// "$"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
				'$', KeyEvent.VK_4, KeyEvent.SHIFT_DOWN_MASK, this));
		// "%"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
				'%', KeyEvent.VK_5, KeyEvent.SHIFT_DOWN_MASK, this));
		// "^"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
				'^', KeyEvent.VK_6, KeyEvent.SHIFT_DOWN_MASK, this));
		// "&"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
				'&', KeyEvent.VK_7, KeyEvent.SHIFT_DOWN_MASK, this));
		// "*"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
				'*', KeyEvent.VK_8, KeyEvent.SHIFT_DOWN_MASK, this));
		// "("
		actions.add(createAction("", ACTION_DIGIT + "_" + 
				'(', KeyEvent.VK_9, KeyEvent.SHIFT_DOWN_MASK, this));
		// ")"
		actions.add(createAction("", ACTION_DIGIT + "_" + 
				')', KeyEvent.VK_0, KeyEvent.SHIFT_DOWN_MASK, this));
		// минус
		actions.add(createAction("", ACTION_DIGIT + "_" + 
					(char)KeyEvent.VK_MINUS, KeyEvent.VK_MINUS, 0, this));
		// цифры
		for (int i = KeyEvent.VK_0; i <= KeyEvent.VK_9; i++) {
			actions.add(createAction("", ACTION_DIGIT + "_" + 
					(char)i, i, 0, this));
		}
		// латиница
		for (int i = KeyEvent.VK_A; i <= KeyEvent.VK_Z; i++) {
			// верхний регистр
			actions.add(createAction("", ACTION_DIGIT + "_" + 
					(char)i, i, KeyEvent.SHIFT_DOWN_MASK, this));
			// нижний регистр
			actions.add(createAction("", ACTION_DIGIT + "_" + 
					Character.toLowerCase((char)i), i, 0, this));
		}
		for (Action action: actions) {
			amap.put(action.getValue(Action.ACTION_COMMAND_KEY), action);
			imap.put((KeyStroke) action.getValue(Action.ACCELERATOR_KEY),
				action.getValue(Action.ACTION_COMMAND_KEY));
		}
	}

	private Action createAction(String name, String command,
			int key, int mod, ActionListener listener) {//реакция на нажатия
		KeyStroke keyStroke = KeyStroke.getKeyStroke(key, mod);
		//CustomAction action = new CustomAction(name, listener);
		CustomAction action = new CustomAction(name, listener);
		action.putValue(Action.ACTION_COMMAND_KEY, command);
		if (keyStroke != null) {
			action.putValue(Action.ACCELERATOR_KEY, keyStroke);
		}
		return action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand(); //если хотим найти по штриху
		if (e.getActionCommand().equals(ACTION_ACTIVATE)) {
			str = "";
			activate = true;
			//System.out.println("Чтение штрих-кода активировано");
			if (!strPrev.isEmpty()) {
				str = strPrev;
				strPrev = "";
			}
			timer.purge();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					setStr(str);
				}
			};
			timer.schedule(task, interval);
			
		} else if (e.getActionCommand().equals(ACTION_DEACTIVATE)) {
			activate = false;
			//System.out.println("Чтение штрих-кода деактивировано");
			
		} else if (activate && cmd.startsWith(ACTION_DIGIT + "_")) {
			str += cmd.substring(ACTION_DIGIT.length() + 1);
			//System.out.println("str = "+str);
			
		} else if (!prefix.isEmpty()
				  && cmd.startsWith(ACTION_DIGIT + "_")
				  && cmd.substring(ACTION_DIGIT.length() + 1).equals(prefix)
				  ) {
			strPrev += cmd.substring(ACTION_DIGIT.length() + 1);
			//System.out.println("prefix = "+strPrev);
			actionPerformed(new ActionEvent(this, 0, ACTION_ACTIVATE));
			
		} else if (cmd.startsWith(ACTION_DIGIT + "_") && time == 0) {
			time = System.currentTimeMillis();
			strPrev = cmd.substring(ACTION_DIGIT.length() + 1);
			//System.out.println("0.strPrev = "+strPrev);
			
		} else if (cmd.startsWith(ACTION_DIGIT + "_") && time > 0) {
			long t = System.currentTimeMillis() - time;
			//System.out.println("t = "+t);
			//System.out.println(cmd.substring(ACTION_DIGIT.length() + 1));
			if (t <= delay) {
				strPrev += cmd.substring(ACTION_DIGIT.length() + 1);
				actionPerformed(new ActionEvent(this, 0, ACTION_ACTIVATE));
				//System.out.println("strPrev = "+strPrev);
			}	
			time = 0;
			strPrev = "";
		}
	}
	
	// <editor-fold defaultstate="collapsed" desc="Генерация событий.">
	public void addBarcodeReadListener(BarcodeReadListener l) {
		listenerList.add(BarcodeReadListener.class, l);
	}

	public void removeBarcodeReadListener(BarcodeReadListener l) {
		listenerList.remove(BarcodeReadListener.class, l);
	}

	/**
	 * Генерация события , вызов обработчика события
	 * @param e Параметры события.
	 */
	public void fireBarcodeRead(BarcodeReadEvent e) {
		EventListener[] listeners = listenerList.getListeners(
			BarcodeReadListener.class);
		for (int i = 0; i < listeners.length; i++) {
			BarcodeReadListener listener =
				(BarcodeReadListener) listeners[i];
			listener.readBarCode(e);
		}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Методы доступа к полям.">
	public void setStr(String s) {
		str = "";
		if (!s.isEmpty()) {
			actionPerformed(new ActionEvent(this, 0, ACTION_DEACTIVATE));
			
			if (!prefix.isEmpty()) {
				if (s.startsWith(prefix)) {
					s = s.substring(prefix.length());
				}
			} else {
				if (_PREFIX.contains(s.substring(0, 1))) {
					s = s.substring(1);
				}
			}
			if (!postfix.isEmpty()) {
				if (s.endsWith(postfix)) {
					s = s.substring(0, s.length()-postfix.length());
				}
			} else {
				if (_PREFIX.contains(s.substring(s.length()-1))) {
					s = s.substring(0, s.length()-1);
				}
			}
			BarcodeReadEvent event = new BarcodeReadEvent(this, s);
			fireBarcodeRead(event);
		}
	}
	
	public void setForm(JPanel f) {
		form = f;
		if (form != null) {
			init();
		}
   }
    public int getDelay() {
       return delay;
    }
    
   public void setDelay(int d) {
      delay = d;
   } 
    
    public int getInterval() {
       return interval;
    }
    
   public void setInterval(int d) {
      interval = d;
   } 
    
	// </editor-fold>
	
	/** Прочитанная строка. */
	private String str = "";
	/** Прочитанная строка до активации сканера. */
	private String strPrev = "";
	/** Объект формы. */
	private JPanel form = null;
	private static final String ACTION_ACTIVATE   = "activate";
	private static final String ACTION_DEACTIVATE   = "deactivate";
	private static final String ACTION_DIGIT   = "action_digit";
	/** Признак активации чтения штрих-кода. */
	private boolean activate = false;
	/** момент времени для идентификации работы сканера. */
	private long time = 0;
	/** таймер ожидания окончания штрих-кода */
	private java.util.Timer timer = new java.util.Timer();
	/** возможные префиксы(постфиксы). */
	public static String _PREFIX = "!@#$%^&*();?";
	/** Префикс. */
	public String prefix = "";
	/** Постфикс. */
	public String postfix = "";
	/** интервал времени чтения одного символа(мс). */
	public int delay = 40;
	/** интервал времени чтения всего штрих-кода(мс). */
	public int interval = 200;
	
	// <editor-fold defaultstate="collapsed" desc="Класс действия с передачей обработки слушателю.">
	/**
	 * Абстрактное действие с передачей обработки слушателю.
	 */
	public static class CustomAction extends AbstractAction {
		/**
		 * Конструктор.
		 * @param name Имя действия.
		 * @param l Слушатель для передачи обработки.
		 */
		public CustomAction(String name, ActionListener l) {
			super(name);
			listener = l;
		}

		/**
		 * Передача обработки слушателю.
		 * @param e Параметры события.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			listener.actionPerformed(e);
		}

		/**
		 * Установить слушатель для передачи обработки.
		 * @param l Слушатель для передачи обработки.
		 */
		public void setListener(ActionListener l) {
			listener = l;
		}

		/**
		 * Получить слушатель для передачи обработки.
		 * @return Слушатель для передачи обработки.
		 */
		public ActionListener getListener() {
			return listener;
		}

		/** Слушатель для передачи обработки. */
		private ActionListener listener;
	}
	// </editor-fold>
}
