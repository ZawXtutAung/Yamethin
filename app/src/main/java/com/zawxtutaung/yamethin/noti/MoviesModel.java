package com.zawxtutaung.yamethin.noti;

/**
 * Created by zawxtutaung on 1/4/2017.
 */
public class MoviesModel {
    private String movie;
    private String year;
    private String duration;
    private String director;
    private String image;





    public MoviesModel(){
    }
    public MoviesModel(String movie,String year,String duration,String director){
        this.movie=movie;
        this.year=year;
        this.duration=duration;
        this.director=director;
    }

    public String getMovie(){
        return movie;
    }
    public void setMovie(String movie){
        this.movie=movie;
    }
    public String getYear(){
        return year;
    }
    public void setYear(String year){
        this.year=year;
    }

    public String getDuration(){
        return duration;
    }
    public void setDuration(String duration){
        this.duration=duration;
    }
    public String getDirector(){
        return director;
    }
    public void setDirector(String director){
        this.director=director;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
