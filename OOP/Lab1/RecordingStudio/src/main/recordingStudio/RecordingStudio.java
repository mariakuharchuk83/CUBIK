package main.recordingStudio;

import java.util.List;
import main.music.Song;
import main.music.Duration;


public class RecordingStudio {
    public void getSongs(List<Song> songs) {
        for(Song song : songs)
            System.out.println(song.toString());
        System.out.println();
    }

    public void getTotalDuration(List<Song> songs){
        Integer totalMinutes = 0;
        Integer totalSeconds = 0;
        for(int i = 0; i < songs.size(); i++) {
            totalMinutes += songs.get(i).getSongDuration().getMinutes();
            totalSeconds += songs.get(i).getSongDuration().getSeconds();
            if (totalSeconds >= 60) {
                totalMinutes++;
                totalSeconds -= 60;
            }
        }
        System.out.println("\nTotal duration of all tracks: " + totalMinutes + ":" + totalSeconds + '\n');
    }

    public void sortingByStyle(List<Song> songs, String genre){
        System.out.println("\nSorting by genre:");
        for(Song song : songs) {
            if (song.getSongGenre() == genre)
                System.out.println(song.toString());
        }
        for(Song song : songs) {
            if(song.getSongGenre() != genre)
                System.out.println(song.toString());
        }
        System.out.println();
    }

    public void findByRange(Duration start, Duration finish, List<Song> songs){
        System.out.println("Find by duration:");
        Boolean isEmpty = true;
        for(int i = 0; i < songs.size(); i++){

            Integer trackToFind = 0;
            trackToFind += songs.get(i).getSongDuration().getMinutes() * 60;
            trackToFind += songs.get(i).getSongDuration().getSeconds();

            Integer startSum = 0;
            startSum += start.getMinutes() * 60;
            startSum += start.getSeconds();

            Integer finishSum = 0;
            finishSum += finish.getMinutes() * 60;
            finishSum += finish.getSeconds();

            if(trackToFind >= startSum && trackToFind <= finishSum) {
                System.out.println(songs.get(i).toString());
                isEmpty = false;
            }
        }
        if(isEmpty){
            System.out.println("There are no such tracks");
        }
    }

}
