import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Graphics;

/**
 * Counter that displays a text and number.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Counter extends Actor
{
    private static final Color textColor = new Color(255, 255, 255);
    
    private int value = 0;
    private int target = 1;
    private String text;
    private int stringLength;

    public Counter()
    {
        this("");
    }

    public Counter(String prefix)
    {
        text = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        GreenfootImage image = getImage();
        image.setColor(textColor);

        updateImage();
    }
    
    public void act() {
        if(value < target) {
            value++;
            updateImage();
        }
        else if(value > target) {
            value--;
            updateImage();
        }
    }

    public void add(int score)
    {
        target += score;
    }
    
    public void reset()
    {
        target = 1;
    }

    public int getValue()
    {
        return value;
    }

    /**
     * Make the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + value, 1, 12);
    }
}
