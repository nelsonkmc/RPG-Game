/* SWEN2003 Object Oriented Software Development
 * RPG Game Engine
 * Sample Solution
 * Author: Matt Giuca <mgiuca>
 * Editor: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.Random;
import java.util.ArrayList;

/** The character which the user plays as.
 */
public class Player extends Unit
{
	// Constant Declaration
	private static final int NEAR = 50;
	private static final int INITIAL_MAXDMG = 26;
	private static final int INITIAL_CD = 600;
	private static final int INITIAL_HP = 100;
	private static final double MOVEMENT_SPD = 0.25;
	
    private Image img = null;
    private Image img_flipped = null;
    private Image panel = null;
    
    private ArrayList<Item> inventory = null;
    
    // In pixels
    private double xPos, yPos;
    private double width, height;
    private boolean face_left = false;
    
    private int maxDamage;

    // Pixels per millisecond
    private static final double SPEED = 1;
    
    /** Returns the x coordinate of the player (pixels). 
     * @return the x-coordinate of player.
     */
    public double getxPos()
    {
        return xPos;
    }
    
    /** Set x-coordinate of the player. 
     * @param xPos The x-coordinate of the player.
     */
    public void setxPos(double xPos) {
		this.xPos = xPos;
	}

    /** Returns the y coordinate of the player (pixels). 
     * @return The y-coordinate of the player.
     */
    public double getyPos()
    {
        return yPos;
    }
    
    /** Sets y-coordinate of the player 
     * @param yPos The y-coordinate of the player.
     */
    public void setyPos(double yPos) {
		this.yPos = yPos;
	}

    /** Returns the max damage of the player.
     * @return The maximum damage of the player.
     */
    public int getmaxDamage()
    {
    	return maxDamage;
    }
    
    /** Sets max damage of the player.
     * @param The maximum damage of the player.
     */
    public void setmaxDamage(int maxDamage)
    {
    	this.maxDamage = maxDamage;
    }
    
    /** Returns the inventory list from player.
     * @return The inventory of the player.
     */
    public ArrayList<Item> getInventory(){
    	return inventory;
    }
    
    /** Creates a new Player.
     * @param image_path The image file location of player.
     * @param x The Player's starting x location in pixels.
     * @param y The Player's starting y location in pixels.
     * @throws SlickException
     */
    public Player(String image_path, double xPos, double yPos)
        throws SlickException
    {
    	super(image_path, xPos, yPos, INITIAL_HP , "Player", MOVEMENT_SPD);
        img = new Image(image_path);
        panel = new Image(RPG.ASSETS_PATH + "/panel.png");
        img_flipped = img.getFlippedCopy(true, false);
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.maxDamage = INITIAL_MAXDMG;	
        setCooldown(INITIAL_CD);
        
        inventory = new ArrayList<Item>();
    }

    /** Move the player in a given direction.
     * Prevents the player from moving outside the map space, and also updates
     * the direction the player is facing.
     * @param world The world the player is on (to check blocking).
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void move(World world, double dir_x, double dir_y, double delta)
    {
        // Update player facing based on X direction
        if (dir_x > 0)
            this.face_left = false;
        else if (dir_x < 0)
            this.face_left = true;

        // Move the player by dir_x, dir_y, as a multiple of delta * speed
        double new_x = this.xPos + dir_x * delta * SPEED;
        double new_y = this.yPos + dir_y * delta * SPEED;

        // Move in x first
        double x_sign = Math.signum(dir_x);
        if(!world.terrainBlocks(new_x + x_sign * width / 2, this.yPos + height / 2) 
                && !world.terrainBlocks(new_x + x_sign * width / 2, this.yPos - height / 2)) {
            this.xPos = new_x;
        }
        
        // Then move in y
        double y_sign = Math.signum(dir_y);
        if(!world.terrainBlocks(this.xPos + width / 2, new_y + y_sign * height / 2) 
                && !world.terrainBlocks(this.xPos - width / 2, new_y + y_sign * height / 2)){
            this.yPos = new_y;
        }
   
    }
    
    /** Updates the current status of player.
     * @param world The world the player is on
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta delta Time passed since last frame (milliseconds).
     * @param key_z The attack key.
     * @param key_x The pick up key.
     * @param key_n The talk key.
     * @throws SlickException.
     */
    public void update(World world, int dir_x, int dir_y, int delta, int key_z, int key_x, int key_n)
    	throws SlickException
    {
    	// Respawn player if dead
    	if(getHP() <= 0){
    		setxPos(World.PLAYER_START_X);
    		setyPos(World.PLAYER_START_Y);
    		setHP(getmaxHP());
    		return;
    	}
    	
    	move(world, dir_x, dir_y, delta);
    	
    	setCooldownTimer(getCooldownTimer() - delta);   	
    	
    	// Attacks monster on key Z when within proximity
    	if (key_z == 1 && getCooldownTimer() <= 0){
    		for (Monster m : world.getMonsterList()){   			
    			if (checkDistance(m) <= NEAR){
    				attack(m);  				
    			}
    		}
    	}
    	
    	// Takes item on key X and when within proximity
    	if (key_x == 1){
    		for (Item i : world.getItemList()){
    			if (checkDistance(i) <= NEAR && !i.getTaken()){
    				i.take(this);
    			}
    		}
    	}
    	
    	// Talks to NPC on key N when within proximity
    	if (key_n == 1){
    		for (NPC n : world.getNPCList()){
    			if (checkDistance(n) <= NEAR){
    				n.talk(this, world);   				
    			}
    		}	
    	}
    	
    }
    
    /** Deal random damage within max damage to monster.
     * @param monster The monster object.
     */
    private void attack(Monster monster)
    {
    	Random rand = new Random();
    	int damage = rand.nextInt(maxDamage);
    	
    	monster.setHP(monster.getHP() - damage);
    	
    	// Passive Monsters runaway
    	if(monster instanceof PassiveMonster){
    		((PassiveMonster) monster).setEvadeTimer(0);
    	}
    	
    	setCooldownTimer(getCooldown());
    	
    }

    /** Draw the player to the screen at the correct place.
     */
    public void render()
    {
        Image which_img;
        which_img = this.face_left ? this.img_flipped : this.img;
        which_img.drawCentered((int) xPos, (int) yPos);
    }
    
    /** Draws the status panel of player at the bottom of screen.
     * @param g The current Graphics context.
     */
    public void renderPanel(Graphics g)
    {
        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT);

        // Display the player's health
        text_x = 15;
        text_y = RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        text = 	getHP() + "/" + getmaxHP();                                 

        bar_x = 90;
        bar_y = RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = (float)getHP()/(float)getmaxHP();                        
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        text = Integer.toString(getmaxDamage());                     
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        text = Integer.toString(getCooldown());                      
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);

        inv_x = 490;
        inv_y = RPG.SCREEN_HEIGHT - RPG.PANEL_HEIGHT
            + ((RPG.PANEL_HEIGHT - 72) / 2);
        // for (each item in the player's inventory)                
        for (Item i : getInventory())
        {
            // Render the item to (inv_x, inv_y)
            i.getImage().draw(inv_x,inv_y);
        	inv_x += 72;
        }
    }
}
