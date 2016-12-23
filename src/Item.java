/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

/** The items which the player is allowed to take.
 */
public abstract class Item extends Unit {

	private boolean taken;
	
	/** Returns the value of taken item. 
	 * @return True when item is already taken.
	 */
	public boolean getTaken(){
		return taken;
	}
	
	/** Sets the value of taken item.
	 * @param taken The availability of the item.
	 */
	public void setTaken(boolean taken){
		this.taken = taken;
	}
	
	/** Creates item unit in the game world
	 * @param image_path The image file location
	 * @param xPos The Item's x location in pixels.
	 * @param yPos The Item's y location in pixels.
	 * @throws SlickException
	 */
	public Item(String image_path, double xPos, double yPos)
	throws SlickException 
	{
		super(image_path, xPos, yPos, 0, null, 0);
		
		taken = false;
	}

	/** Render the item on screen.
	 * @param g The current Graphics context.
	 * @param camera The camera object.
	 * @return True when item is still available.
	 */
	public boolean render(Graphics g, Camera camera){
		
		if (taken){
			return false;
		}
		
		super.render(g, camera);
		
		return true;
	}
	
	/** The player takes the item and has effect on him.
	 * @param player The player of the game.
	 */
	public abstract void take(Player player);
	
	
}
