/**
 * Holds all Final variables for the simulation which should only be modified outside of the simulation.
 * 
 * @author Peterson Guo
 * @version 1.0
 */
public interface FINAL {
    int WORLD_HEIGHT = 800;
    int WORLD_WIDTH = 500;
    int ARROW_OFFSET = 10;
    int STAT_SIZE = 30;
    int STAT_SECTION_SIZE = WORLD_HEIGHT / 4;
    
    
    String FONT = "Joystix";
    String NEXT_WORLD_BUTTON = "Space";
    String[] STAT_NAMES = {
        "Difficulty0",
        "Difficulty1",
        "Difficulty2",
        "Difficulty3",
        "Difficulty4",
        "Difficulty5",
        "Difficulty6",
        "Difficulty7",
        "Difficulty8",
        "HI",
        "JI",
        "W"
    };
}