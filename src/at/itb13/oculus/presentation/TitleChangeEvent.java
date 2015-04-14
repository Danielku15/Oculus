package at.itb13.oculus.presentation;

import javafx.event.Event;
import javafx.event.EventType;

public class TitleChangeEvent extends Event {
	private static final long serialVersionUID = -3003758889452059699L;
	
	public static final EventType<TitleChangeEvent> TITLE_CHANGE_EVENT = new EventType<TitleChangeEvent>(ANY, "TITLE_CHANGED"); 
	
	private String _title;
	
	public TitleChangeEvent(EventType<? extends Event> eventType, String title) {
		super(eventType);
		_title = title;
	}
	
	public String getTitle() {
		return _title;
	}
}
