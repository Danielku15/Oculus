package at.itb13.oculus.util;

import java.util.UUID;

public final class IdGenerator {
	
	private IdGenerator() {}
	
    public static String createId() {
        UUID uuid = java.util.UUID.randomUUID();
        return uuid.toString();
    }
}