package io.github.pinkteammodfest.techmod;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Techmod implements ModInitializer {
	INSTANCE;
	
	public static final String MODID = "starter";

	public static final Logger logger = LogManager.getLogger();

	@Override
	public void onInitialize() {

	}
}
