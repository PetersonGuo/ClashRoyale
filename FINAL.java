import greenfoot.*;
/**
 * Holds all Final variables for the simulation which should only be modified outside of the simulation.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public interface FINAL {
    int WORLD_HEIGHT = 750;
    int WORLD_WIDTH = 423;
    int ARROW_OFFSET = 10;
    int STAT_SIZE = 30;
    int STAT_SECTION_SIZE = WORLD_HEIGHT / 4;
    int CARD_SPACING = 8;
    int NUM_OF_TROOPS = 5;
    int[] DEFAULT_VALUES = {
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
    
    String NEXT_WORLD_BUTTON = "Space";
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
    
    Color ELIXIR_COLOR = Color.RED;
}