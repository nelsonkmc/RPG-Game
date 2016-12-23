/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

import java.util.Random;

/** The passive monsters of the game that only runs away and move.
 */
public abstract class PassiveMonster extends Monster {
	
	private static final int DIRECTION = 3;
	private static final double PASSIVE_SPEED = 0.2;
	
	public static final int EVADE_TIME = 5000;
	public static final int MOVEMENT_TIME = 3000;
	
	// Initialise variables.
	private double dist;
	private double dir_x, dir_y;
	private double xDist, yDist;
	private int movementTimer;
	private int evadeTimer;
	
	/** Returns the timer for monsters to move.
	 * @return The timer to move.
	 */
	public int getMovementTimer(){
		return movementTimer;
	}
	
	/** Sets the timer for monsters to move.
	 * @param movementTimer The timer to move.
	 */
	public void setMovementTimer(int movementTimer){
		this.movementTimer = movementTimer;
	}
	
	/** Returns timer for monster running away 
	 * @return The timer to evade.
	 */
	public int getEvadeTimer(){
		return evadeTimer;
	}
	
	/** Sets timer for monster running away 
	 * @param evadeTimer The timer to evade.
	 */
	public void setEvadeTimer(int evadeTimer) {
		this.evadeTimer = evadeTimer;
	}
	
	/** Creates a passive monster that does not attack the player.
	 * @param image_path The image file location.
	 * @param xPos The monster's x location in pixels.
	 * @param yPos The monster's y location in pixels.
	 * @param HP The health point of the monster.
	 * @param unitName The name of the unit.
	 * @throws SlickException
	 */
	public PassiveMonster(String image_path, double xPos, double yPos, int HP, String unitName) 
	throws SlickException 
	{
		super(image_path, xPos, yPos, HP, unitName, PASSIVE_SPEED);
		evadeTimer = 0;
		movementTimer = 0;
	}

	/** Updates passive monsters to current status.
	 * @param delta Time passed since last frame (milliseconds).
	 * @param world The world the passive monster is on.
	 * @throws SlickException
	 */
	public void update(int delta, World world) 
	throws SlickException 
	{
				
		if(getHP() <= 0) {
			world.getDeadList().add(this);
			return;
		}
				
		movementTimer -= delta;
		evadeTimer -= delta;
				
		if(evadeTimer <= 0) {
			runAway(delta, world);
		}
		else {
			move(delta, world);
		}
	}
	
	/** Monsters run away when player is near
	 * @param delta Time passed since last frame (milliseconds).
	 * @param world The world the passive monster is on.
	 * @throws SlickException
	 */
	private void runAway(int delta, World world) 
	throws SlickException 
	{				
		dist = checkDistance(world.getPlayer());
				
		// Run away based on Algorithm 1.
		xDist = this.getxPos() - world.getPlayer().getxPos();
		yDist = this.getyPos() - world.getPlayer().getyPos();
		dir_x = (xDist/dist) * delta;
		dir_y = (yDist/dist) * delta;
				
		move(dir_x, dir_y, delta, world);
	}
	
	/** Monsters move around the map in direction (-1,0,1).
	 * @param delta Time passed since last frame (milliseconds).
	 * @param world The world the passive monster is on.
	 * @throws SlickException
	 */
	private void move(int delta, World world) 
	throws SlickException 
	{	
		Random rand = new Random();
		
		if(movementTimer <= 0) {
			dir_x = rand.nextInt(DIRECTION) - 1;
			dir_y = rand.nextInt(DIRECTION) - 1;
			if(dir_x != 0 && dir_y != 0){
				movementTimer = MOVEMENT_TIME;
			}
		}
		
		move(dir_x, dir_y, delta, world);
	}
}
