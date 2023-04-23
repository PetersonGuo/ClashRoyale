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
    
    double[][] DEFAULT_VALUES = { // Default values for the stats
        {5, 0, 20, 1}, // First Value: Default value, Second: Min Value, Third: Max Value, Fourth: Increment
        {10, 1, 20, 1},
        {1, 0, 4, 0.25},
        {120, 30, 300, 10},
        {1, 0.25, 4, 0.25},
        {1, 0.25, 4, 0.25},
        {1, 0.25, 4, 0.25},
        {1, 0.25, 4, 0.25},
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
        "Elixir Speed",
        "Game Duration",
        "Troop Health Multiplyer",
        "Troop Damage Multiplyer",
        "Tower Health Multiplyer",
        "Tower Damage Multiplyer",
    };
    
    Color ELIXIR_COLOR = Color.RED; // Elixir color
}