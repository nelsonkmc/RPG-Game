/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

/** The unit of the game.
 */
public abstract class Unit {

	// Constants Declaration.
	private static final int BAR_WIDTH = 70;
	private static final int BAR_HEIGHT = 20;
	private static final int TEXT_EDGE = 6;
	private static final int OVERHEAD_BAR = 50;
	private static final int  OVERHEAD_TEXT = 48;
	
	public static final int BLOCK_EDGE = 15;

	// Initiaise variables.
	private Image img = null;
	
	private double xPos, yPos;
	private double movementRate, step;
	private double newX, newY;
	private double moveX, moveY;

	private int HP, maxHP;
	private int cooldown, cooldownTimer;
	private int tilemap_width, tilemap_height;

	private String unitName;
	
	/** Returns the image of unit. 
	 * @return The image object.
	 */
	public Image getImage(){
		return img;
	}
	
	/** Sets the image of Unit. 
	 * @param img The image object.
	 */
	public void setImage(Image img){
		this.img = img;
	}
	
	/** Returns the x-coordinate of unit. 
	 * @return The x-coordinate of unit.
	 */
	public double getxPos(){
		return xPos;
	}
	
	/** Set x-coordinate of unit. 
	 * @param xPos The x-coordinate of unit.
	 */
	public void setxPos(double xPos){
		this.xPos = xPos;
	}
		
	/** Returns the y-coordinate of unit. 
	 * @return The y-coordinate of unit.
	 */
	public double getyPos(){
		return yPos;
	}
	
	/** Set y-coordinate of unit. 
	 * @param yPos The y-coordinate of unit.
	 */
	public void setyPos(double yPos){
		this.yPos = yPos;
	}
	
	/** Returns the current HP of unit.
	 * @return The health point of unit.
	 */
	public int getHP(){
		return HP;
	}
	
	/** Set current HP of unit. 
	 * @param HP The health point of unit.
	 */
	public void setHP(int HP){
		this.HP = HP;
	}
	
	/** Returns the max HP of unit. 
	 * @return The maximum health point of unit.
	 */
	public int getmaxHP(){
		return maxHP;
	}
	
	/** Set max HP of unit. 
	 * @param maxHP The maximum health point of unit.
	 */
	public void setmaxHP(int maxHP){
		this.maxHP = maxHP;
	}
	
	/** Returns movement rate per pixel of unit. 
	 * @return The movement rate per pixel of unit. 
	 */
	public double getmovementRate(){
		return movementRate;
	}
	
	/** Sets movement rate per pixel of unit. 
	 * @param movementRate The movement rate per pixel of unit.
	 */
	public void setmovementRate(float movementRate){
		this.movementRate = movementRate;
	}
	
	/** Returns the name of unit.
	 * @return The name of unit.
	 */
	public String getunitName(){
		return unitName;
	}
	
	/** Set name of unit. 
	 * @param unitName The name of unit.
	 */
	public void setunitName(String unitName){
		this.unitName = unitName;
	}
	
	/** Return the cooldown of unit. 
	 * @return The cooldown of unit.
	 */
	public int getCooldown(){
		return cooldown;
	}
	
	/** Set cooldown of unit.
	 * @param cooldown The cooldown of unit. 
	 */
	public void setCooldown(int cooldown){
		this.cooldown = cooldown;
	}
	
	/** Return the cooldown timer. 
	 * @return The cooldown timer.
	 */
	public int getCooldownTimer(){
		return cooldownTimer;
	}
	
	/** Set cooldown timer of unit.
	 * @param cooldownTimer The cooldown timer.
	 */
	public void setCooldownTimer(int cooldownTimer)
	{
		this.cooldownTimer = cooldownTimer;
	}
	

	/** Creates a new unit.
	 * @param image_path The image file location.
	 * @param xPos The x location in pixels.
	 * @param yPos The y location in pixels.
	 * @param maxHP The maximum health points of the unit.
	 * @param unitName The name of the unit.
	 * @param movementRate The movement rate of the unit.
	 * @throws SlickException
	 */
	public Unit(String image_path, double xPos, double yPos, int maxHP, String unitName, double movementRate)
	throws SlickException
	{
		img = new Image(image_path);
		this.xPos = xPos;
		this.yPos = yPos;
		this.HP = maxHP;
		this.maxHP = maxHP;
		this.unitName = unitName;
		this.movementRate = movementRate;
	}
	
	/** Calculate distance between two objects with Euclidean Distance.
	 * @param object The unit of in the game world.
	 * @return The distance between two objects (units).
	 */
	public double checkDistance(Unit object)
	{
		double d, xDistance, yDistance;
		xDistance = Math.pow((object.getxPos() - this.getxPos()), 2);
		yDistance = Math.pow((object.getyPos() - this.getyPos()), 2);
		d = Math.sqrt(xDistance + yDistance);
		
		return d;
	}
	
	/** Move unit to available terrains.
     * @param dir_x The unit's movement in the x axis (-1, 0 or 1).
     * @param dir_y The unit's movement in the y axis (-1, 0 or 1).
	 * @param delta Time passed since last frame (milliseconds).
	 * @param world The world the monster is on (to check blocking).
	 * @throws SlickException
	 */
	public void move(double dir_x, double dir_y, int delta, World world)
	throws SlickException
	{
		step = delta*movementRate;
				
		// Get updated coordinates of unit.
		newX = this.getxPos() + step*dir_x;
		newY = this.getyPos() + step*dir_y;		
				
		// Prevent movement to a blocked tile in x direction
		if(world.terrainBlocks(newX, this.getyPos())) {
			dir_x = 0;
		}
		
		// Prevent movement to a blocked tile in y direction
		if(world.terrainBlocks(this.getxPos(), newY)) {
			dir_y = 0;
		}
		
		moveX = getxPos() + dir_x*step;
		moveY = getyPos() + dir_y*step;
				
		tilemap_width = world.getMapWidth()*world.getTileWidth();
		tilemap_height = world.getMapHeight()*world.getTileHeight();
		
		// Controls movement of unit to available terrain.
		if((getxPos() > BLOCK_EDGE || dir_x > 0)
				&& (getxPos() < tilemap_width  - BLOCK_EDGE || dir_x < 0)) {
			setxPos(moveX);
		}
		
		if((getyPos() > BLOCK_EDGE || dir_y > 0)
				&& (getyPos() < tilemap_height - BLOCK_EDGE || dir_y < 0)) {
			setyPos(moveY);
		}
		
	}
	
	/** Render health bar for all units with health
	 * @param g The Slick graphics object, used for drawing.
	 * @param camera The camera object.
	 * @return True when render is needed.
	 */
	public boolean render(Graphics g, Camera camera)
	{
		// Check whether the unit is on screen
		if (!camera.onScreen(this)){
			return false;
		}
		
		getImage().drawCentered((int) xPos, (int) yPos);
		
		// If unit is an item, health bar and text are not needed.
		if (this instanceof Item){
			return false;
		}
		
		// If unit is the player, health bar and text are not needed.
		if (this instanceof Player){
			return false;
		}
		
		Color TEXT = new Color(1.0f, 1.0f, 1.0f); // White
		Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f); // Red
		Color BAR_BG = new Color(1, 1, 1); // Black
				
		double text_x, text_y;
		float bar_x, bar_y;	
		int bar_width = BAR_WIDTH, bar_height = BAR_HEIGHT;
		int text_width = g.getFont().getWidth(unitName);
		
		double currHP = (float)getHP()/(float)getmaxHP();
		
		if (text_width + TEXT_EDGE > bar_width){
			bar_width = text_width + TEXT_EDGE;
		}

		bar_x = (float)(xPos - bar_width/2);
		bar_y = (float)(yPos - OVERHEAD_BAR);
		text_x = xPos - g.getFont().getWidth(unitName)/2;
		text_y = yPos - OVERHEAD_TEXT;		
		
		// Set and fill bar with colors.
		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, bar_width, bar_height);
		g.setColor(BAR);
		g.fillRect(bar_x, bar_y, (float)(currHP * bar_width), (float) bar_height);
		g.setColor(TEXT);
		g.drawString(unitName, (float) text_x, (float) text_y);

		return true;
	}
}
