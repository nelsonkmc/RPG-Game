/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

public class Bandit extends AggresiveMonster {

	/** Creates a bandit within the game world.
	 * @param xPos The initial x coordinate of the bandit.
	 * @param yPos The initial y coordinate of the bandit.
	 * @throws SlickException
	 */
	public Bandit(String image_path, double xPos, double yPos) 
	throws SlickException 
	{
		super(image_path, xPos, yPos, 40, "Bandit", 200, 8);
	}

	/** Updates the status of a bandit.
	 * @param delta Time passed since last frame (milliseconds).
	 * @param world The world the bandit is in.
	 * @throws SlickException
	 */
	public void update(int delta, World world)
	throws SlickException
	{
		super.update(delta, world);
	}

}
