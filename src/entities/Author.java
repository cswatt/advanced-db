package entities;

import java.util.List;

public class Author extends Person{

	private List<String> books;
	private List<String> influenced;
	private List<String> influencedBy;
	
	private String bookAboutTheAuthor;
	
	public Author(String name,String dateOfBirth,String placeOfBirth){
		super(name,dateOfBirth,placeOfBirth);
	}
	
	public List<String> getBooks(){
		return this.books;
	}
	
	public void setBooks(List<String> books){
		this.books = books;
	}
	
	public List<String> getInfluenced(){
		return this.influenced;
	}
	
	public void setInfluenced(List<String> influenced){
		this.influenced = influenced;
	}
	
	public List<String> getInfluencedBy(){
		return this.influencedBy;
	}
	
	public void setInfluencedBy(List<String> influencedBy){
		this.influencedBy = influencedBy;
	}
	
	public String getBookAboutTheAuthor(){
		return this.bookAboutTheAuthor;
	}
	
	public void setBookAboutTheAuthor(String bookAboutTheAuthor){
		this.bookAboutTheAuthor = bookAboutTheAuthor;
	}
}
