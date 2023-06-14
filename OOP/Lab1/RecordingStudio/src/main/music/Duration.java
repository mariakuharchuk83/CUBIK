package main.music;

public class Duration {
    private int minutes;
    private int seconds;

    public Duration(int min, int sec){
        this.minutes = min;
        this.seconds = sec;
    }


    public void setDuration(int minutes, int seconds){
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes(){return minutes;}
    public int getSeconds(){return seconds;}

    public String toString(){
        if(seconds >= 10)
            return(minutes + ":" + seconds);
        else
            return(minutes + ":0" + seconds);
    }

}
