package at.itb13.oculus.presentation;

import java.util.EventListener;

/**
 * 
 * Interface for a listener of changes depending on the queue entry event
 *
 */
public interface QueueEntryChosenListener extends EventListener {
	void queueEntryChosen(QueueEntryChosenEvent e);
}
