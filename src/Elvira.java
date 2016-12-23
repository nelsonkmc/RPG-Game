/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

/** The healer of the game that heals the player to full health.
 */
public class Elvira extends NPC {

	Boolean healed = false;
	
	/** Creates non-playable character Elvira.
	 * @param image_path The image file location of Elvira.
	 * @param x The x location of Elvira in pixels.
	 * @param y The y location of Elvira in pixels.
	 * @throws SlickException
	 */
	public Elvira(String image_path, double x, double y) 
	throws SlickException 
	{
		super(image_path, x, y, "Elvira");
	}
	
	/** Interacts with the player to restore full health to the player.
	 * @param player The player unit of the game.
	 * @param world The world Elvira is on.
	 */
	public void talk(Player player, World world){
		
		// Restore full health to player
		if (player.getHP() == player.getmaxHP()){
			setDialogue("Return to me if you ever need healing");
		} else {
			player.setHP(player.getmaxHP());
			setDialogue("You're looking much healthier now.");
		}
		
		setDialogueTimer(DIALOGUE_TIME);
	}

}
