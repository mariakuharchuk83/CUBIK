package test.music;

import main.music.Song;
import org.junit.Test;

import static org.junit.Assert.*;

public class SongTest {
    Song songTest = new Song("testSong", "TEST",1,0);

    @Test
    public void getSongName() {
        songTest.getSongName();
    }

    @Test
    public void getSongDuration() {
        songTest.getSongDuration();
    }

    @Test
    public void getSongGenre() {
        songTest.getSongGenre();
    }

    @Test
    public void setSongName() {
        songTest.setSongName("newTestSong");
    }

    @Test
    public void setSongDuration() {
        songTest.setSongDuration(2,0);
    }

    @Test
    public void setTrackGenre() {
        songTest.setTrackGenre("newTEST");
    }

    @Test
    public void testToString() {
        songTest.toString();
    }
}