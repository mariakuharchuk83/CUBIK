package test.music;

import main.music.Duration;
import org.junit.Test;

import static org.junit.Assert.*;

public class DurationTest {
    Duration songDurationTest = new Duration(0,1);
    @Test
    public void setDuration() {
        songDurationTest.setDuration(2,3);
    }

    @Test
    public void getMinutes() {
        songDurationTest.getMinutes();
    }

    @Test
    public void getSeconds() {
        songDurationTest.getSeconds();
    }

    @Test
    public void testToString() {
        songDurationTest.toString();
    }
}