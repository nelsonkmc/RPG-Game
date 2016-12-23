/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

public class Skeleton extends AggresiveMonster {

	/** Creates a skeleton within the game world.
	 * @param xPos The initial x coordinate of the skeleton.
	 * @param yPos The initial y coordinate of the skeleton.
	 * @throws SlickException
	 */
	public Skeleton(String image_path, double xPos, double yPos) 
	throws SlickException 
	{
		super(image_path, xPos, yPos, 100, "Skeleton", 500, 16);
	}

	/** Updates the status of a skeleton.
	 * @param delta Time passed since last frame (milliseconds)
	 * @param world The world the skeleton is in.
	 * @throws SlickException
	 */
	public void update(int delta, World world)
	throws SlickException
	{
		super.update(delta, world);
	}
}
