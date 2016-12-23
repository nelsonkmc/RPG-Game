/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

/** The peasant of the game that gives information on valuable items.
 */
public class Garth extends NPC {

	/** Create non-playable character Garth.
	 * @param image_path The image file location of Garth.
	 * @param x The x location of Garth in pixels.
	 * @param y The y location of Garth in pixels.
	 * @throws SlickException
	 */
	public Garth(String image_path, double x, double y) 
	throws SlickException 
	{
		super(image_path, x, y, "Garth");
		
	}

	/** Interaction with the player to give away item location.
	 * @param The player unit of the game.
	 * @param The world Garth is on.
	 */
	public void talk(Player player, World world){
				
		// Check inventory for specified item
		if (!player.getInventory().contains(world.getAmulet())){
			setDialogue("Find the Amulet of Vitality, across the river to the west.");
		} else if (!player.getInventory().contains(world.getSword())){
			setDialogue("Find the Sword of Strength - cross the river and back, on the east side.");
		} else if (!player.getInventory().contains(world.getTome())){
			setDialogue("Find the Tome of Agility, in the Land of Shadows.");
		} else {
			setDialogue("You have found all the treasure I know of.");
		}
		
		setDialogueTimer(DIALOGUE_TIME);		
	}
}
