package test.recordingStudio;

import main.music.Duration;
import main.music.Song;
import main.recordingStudio.RecordingStudio;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RecordingStudioTest {
    RecordingStudio testStudio = new RecordingStudio();
    List<Song> testSongsList = new ArrayList<>();

    RecordingStudioTest() {
        testSongsList.add(new Song("testSong1", "TEST1", 1,0));
        testSongsList.add(new Song("testSong2", "TEST1", 0,50));
        testSongsList.add(new Song("testSong3", "TEST2", 2,4));
        testSongsList.add(new Song("testSong4", "TEST2", 1,16));
    }

    @Test
    public void getSongs() {
        testStudio.getSongs(testSongsList);
    }

    @Test
    public void getTotalDuration() {
        testStudio.getTotalDuration(testSongsList);
    }

    @Test
    public void sortingByStyle() {
        testStudio.sortingByStyle(testSongsList, "TEST1");
    }

    @Test
    public void findByRange() {
        Duration testDurationStart = new Duration(0,50);
        Duration testDurationFinish = new Duration(1,35);
        testStudio.findByRange(testDurationStart,testDurationFinish, testSongsList);
    }
}