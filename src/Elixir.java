/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

public class Elixir extends Item {

	/** Creates item Elixir Of Life. 
	 * @param image_path The image file location.
	 * @param xPos The Elixir's x location in pixels.
	 * @param yPos The Elixir's y location in pixels.
	 * @throws SlickException
	 */
	public Elixir(String image_path, double xPos, double yPos) 
	throws SlickException 
	{
		super(image_path, xPos, yPos);
	}

	/** Take elixir and add into inventory.
	 * @param player The player unit of the game.
	 */
	public void take(Player player) {
		setTaken(true);
		player.getInventory().add(this);
	}

}
