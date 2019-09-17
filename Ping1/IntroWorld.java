import greenfoot.*;


/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage background = getBackground();
        background.setColor(Color.WHITE);
        background.drawString("Hit <space> to start game...", WORLD_WIDTH / 2 - 50, WORLD_HEIGHT / 2);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("space"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
    
}
