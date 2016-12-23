/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

public class Prince extends NPC {
	
	private boolean elixirOfLife;
	
	/** Creates non-playable character Prince Aldric.
	 * @param image_path The image file location.
	 * @param x The x location of Prince Aldric in pixels.
	 * @param y The y location in Prince Aldric pixels.
	 * @throws SlickException
	 */
	public Prince(String image_path, double x, double y) 
	throws SlickException 
	{
		super(image_path, x, y, "Prince Aldric");	
		elixirOfLife = false;
	}
	
	/** Interacts with the player for the Elixir of Life
	 * @param The player unit of the game.
	 * @param The world Prince Aldric is on.
	 */
	public void talk(Player player, World world){
				
		if (elixirOfLife) {
			return;
		}
		
		// Check for Elixir
		if (!player.getInventory().contains(world.getElixir())){
			setDialogue("Please seek out the Elixir of Life to cure the king.");
		} else {
			player.getInventory().remove(world.getElixir());
			elixirOfLife = true;
			setDialogue("The elixir! My father is cured! Thankyou!");
		}
		
		setDialogueTimer(DIALOGUE_TIME);
	}

}
