package entities;

import java.util.List;

public class Actor extends Person{

	private List<Film> films;
	
	public Actor(String name,String dateOfBirth,String placeOfBirth){
		super(name,dateOfBirth,placeOfBirth);
	}
	
	public List<Film> getFilms(){
		return this.films;
	}
	
	public void setFilms(List<Film> films){
		this.films = films;
	}
}
