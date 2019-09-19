import greenfoot.*;
import java.util.*;


/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends Actor
{
    private static final int BALL_SIZE = 15;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;

    private int hit = 0;
    private int speed = 2;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private int delay;
    private GreenfootSound thump;
    private GreenfootSound pew;
    private GreenfootSound booo;
    
   
    

    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        createImage();
        init();
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
       
        setImage(new GreenfootImage ("ball.png"));
    }    
        /*GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
        ballImage.setColor(Color.BLACK);
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);*/
    
   
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkRestart();
            bouncePaddle();
            ballPace();
        }
    }
    /**
     * Creates the reference to the sound pew, sets a volume and plays the file.
     */
    public void Pew()
    {
        
        pew = new GreenfootSound("pew.wav");
        pew.setVolume(40);
        pew.play();
        
    }
    
    /**
     * Creates the reference to the sound file thump, sets a volume and plays the file.
     */
    public void Thump()
    {
        thump = new GreenfootSound("thump.wav");
        thump.setVolume(55);
        thump.play();
        

    }
    
    /**
     * Creates the sound booo, sets a volume and plays the file.
     */
    public void GameOver()
    {
        booo = new GreenfootSound("booo.wav");
        booo.setVolume(30);
        booo.play();
    }
    
    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2) ;
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        //System.out.println("isTouchingCeiling()");
        return (getY() <= BALL_SIZE);
        
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }

    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
                Thump();
            }
            
        }
         else if(isTouchingCeiling())
        {
                  System.out.println("isTouchingCeiling()");
                  revertVertically();
                  Thump();
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            ((PingWorld)getWorld()).getCounter().reset();
            init();
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }
        
       
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically()
    {
        //System.out.println("revertVertically()");
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        if(hasBouncedVertically){
        hasBouncedVertically = false;
        }
        else{ hasBouncedVertically = true;}
       
    }

    public void bouncePaddle()
    {
        Actor Paddle = getOneIntersectingObject(Paddle.class);
        if (Paddle !=null)
        {
            revertVertically();
            hit = hit + 1;
            Pew();

        }
        Actor Computer = getOneIntersectingObject(Computer.class);
        if (Computer !=null)
        {

            
            if(!hasBouncedVertically){
                System.out.println("computer   " + Computer.getY());
                System.out.println("ball    " + getY());
                Pew();

            }
            else{
               revertVertically();
               System.out.println("bounce");
               hit = hit + 1;
            }

            //revertVertically();
            

        }
    }
    

     /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }
    
    /**
     * If hit either player 10 times, go faster
     */
    public void ballPace()
    {
        if (hit == 10)
        {
            speed = speed + 1;
            hit = 0;
            ((PingWorld)getWorld()).getCounter().add(1);
            
        }
    }
  
}
