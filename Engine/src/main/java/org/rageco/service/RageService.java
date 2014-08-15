package org.rageco.service;

import org.rageco.manager.RageManager;

public interface RageService {
	void initialize();
	RageManager getManager();
	void close();
}
