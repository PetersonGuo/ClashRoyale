import greenfoot.GreenfootSound;
import java.util.ArrayList;

/**
 * An extension of Sound to give it more functionality and easier interface such as sounds in parallel and singular instance functions
 * Taken from Grade 11 CS Final and improved
 * 
 * @author Peterson Guo
 * @version 1.3
 */
class Sound {
    private static ArrayList<Sound> instances = new ArrayList<>(); // Tracks all instances of this class
    
    private GreenfootSound music;  // Sound instance
    private ArrayList<GreenfootSound> musics; // Sound instances
    private boolean multiplay; // Allow multiple sounds to play at once
    /**
     * Constructor for objects of class Sound.
     * 
     * @param fileName Pass in file name as string with the extension
     */
    Sound(String fileName) {    // Use other constructor with presets
        this(fileName, false);
    }
    
    /**
     * Constructor for objects of class Sound.
     * 
     * @param fileName Pass in file name as a string with the extension
     * @param multiplay Allow multiple sounds to play at once
     */
    Sound(String fileName, boolean multiplay) { // Setup
        try { // Run with sound if possible
            music = new GreenfootSound(fileName);
        } catch (Exception e) {
            System.out.println(e); // Soft error
            music = new GreenfootSound(null);
        }
        this.multiplay = multiplay;
        music.pause();
        multiPlay();
        instances.add(this);
    }
    
    /**
     * Loop audio
     */
    void loop() {
        boolean play = music.isPlaying();   // Store if the music is currently being played
        if (multiplay) {
            for (GreenfootSound i : musics) {
                i.playLoop();
                i.stop();
            }
            if (play)
                play();
        } else {
            music.playLoop();
            if (play)   // Continue with last state
                play(music.getVolume());
            else 
                music.stop();
        }
    }
    
    /**
     * Play Sound at 50% volume
     */
    void play() {   // Set a default volume
        play(50);
    }
    
    /**
     * Play Sound
     * 
     * @param level Play with volume as a percentage
     */
    void play(int level) {
        if (multiplay) {
            boolean played = false;
            ArrayList<GreenfootSound> temp = new ArrayList<>(musics); // Create copy of list before modification
            for (GreenfootSound i : musics) { // Parse list and remove redundant instances
                if (!played) {
                    if (!i.isPlaying()) { // Reuse an unused sound instance
                        i.setVolume(level);
                        i.play();
                        played = true;
                    }
                } else
                    temp.remove(i); // Remove unused sound from array
            }
            if (!played) { // If none are available, add another instance
                temp.add(music);
                temp.get(temp.size()-1).setVolume(level);
                temp.get(temp.size()-1).play();
            }
            musics = new ArrayList<>(temp);
        } else { // Singular
            music.setVolume(level);
            music.play();
        }
    }
    
    /**
     * Pause all currently running sound effects
     */
    void pause() {
        if (multiplay) {
            for (GreenfootSound i : musics) {
                i.setVolume(0);
                i.pause();
            }
        } else {
            music.setVolume(0);
            music.pause();
        }
    }
    
    /**
     * Stop all currently running sound effects
     */
    void stop() {
        if (multiplay) {
            for (GreenfootSound i : musics) {
                i.setVolume(0);
                i.stop();
            }
        } else {
            music.setVolume(0);
            music.stop();
        }
    }
    
    /**
     * Enable multiPlay
     * @param allow Enable multiplay
     */
    void multiPlay(boolean allow) {
        multiplay = allow;
        multiPlay();
    }
    
    /**
     * Initiate arraylist of sound
     */
    void multiPlay() {
        if (multiplay) { // Create array of one sound
            musics = new ArrayList<>(1);
            for (GreenfootSound i : musics) {
                i = music;
                i.stop();
            }
        }
    }
    
    /**
     * Return all instances of this class
     * @return ArrayList of all instances of this class
     */
    static ArrayList<Sound> getInstances() {
        return instances;
    }
}