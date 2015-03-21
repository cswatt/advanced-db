package result;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Values implements JSONable{
	private static final long serialVersionUID = -7934442049449016087L;
	
	@Expose
    @SerializedName(value = "values")
	protected List<Value> values = new ArrayList<Value>();
	
	@Expose
    @SerializedName(value = "count")
	protected int count = 0;
	
	public Values(){
		
	}
	
	public List<Value> getValues(){
		return this.values;
	}
	
	public void setValues(List<Value> values){
		this.values = values;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public void printValues(String field){
		for(int i=0;i<values.size();i++){
			if(field.equals("id"))
				System.out.println("Value: "+values.get(i).id);
			else if(field.equals("text"))
				System.out.println("Value: "+values.get(i).text);
			else if(field.equals("timestamp"))
				System.out.println("Value: "+values.get(i).timestamp);
		}
			
	}
	
	@Override
    public String toJSONString() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }
}
