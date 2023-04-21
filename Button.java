import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Button to Control User Input
 * 
 * @author Jordan Cohen 
 * @version 1.2
 */
public class Button extends Actor {
    private Font buttonFont = new Font("Courier New",  true,  false,  24); // Font for the button
    public static final double FONT_RATIO = 0.58; // Ratio of font size to image height
    private String myText; // Text to display on the button
    private int drawX, drawY; // X and Y coordinates for drawing the text
    private GreenfootImage image, touchingImage; // Images for the button
    private boolean touching; // Is the mouse over the button?
    
    /** 
     * Constructor for objects of class Button
     * 
     * @param text The text to display on the button
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
    public void act() {
        if (Greenfoot.mouseMoved(this))
            setImage(touchingImage);
        else if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
           setImage(image);
    }
    
    /**
     * Return the height of the button
     */
    public int getHeight() {
        return getImage().getHeight();
    }
    
    /**
     * Return the width of the button
     */
    public int getWidth() {
        return getImage().getWidth();
    }
}