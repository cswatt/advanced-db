package result;

public class Value {
	
	private String text;
	private String id;
	private String timestamp;
	private Value value;
	
	public Value(){
		
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getTimestamp(){
		return this.timestamp;
	}
	
	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}
	
	public Value getValue(){
		return this.value;
	}
	
	public void setValue(Value value){
		this.value = value;
	}
}
