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
	
	public Values(){
		
	}
	
	public List<Value> getValues(){
		return this.values;
	}
	
	public void setValues(List<Value> values){
		this.values = values;
	}
	
	public void printValues(){
		for(int i=0;i<values.size();i++)
			System.out.println("Value: "+values.get(i).id);
	}
	
	@Override
    public String toJSONString() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }
}
