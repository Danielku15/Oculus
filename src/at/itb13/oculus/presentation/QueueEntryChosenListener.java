package at.itb13.oculus.presentation;

import java.util.EventListener;

public interface QueueEntryChosenListener extends EventListener {
	void queueEntryChosen(QueueEntryChosenEvent e);
}
