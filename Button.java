import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Button to Control User Input
 * 
 * @author Jordan Cohen 
 * @version 1.2
 */
public class Button extends Actor {
    private Font buttonFont = new Font("Courier New",  true,  false,  24);
    public static final double FONT_RATIO = 0.58;
    private String myText;
    private int drawX;
    private int drawY;
    private GreenfootImage image;
    private GreenfootImage touchingImage;
    private boolean touching;
    
    /** 
     * Control the main button features
     */
    public Button (String text) {
        image = new GreenfootImage (240, 40);
        touchingImage = new GreenfootImage (240, 40);
        myText = text;
        // Prepare for centering:
        // How many letters?
        int wordLength = myText.length();  
        // How many pixels?
        int wordWidth = (int)(wordLength * buttonFont.getSize() * FONT_RATIO); 
        drawX = (image.getWidth() - wordWidth)/2;
        drawY = image.getHeight() - (image.getHeight() - buttonFont.getSize()) /2;
        //System.out.println("ww: " + wordWidth + " drawX: " + drawX);
      
        draw();
        setImage (image);
    }
    
    /** 
     * Control how the button would look
     */
    private void draw () {
        image.setColor(Color.ORANGE);
        image.fillRect(drawY/2, 0, image.getWidth()-drawY, drawY);
        image.fillOval(0, 0, drawY, drawY);
        image.fillOval(image.getWidth()-drawY, 0, drawY, drawY);
        image.setFont (buttonFont);
        image.setColor(Color.WHITE);
        image.drawString (myText, drawX, drawY*3/4);

        // Outline
        touchingImage.setColor(Color.BLACK);
        touchingImage.fillRect(drawY/2, 0, image.getWidth()-drawY, drawY);
        touchingImage.fillOval(0, 0, drawY, drawY);
        touchingImage.fillOval(image.getWidth()-drawY, 0, drawY, drawY);
        touchingImage.setColor(Color.WHITE);
        int outlinepx = 1;
        touchingImage.fillRect(drawY/2+outlinepx, outlinepx, image.getWidth()-drawY-outlinepx*2, drawY-outlinepx*2);
        touchingImage.fillOval(outlinepx, outlinepx, drawY-2, drawY-2);
        touchingImage.fillOval(image.getWidth()-drawY+outlinepx, outlinepx, drawY-outlinepx*2, drawY-outlinepx*2);
        
        touchingImage.setColor(Color.BLACK);
        touchingImage.setFont (buttonFont);
        touchingImage.drawString (myText, drawX, drawY*3/4);
    }
    
    /** 
     * Return to check for mouse clicks
     */
    public boolean MouseClicked() {
        return touching;
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseMoved(this)) {
            setImage(touchingImage);
        } else if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
           setImage(image);
        }
    }
    
    public int getHeight() {
        return getImage().getHeight();
    }
    
    public int getWidth() {
        return getImage().getWidth();
    }
}