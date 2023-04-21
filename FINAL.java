import greenfoot.*;
/**
 * Holds all Final variables for the simulation which should only be modified outside of the simulation.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public interface FINAL {
    int WORLD_HEIGHT = 750; // Height of the world
    int WORLD_WIDTH = 423; // Width of the world
    int ARROW_OFFSET = 10; // Offset of the arrows
    int STAT_SIZE = 30; // Size of the stats
    int STAT_SECTION_SIZE = WORLD_HEIGHT / 4;   // Size of the stat section
    int CARD_SPACING = 8; // Spacing between cards
    int NUM_OF_TROOPS = 5; // Number of troops
    int[] DEFAULT_VALUES = { // Default values for the stats
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
    };
    
    String NEXT_WORLD_BUTTON = "Space"; // Next world button
    String[] STAT_NAMES = { // Stat names
        "Start Elixir",
        "Max Elixir",
        "Elixir Time",
        "Difficulty3",
        "Difficulty4",
        "Difficulty5",
        "Difficulty6",
        "Difficulty7",
        "Difficulty8"
    };
    
    Color ELIXIR_COLOR = Color.RED; // Elixir color
}