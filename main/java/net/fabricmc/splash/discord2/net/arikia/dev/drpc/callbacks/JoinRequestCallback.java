package net.fabricmc.splash.discord2.net.arikia.dev.drpc.callbacks;

import com.sun.jna.Callback;
import net.fabricmc.splash.discord2.net.arikia.dev.drpc.DiscordUser;


/**
 * @author Nicolas "Vatuu" Adamoglou
 * @version 1.5.1
 * <p>
 * Interface to be implemented in classes that will be registered as "JoinRequestCallback" Event Handler.
 * @see net.fabricmc.splash.discord2.net.arikia.dev.drpc.DiscordEventHandlers
 **/
public interface JoinRequestCallback extends Callback {

	/**
	 * Method called when another player requests to join a game.
	 *
	 * @param request Object containing all required information about the user requesting to join.
	 * @see DiscordUser
	 */
	void apply(DiscordUser request);
}
