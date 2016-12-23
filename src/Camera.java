/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Your name> <Your login>
 */

import org.newdawn.slick.SlickException;

/** Represents the camera that controls our viewpoint.
 */
public class Camera
{

    /** The unit this camera is following */
    private Player unitFollow;
    
    /** The width and height of the screen */
    /** Screen width, in pixels. */
    private int screenwidth;
    /** Screen height, in pixels. */
    private int screenheight;

    /** The camera's position in the world, in x and y coordinates. */
    private int xPos;
    private int yPos;

    /** Create a new Camera object.
     * 
     * @param player The player to follow
     * @param screenwidth The width of the viewport in pixels
     * @param screenheight The height of the viewport in pixels
     */
    public Camera(Player player, int screenwidth, int screenheight)
    {   
        this.unitFollow = player;
        this.screenwidth = screenwidth;
        this.screenheight = screenheight  - RPG.PANEL_HEIGHT;
        xPos = (int)unitFollow.getxPos() - this.screenwidth/2;
        yPos = (int)unitFollow.getyPos() - this.screenheight/2;
    }

    /** Update the game camera to re-centre its viewpoint around the player 
     */
    public void update()
    throws SlickException
    {
        //Update the camera based on the player's position
        xPos = (int) unitFollow.getxPos() - (screenwidth/2);
        yPos = (int) unitFollow.getyPos() - (screenheight/2); 
        
    	
    }
    
    /** Returns the x coordinate of the camera's top left corner.
     */
    public int getxPos() {
        return xPos;
    }

    /** Returns the y coordinate of the camera's top left corner.
     */
    public int getyPos() {
        return yPos;
    }
    
    /** Returns the minimum x value on screen 
     */
    public int getMinX(){
        return xPos;
    }
    
    /** Returns the maximum x value on screen 
     */
    public int getMaxX(){
        return xPos + screenwidth;
    }
    
    /** Returns the minimum y value on screen 
     */
    public int getMinY(){
        return yPos;
    }
    
    /** Returns the maximum y value on screen 
     */
    public int getMaxY(){
        return yPos+screenheight;
    }

    /** Tells the camera to follow a given unit.
     * 
     * @param unit The new unit to follow
     */
    public void followUnit(Object unit)
    {
        if(unit instanceof Player){
            unitFollow = (Player) unit;
        }
    }
    
    /** Checks whether the object is already rendered on screen.
     * @param object unit object to be checked
     * @return True when object is already on screen.
     */
    public boolean onScreen(Unit object) {
    	
    	if (object.getxPos() > this.getMinX() 
    		&& object.getxPos() < this.getMaxX()
    		&& object.getyPos() > this.getMinY() 
    		&& object.getyPos() < this.getMaxY())
    		return true;
    	else
    		return false;
    }
}