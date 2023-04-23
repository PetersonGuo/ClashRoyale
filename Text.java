import greenfoot.*;

/**
 * Add a Text box to the world
 * Choose between no background, different colors and font sizes
*/
public class Text extends Actor {
    private final double P_TO_PX = 4/3; // Ratio of pixels to points
    private GreenfootImage text; // Greenfoot image for the text
    private String str; // String for the text
    private Color color, background; // Color for the text and background
    private int size; // Font size
    
    /**
     * Create a text object
     * @param value The value to display
     * @param color The color of the text
     * @param size The size of the text
     */
    public Text(int value, Color color, int size) {this(Integer.toString(value), color, new Color(0, 0, 0, 0), size);}
    public Text(double value, Color color, int size) {this(Double.toString(value), color, new Color(0, 0, 0, 0), size);}
    /**
     * Create a text object
     * @param str The string to display
     * @param color The color of the text
     * @param size The size of the text
     */
    public Text(String str, Color color, int size) {this(str, color, new Color(0, 0, 0, 0), size);}
    /**
     * Create a text object
     * @param str The string to display
     * @param color The color of the text
     * @param background The background color
     * @param size The size of the text
     */
    public Text(String str, Color color, Color background, int size) { // Create a greenfoot TextImage (was not able to centre text in a greenfoot image)
        this.str = str;
        this.color = color;
        this.background = background;
        this.size = size;
        text = new GreenfootImage(str, size, color, background);
        setImage(text);
    }
    /**
     * Create a text object with a custom font
     * @param str The string to display
     * @param color The color of the text
     * @param size The size of the text
     * @param f The font to use
     */
    public Text(String str, Color color, int size, String f) { // Create a greenfoot TextImage (was not able to centre text in a greenfoot image)
        this.str = str;
        this.color = color;
        this.background = background;
        this.size = size;
        Font font = new Font(f, size);
        text = new GreenfootImage((int)(str.length() * font.getSize() * P_TO_PX), size);
        text.setFont(font);
        text.drawString(str, 0, 0);
        setImage(text);
    }
    
    // Used for updating score
    public void updateText(int str) {updateText(Integer.toString(str));}
    public void updateText(double str) {updateText(Double.toString(str));}
    public void updateText(String str) {
        this.str = str;
        text = new GreenfootImage(str, size, color, background);
        setImage(text);
    }
    
    // Mutators
    public void changeColor(Color color) {
        this.color = color;
        updateText(str);
    }
    
    public void changeBackgroundColor(Color color) {
        this.background = color;
        updateText(str);
    }
    
    // Accessors
    public int fontSize() {return size;}
    public int getWidth() {return getImage().getWidth();}
    public int getHeight() {return getImage().getHeight();}
    public String getString() {return str;}
}
