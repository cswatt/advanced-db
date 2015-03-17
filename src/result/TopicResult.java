package result;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;

import entities.Actor;
import entities.Author;
import entities.BusinessPerson;
import entities.League;
import entities.Person;
import entities.Team;

public class TopicResult extends Result{
	static Gson gson = new GsonBuilder()
    .excludeFieldsWithoutExposeAnnotation()
    .create();
	
	private JSONObject topic;
	
	private Set<Type> types = new HashSet<Type>();
	
	private Person person;
	private BusinessPerson businessPerson;
	private Actor actor;
	private Author author;
	private League league;
	private Team team;
	
	public TopicResult(JSONObject topic){
		this.topic = topic;
	}
	
	public void parseTopic(){
		//find types first
		String res = JsonPath.read(topic,"$.property['/type/object/type']").toString();
		Values typeValues = gson.fromJson(res, Values.class);
		for(Value value : typeValues.getValues()){
			if(value.getId().equals("/people/person")){
				types.add(Type.PERSON);
				continue;
			}
			if(value.getId().equals("/book/author")){
				types.add(Type.AUTHOR);
				continue;
			}
			if(value.getId().equals("/organization/organization_founder")){
				types.add(Type.ORGANIZATION_FOUNDER);
				continue;
			}
			if(value.getId().equals("/business/board_member")){
				types.add(Type.BUSINESSPERSON);
				continue;
			}
			if(value.getId().equals("/tv/tv_actor")){
				types.add(Type.ACTOR);
				continue;
			}
			if(value.getId().equals("/film/actor")){
				types.add(Type.ACTOR);
				continue;
			}
			if(value.getId().equals("/sports/sports_league")){
				types.add(Type.LEAGUE);
				continue;
			}
			if(value.getId().equals("/sports/sports_team")){
				types.add(Type.TEAM);
				continue;
			}
			if(value.getId().equals("/sports/professional_sports_team")){
				types.add(Type.TEAM);
				continue;
			}
		}

		processTypes();
	}
	
	private void processTypes(){
		
		String name = JsonPath.read(topic,"$.property['/type/object/name'].values[0].text").toString();

		for(Type type : types){
			//This is a person
			if(type.equals(Type.PERSON)){
				String dateOfBirth = null;
				String placeOfBirth = null;
				String dateOfDeath = null;
				String placeOfDeath = null;
				String causeOfDeath = null;
				
				List<String> namesOfSimblings = new ArrayList<String>();
				List<String> namesOfSpouses = new ArrayList<String>();
				
				//extract notable properties
				dateOfBirth = JsonPath.read(topic,"$.property['/people/person/date_of_birth'].values[0].text").toString();
				placeOfBirth = JsonPath.read(topic,"$.property['/people/person/place_of_birth'].values[0].text").toString();
				if(topic.containsValue("/people/deceased_person/date_of_death"))
					dateOfDeath = JsonPath.read(topic,"$.property['people/deceased_person/date_of_death'].values[0].text").toString();
				if(topic.containsValue("/people/deceased_person/place_of_death"))
					dateOfDeath = JsonPath.read(topic,"$.property['/people/deceased_person/place_of_death'].values[0].text").toString();
				if(topic.containsValue("/people/deceased_person/cause_of_death"))
					causeOfDeath = JsonPath.read(topic,"$.property['/people/deceased_person/cause_of_death'].values[0].text").toString();
				
				//extract siblings
				String res2 = JsonPath.read(topic,"$.property['/people/person/sibling_s']").toString();
				Values siblings = gson.fromJson(res2, Values.class);
				for(Value sibling : siblings.getValues()){
					namesOfSimblings.add(JsonPath.read(sibling.getProperty(),"$./people/sibling_relationship/sibling.values[0].text").toString());
				}
				
				//extract spouses
				String res3 = JsonPath.read(topic,"$.property['/people/person/spouse_s']").toString();
				Values spouses = gson.fromJson(res3, Values.class);
				for(Value spouse : spouses.getValues()){
					namesOfSpouses.add(JsonPath.read(spouse.getProperty(),"$./people/marriage/spouse.values[0].text").toString());
				}
				
				//extract description
				String description = JsonPath.read(topic,"$.property['/common/topic/description'].values[0].value").toString();
				
				person = new Person(name,dateOfBirth,placeOfBirth);
				person.setDateOfDeath(dateOfDeath);
				person.setPlaceOfDeath(placeOfDeath);
				person.setCauseOfDeath(causeOfDeath);
				person.setSiblings(namesOfSimblings);
				person.setSpouses(namesOfSpouses);
				person.setDescription(description);
			}
		}
	}
	
	public Set<Type> getTypes(){
		return this.types;
	}
	
	public void setTypes(Set<Type> types){
		this.types = types;
	}
	
	public Person getPerson(){
		return this.person;
	}
	
	public void setPerson(Person person){
		this.person = person;
	}
	
	public BusinessPerson getBusinessPerson(){
		return this.businessPerson;
	}
	
	public void setBusinessPerson(BusinessPerson businessPerson){
		this.businessPerson = businessPerson;
	}
	
	public Actor getActor(){
		return this.actor;
	}
	
	public void setActor(Actor actor){
		this.actor = actor;
	}
	
	public Author getAuthor(){
		return this.author;
	}
	
	public void setAuthor(Author author){
		this.author = author;
	}
	
	public League getLeague(){
		return this.league;
	}
	
	public void setLeague(League league){
		this.league = league;
	}
	
	public Team getTeam(){
		return this.team;
	}
	
	public void setTeam(Team team){
		this.team = team;
	}
}
