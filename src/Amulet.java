/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

public class Amulet extends Item {
	
	private static final int MAXHP_INCREASE = 80;
	private int newMaxHP, newHP;
	
	/** Creates item Amulet of Vitality. 
	 * @param image_path The image file location.
	 * @param xPos The Amulet's x location in pixels.
	 * @param yPos The Amulet's y location in pixels.
	 */
	public Amulet(String image_path, double xPos, double yPos) 
	throws SlickException 
	{
		super(image_path, xPos, yPos);
	}

	/** Gives the player a maxHP boost.
	 * @param player The player unit of the game.
	 */
	public void take(Player player) {
		
		newMaxHP = player.getmaxHP() + MAXHP_INCREASE;
		newHP = player.getHP() + MAXHP_INCREASE;
		
		player.setmaxHP(newMaxHP);
		player.setHP(newHP);
		
		setTaken(true);
		player.getInventory().add(this);
	}

}
