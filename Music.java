import greenfoot.GreenfootSound;
class Music {
    private GreenfootSound music, musics[];
    private boolean multiplay;
    private int it;
    
    Music(GreenfootSound file) {    // Use other constructor with presets
        this(file, false);
    }
    
    Music(GreenfootSound file, boolean multiplay) { // Setup
        music = file;
        this.multiplay = multiplay;
        music.pause();
        multiPlay();
        it = 0;
    }
    
    void loop() {   // Enable looping
        boolean play = music.isPlaying();   // Store if the music is currently being played
        if (multiplay) {
            for (int i = 0; i < musics.length; i++) {
                musics[i].playLoop();
                musics[i].stop();
            }
            if (play) {
                play();
            }
        } else {
            music.playLoop();
            if (play)   // Continue with last state
                play(music.getVolume());
            else 
                music.stop();
        }
    }
    
    void play() {   // Set a default volume
        play(50);
    }
    
    void play(int level) {  // Play music at a certain level
        if (multiplay) {
            it++;
            musics[it%20].setVolume(level);
            musics[it%20].play();
        } else {
            music.setVolume(level);
            music.play();
        }
    }
    
    void pause() {  // Pause music
        if (multiplay) {
            for (int i = 0; i < musics.length; i++) {
                musics[i].setVolume(0);
                musics[i].pause();
            }
        } else {
            music.setVolume(0);
            music.pause();
        }
    }
    
    void stop() {   // Stop music
        if (multiplay) {
            for (int i = 0; i < musics.length; i++) {
                musics[i].setVolume(0);
                musics[i].stop();
            }
        } else {
            music.setVolume(0);
            music.stop();
        }
    }
    
    void multiPlay(boolean allow) { // Allow enable multiplay
        multiplay = allow;
        multiPlay();
    }
    
    void multiPlay() {  // Update array
        if (multiplay) {
            musics = new GreenfootSound[20];
            for (int i = 0; i < musics.length; i++) {
                musics[i] = music;
                musics[i].stop();
            }
        }
    }
}