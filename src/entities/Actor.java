package entities;

import java.util.List;

public class Actor extends Person{

	private List<Film> movies;
	private List<Film> tvShows;
	
	public Actor(String name,String dateOfBirth,String placeOfBirth){
		super(name,dateOfBirth,placeOfBirth);
	}
	
	public List<Film> getMovies(){
		return this.movies;
	}
	
	public void setMovies(List<Film> movies){
		this.movies = movies;
	}
	
	public List<Film> getTvShows(){
		return this.tvShows;
	}
	
	public void setTvShows(List<Film> tvShows){
		this.tvShows = tvShows;
	}
	
	public void print(){
		System.out.println();
		if(!movies.isEmpty()){
			System.out.println("Movies "+ this.getName() + " appeared in:");
			for(Film movie : this.getMovies()){
				System.out.println("In " + movie.getName() +" played the character "+movie.getCharacter());
			}
		}
		System.out.println();
		if(!tvShows.isEmpty()){
			System.out.println("Tv Shows "+ this.getName() + " appeared in:");
			for(Film tvShow : this.getTvShows()){
				System.out.println("In " + tvShow.getName() +" played the character "+tvShow.getCharacter());
			}
		}
	}
}
