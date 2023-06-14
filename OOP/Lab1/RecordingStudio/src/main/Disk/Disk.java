package main.Disk;

import main.music.Duration;
import main.music.Song;
import main.recordingStudio.RecordingStudio;

import java.util.ArrayList;
import java.util.List;

public class Disk {
    private List<Song> songList = new ArrayList<>();
    private RecordingStudio studio = new RecordingStudio();

    public Disk(){
        songList.add(new Song("Alok - Always feel like", "Dance", 3,2));
        songList.add(new Song("Imanbek - Goodbye", "Dance", 2,24));
        songList.add(new Song("OTOY - CVIT", "Rap", 3,2));
        songList.add(new Song("АрХангел - Оригами", "Rap", 3,16));
        songList.add(new Song("Valentine Lawrence - Оплачу за тебе комуналкку", "Pop", 3,45));
        songList.add(new Song("John Newman - Love me again", "Pop", 3,45));
        songList.add(new Song("Nia Barbia - Скажи паляниця", "Hip-Hop", 2,8));
        songList.add(new Song("KALUSH - Сонячна", "Hip-Hop", 3,13));
        songList.add(new Song("Thekomakoma - Киянка", "Dance", 1,35));

        studio.getSongs(songList);
        studio.getTotalDuration(songList);
        studio.sortingByStyle(songList, "Dance");
        Duration start = new Duration(1,20);
        Duration finish = new Duration(2,30);
        studio.findByRange(start, finish, songList);

    }

}
