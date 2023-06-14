package main.music;

public class Song {
    private String name;
    private String genre;
    private Duration duration;

    public Song(String name, String genre, int minutes, int seconds){
        this.name = name;
        this.genre = genre;
        this.duration = new Duration(minutes, seconds);
    }

    public String getSongName(){return name;}
    public Duration getSongDuration(){return duration;}
    public String getSongGenre(){return genre;}

    public void setSongName(String name) {this.name = name;}
    public void setSongDuration(int minutes, int seconds) {
        duration.setDuration(minutes, seconds);
    }
    public void setTrackGenre(String genre) {this.genre = genre;}

    public String toString(){
        return "Track Name: " + getSongName() + ", Duration: " + getSongDuration() + ", Genre: " + getSongGenre();
    }

}
