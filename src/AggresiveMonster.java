/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Nelson Chen <nelsonc1>
 */

import org.newdawn.slick.SlickException;

import java.util.Random;

/** The aggressive monsters that attacks when triggered.
 */
public abstract class AggresiveMonster extends Monster {

	private static final double AGGRO_SPEED = 0.25;
	private static final int MOVE_TRIGGER = 150;
	private static final int ATTACK_TRIGGER = 50;
	
	private int maxDamage;
	private int damage;
	
	private double dir_x, dir_y;
	private double xDist, yDist;
	private double dist;
	
	/** Returns the maximum damage of aggressive monsters.
	 * @return The maximum damage of aggressive monsters.
	 */
	public int getmaxDamage() {
		return maxDamage;
	}
	
	/** Sets the maximum damage of aggressive monsters.
	 * @param maxDamage The maximum damage of aggressive monsters.
	 */
	public void setmaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}
	
	/** Creates an aggressive monster that attacks on trigger.
	 * @param image_path The image file location of aggressive monster.
	 * @param xPos The x location of aggressive monster in pixels.
	 * @param yPos The y location of aggressive monster in pixels.
	 * @param maxHP The maximum health points of aggressive monster.
	 * @param unitName The name of aggressive monster.
	 * @param cooldown The cooldown of aggressive monster.
	 * @param maxDamage The maximum damage of aggressive monster.
	 * @throws SlickException
	 */
	public AggresiveMonster(String image_path, double xPos, double yPos, int maxHP, String unitName, int cooldown, int maxDamage) 
	throws SlickException 
	{
		super(image_path, xPos, yPos, maxHP, unitName, AGGRO_SPEED);
		setCooldown(cooldown);
		setCooldownTimer(0);
		this.maxDamage = maxDamage;
	}

	/** Updates the current status of aggressive monster.
	 * @param delta Time passed since last frame (milliseconds).
	 * @param world The world the monster is on (to check blocking).
	 * @throws SlickException
	 */
	public void update(int delta, World world)
	throws SlickException
	{
	
		dist = checkDistance(world.getPlayer());
			
		if(getHP() <= 0) {
			world.getDeadList().add(this);
			return;
		}
				

	    setCooldownTimer(getCooldownTimer() - delta);

					
		if(dist <= MOVE_TRIGGER && dist > ATTACK_TRIGGER){
				
			// Chase player based on Algorithm 1.
			xDist = world.getPlayer().getxPos() - this.getxPos();
			yDist = world.getPlayer().getyPos() - this.getyPos();
			dir_x = (xDist/dist) * delta;
			dir_y = (yDist/dist) * delta;
				
			move(dir_x, dir_y, delta, world);
		}
		else if(dist <= ATTACK_TRIGGER && getCooldownTimer() <= 0){
			attack(world.getPlayer());
		}
	}
	
	/** Attacks the player on trigger.
	 * @param player The player of the game.
	 */
	private void attack(Player player)
	{
		Random rand = new Random();
		damage = rand.nextInt(maxDamage);
		
		player.setHP(player.getHP() - damage);
		
		setCooldownTimer(getCooldown());
	}
}
