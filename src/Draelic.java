/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

public class Draelic extends AggresiveMonster {

	/** Creates Draelic within the game world.
	 * @param xPos The initial x coordinate of Draelic.
	 * @param yPos The initial y coordinate of Draelic.
	 * @throws SlickException
	 */
	public Draelic(String image_path, double xPos, double yPos) 
	throws SlickException 
	{
		super(image_path, xPos, yPos, 140, "Draelic", 400, 30);
	}

	/** Updates the status of Draelic.
	 * @param delta Time passed since last frame (milliseconds).
	 * @param world The world Draelic is in.
	 * @throws SlickException
	 */
	public void update(int delta, World world)
	throws SlickException
	{
		super.update(delta, world);
	}

}
