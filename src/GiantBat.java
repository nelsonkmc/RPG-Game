/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

public class GiantBat extends PassiveMonster {

	/** Creates a giant bat within the game world.
	 * @param xPos The initial x coordinate of the giant bat.
	 * @param yPos The initial y coordinate of the giant bat.
	 * @throws SlickException
	 */
	public GiantBat(String image_path, double xPos, double yPos) 
	throws SlickException 
	{
		super(image_path, xPos, yPos, 40, "Giant Bat");
	}

	/** Updates the status of a giant bat.
	 * @param delta Time passed since last frame (milliseconds)
	 * @param world The world the giant bat is in.
	 * @throws SlickException
	 */
	public void update(int delta, World world)
	throws SlickException
	{
		super.update(delta, world);
	}
}
