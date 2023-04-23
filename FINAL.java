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
    int GAME_TIME = 7200;
    int ARROW_OFFSET = 10; // Offset of the arrows
    int STAT_SIZE = 30; // Size of the stats
    int STAT_SECTION_SIZE = WORLD_HEIGHT / 4;   // Size of the stat section
    int CARD_SPACING = 4; // Spacing between cards
    
    // Default values for the stats
    double[][] DEFAULT_VALUES = { // First Value: Default value, Second: Min Value, Third: Max Value, Fourth: Increment
        {5, 0, 20, 1}, // Start Elixir
        {10, 0, 20, 1}, // Max Elixir
        {1, 0, 20, 1}, // Elixir Time
        {1, 0, 20, 1}, // Difficulty3
        {1, 0, 20, 1}, // Difficulty4
        {1, 0, 20, 1}, // Difficulty5
        {1, 0, 20, 1}, // Difficulty6
        {1, 0, 20, 1}, // Difficulty7
        {1, 0, 20, 1}  // Difficulty8
    };
    
    int NUM_OF_TROOPS = 8; // Number of troops
    int[] ELIXIR_COST = { // Elixir cost of troops, size must match NUM_OF_TROOPS
        3,
        4,
        3,
        5,
        3,
        3,
        4,
        4
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