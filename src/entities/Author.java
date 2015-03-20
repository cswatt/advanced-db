package entities;

import java.util.List;

public class Author extends Person{

	private List<String> books;
	private List<String> influenced;
	private List<String> influencedBy;
	private List<String> booksAboutTheAuthor;
	
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
	
	public List<String> getBookAboutTheAuthor(){
		return this.booksAboutTheAuthor;
	}
	
	public void setBookAboutTheAuthor(List<String> booksAboutTheAuthor){
		this.booksAboutTheAuthor = booksAboutTheAuthor;
	}
	
	public void print(){
		if(books != null && books.size() > 0){
			System.out.println("Books published by "+this.getName()+":");
			for(String book : books)
				System.out.println(book);
		}
		if(booksAboutTheAuthor != null && booksAboutTheAuthor.size() > 0){
			System.out.println("Books about "+this.getName() + ":");
			for(String book : booksAboutTheAuthor)
				System.out.println(book);
		}
		if(influenced != null && influenced.size() > 0){
			System.out.println("People "+this.getName() + " influenced:");
			for(String p : influenced)
				System.out.println(p);
		}
		if(influencedBy != null && influencedBy.size() > 0){
			System.out.println("People "+this.getName() + " is influenced by:");
			for(String p : influencedBy)
				System.out.println(p);
		}
	}
}
