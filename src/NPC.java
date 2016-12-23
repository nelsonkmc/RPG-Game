/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;

/** The non-playable characters that resides in the village.
 */
public abstract class NPC extends Unit {

	private static final int BAR_HEIGHT = 20;
	public static final int DIALOGUE_TIME = 4000;

	private String dialogue;
	private int dialogueTimer;
	
	/** Returns the dialogue of NPC. 
	 * @return The speech text of NPC.
	 */
	public String getDialogue(){
		return dialogue;
	}
	
	/** Sets the dialogue of NPC.
	 * @param dialogue The speech text of NPC.
	 */
	public void setDialogue(String dialogue){
		this.dialogue = dialogue;
	}
	
	/** Returns the dialogue timer of NPC 
	 * @return The speech timer of NPC.
	 */
	public int getDialogueTimer(){
		return dialogueTimer;
	}
	
	/** Sets the dialogue timer of NPC. 
	 * @param dialogueTimer The speech timer of NPC.
	 */
	public void setDialogueTimer(int dialogueTimer){
		this.dialogueTimer = dialogueTimer;
	}
	
	/** Creates a new Player.
     * @param image_path Path of NPC's image file.
     * @param xPos The NPC's x location in pixels.
     * @param yPos The NPC's y location in pixels.
     * @param unitName The name of the NPC.
     * @throws SlickException
     */
	public NPC(String image_path, double xPos, double yPos, String unitName)
	throws SlickException
	{
		super(image_path, xPos, yPos, 1, unitName, 0);
		setDialogueTimer(DIALOGUE_TIME);
		dialogue = "";
	}
	
	/** Updates the world with the current status of NPC.
	 * @param delta Time passed since last frame (milliseconds).
	 * @param world The world the NPC is on.
	 * @throws SlickException
	 */
	public void update(int delta, World world)
	throws SlickException
	{
		setDialogueTimer(getDialogueTimer() - delta);
	}
	
	/** Render the dialogue of NPC.
	 * @param g The current graphics context.
	 * @param camera The camera object.
	 * @return True when speech within dialogue time.
	 */
	public boolean render(Graphics g, Camera camera)
	{
	
		Color TEXT = new Color(1.0f, 1.0f, 1.0f); // White
		Color BG = new Color(1,1,1); // Black
		
		if (super.render(g, camera) == false){
			return false;
		}
		
		// Check whether dialogue time is within timer
		if (getDialogueTimer() < 0){
			return true;
		}
		
		int bar_height = BAR_HEIGHT;
		int text_width = g.getFont().getWidth(dialogue);
		int x = (int)getxPos() - text_width/2;
		int y = (int)getyPos() - RPG.PANEL_HEIGHT;
		
		// Draw text boxes with text for dialogue.
		g.setColor(BG);
		g.fillRect(x, y, text_width, bar_height);
		g.setColor(TEXT);
		g.drawString(dialogue, x, y);
		
		return true;
	}

	/** Interact with player based on NPC. 
	 * @param player The player of the game world.
	 * @param world The world the NPC is on.
	 * @throws SlickException
	 */
	public abstract void talk(Player player, World world) throws SlickException;
}