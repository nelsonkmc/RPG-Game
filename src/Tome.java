/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

public class Tome extends Item {

	private static final int COOLDOWN_DECREASE = -300;
	private int newCooldown;
	
	/** Creates item Tome Of Agility 
	 * @param image_path The image file location.
	 * @param xPos The Tome's x location in pixels.
	 * @param yPos The Tome's y location in pixels.
	 * @throws SlickException
	 */
	public Tome(String image_path, double xPos, double yPos) 
	throws SlickException 
	{
		super(image_path, xPos, yPos);
	}

	/** Gives the player a cooldown boost.
	 * @param player The player unit of the game.
	 */
	public void take(Player player) {
		
		newCooldown = player.getCooldown() + COOLDOWN_DECREASE;
		player.setCooldown(newCooldown);
		
		setTaken(true);
		player.getInventory().add(this);
	}

}
