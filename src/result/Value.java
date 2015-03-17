package result;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value implements JSONable{
	
	private static final long serialVersionUID = -7934442049449016087L;
	
	@Expose
    @SerializedName(value = "text")
	protected String text;
	@Expose
    @SerializedName(value = "id")
	protected String id;
	@Expose
    @SerializedName(value = "timestamp")
	protected String timestamp;
	@Expose
    @SerializedName(value = "value")
	private String value;
	
	@Expose
    @SerializedName(value = "property")
	private JSONObject property;
	
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
	
	public String getValue(){
		return this.value;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public JSONObject getProperty(){
		return this.property;
	}
	
	public void setProperty(JSONObject property){
		this.property = property;
	}
	
	@Override
    public String toJSONString() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }
}
