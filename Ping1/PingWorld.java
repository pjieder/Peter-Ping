import greenfoot.*;


/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    public static int paddle;
    
    private Counter scoreCounter;

    public PingWorld()
    { 
        // calling the other constructor with gameStarted = false.
        this(false);   
    }
    
    
    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
             scoreCounter = new Counter("Game Level: ");
              addObject(scoreCounter, 85, 20);
              
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK);
            // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
            addObject(new Ball(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
            Paddle player = new Paddle(144,46);
            addObject(player, 300, 650);
            addObject(new Computer(144,46),60, Greenfoot.getRandomNumber((WORLD_HEIGHT/4))+50);
            paddle++;
            
             
           
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    public void act()
    {
        if (paddle < 1)
        {
            addObject(new Computer(100,20),60, Greenfoot.getRandomNumber((WORLD_HEIGHT/4))+50);
            paddle++;
        }
    }
    public Counter getCounter(){
    return scoreCounter;
    }

}
