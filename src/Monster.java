/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

/** The monster which can be killed by the player and kill the player.
 */
public abstract class Monster extends Unit {

	/** Creates new monster in the game world.
	 * @param image_path The image file location of player.
	 * @param xPos The initial x location of monster in pixels.
	 * @param yPos The initial y location of monster in pixels.
	 * @param HP The health point of the monster.
	 * @param unitName The name of the monster.
	 * @param movementRate The movement rate of the monster in pixels.
	 * @throws SlickException
	 */
	public Monster(String image_path, double xPos, double yPos, int HP, String unitName, double movementRate) 
	throws SlickException {
		super(image_path, xPos, yPos, HP, unitName, movementRate);
	}

	/** Updates the current status of monsters.
	 * @param delta Time passed since last frame (milliseconds).
	 * @param world The world the monster is on.
	 * @throws SlickException
	 */
	public abstract void update(int delta, World world) throws SlickException;
	
}
