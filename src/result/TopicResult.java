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
import entities.Coach;
import entities.Film;
import entities.League;
import entities.Organization;
import entities.Person;
import entities.Player;
import entities.Spouse;
import entities.Team;

public class TopicResult extends Result{
	static Gson gson = new GsonBuilder()
    .excludeFieldsWithoutExposeAnnotation()
    .create();
	
	private JSONObject topic;
	
	private Set<Type> types = new HashSet<Type>();
	
	private Person person = null;
	private BusinessPerson businessPerson = null;
	private Actor actor = null;
	private Author author = null;
	private League league = null;
	private Team team = null;
	
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
		if(types.contains(Type.PERSON)){
			processPersonType();
			if(types.contains(Type.BUSINESSPERSON)){
				processBusinessPersonType();
			}
			if(types.contains(Type.AUTHOR)){
				processAuthorType();
			}
			if(types.contains(Type.ACTOR)){
				processActorType();
			}
		}
		else if(types.contains(Type.LEAGUE)){
			processLeagueType();
		}
		else if(types.contains(Type.TEAM)){
			processTeamType();
		}
	}
	
	private void processPersonType(){
		String name = JsonPath.read(topic,"$.property['/type/object/name'].values[0].text").toString();
		
		String dateOfBirth = null;
		String placeOfBirth = null;
		String dateOfDeath = null;
		String placeOfDeath = null;
		String causeOfDeath = null;
		String description = null;
		
		List<String> siblings = new ArrayList<String>();
		List<Spouse> spouses = new ArrayList<Spouse>();
		
		//extract notable properties 
		if(topic.toString().contains("\\/people\\/person\\/date_of_birth"))
			if(JsonPath.read(topic,"$.property['/people/person/date_of_birth']").toString().contains("valuetype"))
				dateOfBirth = JsonPath.read(topic,"$.property['/people/person/date_of_birth'].values[0].text").toString();
		if(topic.toString().contains("\\/people\\/person\\/place_of_birth"))
			if(JsonPath.read(topic,"$.property['/people/person/place_of_birth']").toString().contains("valuetype"))
				placeOfBirth = JsonPath.read(topic,"$.property['/people/person/place_of_birth'].values[0].text").toString();
		if(topic.toString().contains("\\/people\\/deceased_person\\/date_of_death"))
			if(JsonPath.read(topic,"$.property['/people/deceased_person/date_of_death']").toString().contains("valuetype"))
				dateOfDeath = JsonPath.read(topic,"$.property['/people/deceased_person/date_of_death'].values[0].text").toString();
		if(topic.toString().contains("\\/people\\/deceased_person\\/place_of_death"))
			if(JsonPath.read(topic,"$.property['/people/deceased_person/place_of_death']").toString().contains("valuetype"))
				dateOfDeath = JsonPath.read(topic,"$.property['/people/deceased_person/place_of_death'].values[0].text").toString();
		if(topic.toString().contains("\\/people\\/deceased_person\\/cause_of_death"))
			if(JsonPath.read(topic,"$.property['/people/deceased_person/cause_of_death']").toString().contains("valuetype"))
				causeOfDeath = JsonPath.read(topic,"$.property['/people/deceased_person/cause_of_death'].values[0].text").toString();
				
		
		//extract siblings
		if(topic.toString().contains("\\/people\\/person\\/sibling_s")){
			if(JsonPath.read(topic,"$.property['/people/person/sibling_s']").toString().contains("valuetype")){
				String res2 = JsonPath.read(topic,"$.property['/people/person/sibling_s']").toString();
				Values siblings_names = gson.fromJson(res2, Values.class);
				for(Value sibling : siblings_names.getValues()){
					siblings.add(JsonPath.read(sibling.getProperty(),"$./people/sibling_relationship/sibling.values[0].text").toString());
				}
			}
		}

		//extract spouses
		if(topic.toString().contains("\\/people\\/person\\/spouse_s")){
			if(JsonPath.read(topic,"$.property['/people/person/spouse_s']").toString().contains("valuetype")){
				String res3 = JsonPath.read(topic,"$.property['/people/person/spouse_s']").toString();
				Values spouses_objects = gson.fromJson(res3, Values.class);
				for(Value spouse_object : spouses_objects.getValues()){
					if(JsonPath.read(spouse_object.getProperty(),"$./people/marriage/spouse").toString().contains("valuetype")){
						Spouse spouse  = new Spouse(JsonPath.read(spouse_object.getProperty(),"$./people/marriage/spouse.values[0].text").toString());
						if(spouse_object.getProperty().toString().contains("\\/people\\/marriage\\/from"))
							if(JsonPath.read(spouse_object.getProperty(),"./people/marriage/from").toString().contains("valuetype"))
								spouse.setFrom(JsonPath.read(spouse_object.getProperty(),"./people/marriage/from.values[0].text").toString());
						if(spouse_object.getProperty().toString().contains("\\/people\\/marriage\\/to"))
							if(JsonPath.read(spouse_object.getProperty(),"./people/marriage/to").toString().contains("valuetype"))
								spouse.setTo(JsonPath.read(spouse_object.getProperty(),"./people/marriage/to.values[0].text").toString());
						if(spouse_object.getProperty().toString().contains("\\/people\\/marriage\\/location_of_ceremony"))
							if(JsonPath.read(spouse_object.getProperty(),"$./people/marriage/location_of_ceremony").toString().contains("valuetype"))
								spouse.setMarriageLocation((JsonPath.read(spouse_object.getProperty(),"./people/marriage/location_of_ceremony.values[0].text").toString()));
						
						if(spouse.getTo() == null)
							spouse.setTo("now");
						
						spouses.add(spouse);
					}
				}
			}
		}
		
		//extract description
		if(topic.toString().contains("\\/common\\/topic\\/description"))
			if(JsonPath.read(topic,"$.property['/common/topic/description']").toString().contains("valuetype"))
				description = JsonPath.read(topic,"$.property['/common/topic/description'].values[0].value").toString();
		
		person = new Person(name,dateOfBirth,placeOfBirth);
		person.setDateOfDeath(dateOfDeath);
		person.setPlaceOfDeath(placeOfDeath);
		person.setCauseOfDeath(causeOfDeath);
		person.setSiblings(siblings);
		person.setSpouses(spouses);
		person.setDescription(description);
	}
	
	private void processBusinessPersonType(){
		if(person == null)
			return;
		
		List<Organization> organizations = new ArrayList<Organization>();
		
		businessPerson = new BusinessPerson(person.getName(),person.getDateOfBirth(),person.getPlaceOfBirth());
		person.setIsBusinessPerson(true);
		
		//extract organizations founded by this person
		if(topic.toString().contains("\\/organization\\/organization_founder\\/organizations_founded")){
			if(JsonPath.read(topic,"$.property['/organization/organization_founder/organizations_founded']").toString().contains("valuetype")){
				String organizations_founded = JsonPath.read(topic,"$.property['/organization/organization_founder/organizations_founded']").toString();
				Values orgs = gson.fromJson(organizations_founded, Values.class);
				for(Value org : orgs.getValues()){
					Organization o = new Organization(org.text);
					o.setFoundedBy(true);
					organizations.add(o);
				}
			}
		}
		//extract organizations that this person is leading
		if(topic.toString().contains("\\/business\\/board_member\\/leader_of")){
			if(JsonPath.read(topic,"$.property['/business/board_member/leader_of']").toString().contains("valuetype")){
				String organizations_leader_of = JsonPath.read(topic,"$.property['/business/board_member/leader_of']").toString();
				Values orgs = gson.fromJson(organizations_leader_of, Values.class);
				for(Value org : orgs.getValues()){
					String org_name = null;
					String org_title = null;
					String org_role = null;
					String org_from = null;
					String org_to = null;
					
					//no check because there has to be a name!
					org_name = JsonPath.read(org.getProperty(),"$./organization/leadership/organization.values[0].text").toString();
					
					if(org.getProperty().toString().contains("\\/organization\\/leadership\\/title"))
						if(JsonPath.read(org.getProperty(),"$./organization/leadership/title").toString().contains("valuetype"))
							org_title = JsonPath.read(org.getProperty(),"$./organization/leadership/title.values[0].text").toString();
					if(org.getProperty().toString().contains("\\/organization\\/leadership\\/role"))
						if(JsonPath.read(org.getProperty(),"$./organization/leadership/role").toString().contains("valuetype"))
							org_role = JsonPath.read(org.getProperty(),"$./organization/leadership/role.values[0].text").toString();
					if(org.getProperty().toString().contains("\\/organization\\/leadership\\/from"))
						if(JsonPath.read(org.getProperty(),"$./organization/leadership/from").toString().contains("valuetype"))
							org_from = JsonPath.read(org.getProperty(),"$./organization/leadership/from.values[0].text").toString();
					if(org.getProperty().toString().contains("\\/organization\\/leadership\\/to"))
						if(JsonPath.read(org.getProperty(),"$./organization/leadership/to").toString().contains("valuetype"))
							org_to = JsonPath.read(org.getProperty(),"$./organization/leadership/to.values[0].text").toString();
					
					if(org_to == null)
						org_to = "now";
					
					//check if organization already exists in the list of organizations
					Organization alreadyIn = existsInOrganizations(organizations,org_name);
					if(alreadyIn != null){
						organizations.remove(alreadyIn);
						alreadyIn.setLeaderTitle(org_title);
						alreadyIn.setLeaderRole(org_role);
						alreadyIn.setLeaderTo(org_to);
						alreadyIn.setLeaderFrom(org_from);
						alreadyIn.setIsLeaderOf(true);
						organizations.add(alreadyIn);
					}
					else{
						Organization o = new Organization(org_name);
						o.setLeaderTitle(org_title);
						o.setLeaderRole(org_role);
						o.setLeaderTo(org_to);
						o.setLeaderFrom(org_from);
						o.setIsLeaderOf(true);
						organizations.add(o);
					}
				}
			}
		}
		//extract organizations this person is a board member of
		if(topic.toString().contains("\\/business\\/board_member\\/organization_board_memberships")){
			if(JsonPath.read(topic,"$.property['/business/board_member/organization_board_memberships']").toString().contains("valuetype")){
				String organizations_member_of = JsonPath.read(topic,"$.property['/business/board_member/organization_board_memberships']").toString();
				Values orgs = gson.fromJson(organizations_member_of, Values.class);
				for(Value org : orgs.getValues()){
					String org_name = null;
					String org_title = null;
					String org_role = null;
					String org_from = null;
					String org_to = null;
					
					//no check because there has to be a name!
					org_name = JsonPath.read(org.getProperty(),"$./organization/organization_board_membership/organization.values[0].text").toString();
					
					if(org.getProperty().toString().contains("\\/organization\\/organization_board_membership\\/title"))
						if(JsonPath.read(org.getProperty(),"$./organization/organization_board_membership/title").toString().contains("valuetype"))
							org_title = JsonPath.read(org.getProperty(),"$./organization/organization_board_membership/title.values[0].text").toString();
					if(org.getProperty().toString().contains("\\/organization\\/organization_board_membership\\/role"))
						if(JsonPath.read(org.getProperty(),"$./organization/organization_board_membership/role").toString().contains("valuetype"))
							org_role = JsonPath.read(org.getProperty(),"$./organization/organization_board_membership/role.values[0].text").toString();
					if(org.getProperty().toString().contains("\\/organization\\/organization_board_membership\\/from"))
						if(JsonPath.read(org.getProperty(),"$./organization/organization_board_membership/from").toString().contains("valuetype"))
							org_from = JsonPath.read(org.getProperty(),"$./organization/organization_board_membership/from.values[0].text").toString();
					if(org.getProperty().toString().contains("\\/organization\\/organization_board_membership\\/to"))
						if(JsonPath.read(org.getProperty(),"$./organization/organization_board_membership/to").toString().contains("valuetype"))
							org_to = JsonPath.read(org.getProperty(),"$./organization/organization_board_membership/to.values[0].text").toString();
					
					if(org_to == null)
						org_to = "now";
					
					//check if organization already exists in the list of organizations
					Organization alreadyIn = existsInOrganizations(organizations,org_name);
					if(alreadyIn != null){
						organizations.remove(alreadyIn);
						alreadyIn.setBoardMemberTitle(org_title);
						alreadyIn.setBoardMemberRole(org_role);
						alreadyIn.setBoardMemberTo(org_to);
						alreadyIn.setBoardMemberFrom(org_from);
						alreadyIn.setIsMemberOf(true);
						organizations.add(alreadyIn);
					}
					else{
						Organization o = new Organization(org_name);
						o.setBoardMemberTitle(org_title);
						o.setBoardMemberRole(org_role);
						o.setBoardMemberTo(org_to);
						o.setBoardMemberFrom(org_from);
						o.setIsMemberOf(true);
						organizations.add(o);
					}
				}
			}
		}
		
		businessPerson.setOrganizations(organizations);
	}
	
	private void processAuthorType(){
		if(person == null)
			return;
		
		author = new Author(person.getName(),person.getDateOfBirth(),person.getPlaceOfBirth());
		person.setIsAuthor(true);
		
		//extract books written by this person
		if(topic.toString().contains("\\/book\\/author\\/works_written")){
			if(JsonPath.read(topic,"$.property['/book/author/works_written']").toString().contains("valuetype")){
				List<String> bookTitles = new ArrayList<String>();
				String books_written = JsonPath.read(topic,"$.property['/book/author/works_written']").toString();
				Values books = gson.fromJson(books_written, Values.class);
				for(Value book : books.getValues()){
					bookTitles.add(book.getText());
				}
				author.setBooks(bookTitles);
			}
		}
		
		//extract books written about this person
		if(topic.toString().contains("\\/book\\/book_subject\\/works")){
			if(JsonPath.read(topic,"$.property['/book/book_subject/works']").toString().contains("valuetype")){
				List<String> bookTitles = new ArrayList<String>();
				String books_about = JsonPath.read(topic,"$.property['/book/book_subject/works']").toString();
				Values books = gson.fromJson(books_about, Values.class);
				for(Value book : books.getValues()){
					bookTitles.add(book.getText());
				}
				author.setBooksAboutTheAuthor(bookTitles);
			}
		}
		
		//extract people whom this person influenced 
		if(topic.toString().contains("\\/influence\\/influence_node\\/influenced")){
			if(JsonPath.read(topic,"$.property['/influence/influence_node/influenced']").toString().contains("valuetype")){
				List<String> influenced = new ArrayList<String>();
				String people_influenced = JsonPath.read(topic,"$.property['/influence/influence_node/influenced']").toString();
				Values people = gson.fromJson(people_influenced, Values.class);
				for(Value p : people.getValues()){
					influenced.add(p.getText());
				}
				author.setInfluenced(influenced);
			}
		}
		
		//extract people this person was influenced by
		if(topic.toString().contains("\\/influence\\/influence_node\\/influenced_by")){
			if(JsonPath.read(topic,"$.property['/influence/influence_node/influenced_by']").toString().contains("valuetype")){
				List<String> influencedBy = new ArrayList<String>();
				String people_influencedBy = JsonPath.read(topic,"$.property['/influence/influence_node/influenced_by']").toString();
				Values people = gson.fromJson(people_influencedBy, Values.class);
				for(Value p : people.getValues()){
					influencedBy.add(p.getText());
				}
				author.setInfluencedBy(influencedBy);
			}
		}
	}
	
	private void processActorType(){
		if(person == null)
			return;
		
		actor = new Actor(person.getName(),person.getDateOfBirth(),person.getPlaceOfBirth());
		person.setIsActor(true);
		
		//extract films this person has appeared in
		if(topic.toString().contains("\\/film\\/actor\\/film")){
			if(JsonPath.read(topic,"$.property['/film/actor/film']").toString().contains("valuetype")){
				List<Film> actorsfilms = new ArrayList<Film>();
				
				String filmName = null;
				String character = null;
				
				String filmsmade = JsonPath.read(topic,"$.property['/film/actor/film']").toString();
				Values films = gson.fromJson(filmsmade, Values.class);
				for(Value f : films.getValues()){
					if(f.getProperty().toString().contains("\\/film\\/performance\\/character"))
						if(JsonPath.read(f.getProperty(),"$./film/performance/character").toString().contains("valuetype"))
							character = JsonPath.read(f.getProperty(),"$./film/performance/character.values[0].text").toString();
					
					if(f.getProperty().toString().contains("\\/film\\/performance\\/film"))
						if(JsonPath.read(f.getProperty(),"$./film/performance/film").toString().contains("valuetype"))
							filmName = JsonPath.read(f.getProperty(),"$./film/performance/film.values[0].text").toString();
					
					actorsfilms.add(new Film(filmName, character));
				}
				
				actor.setMovies(actorsfilms);
			}
		}
		//extract tv-shows this person has appeared in
		if(topic.toString().contains("\\/tv\\/tv_actor\\/starring_roles")){
			if(JsonPath.read(topic,"$.property['/tv/tv_actor/starring_roles']").toString().contains("valuetype")){
				List<Film> actorsfilms = new ArrayList<Film>();
				
				String filmName = null;
				String character = null;
				
				String filmsmade = JsonPath.read(topic,"$.property['/tv/tv_actor/starring_roles']").toString();
				Values films = gson.fromJson(filmsmade, Values.class);
				for(Value f : films.getValues()){
					if(f.getProperty().toString().contains("\\/tv\\/regular_tv_appearance\\/character"))
						if(JsonPath.read(f.getProperty(),"$./tv/regular_tv_appearance/character").toString().contains("valuetype"))
							character = JsonPath.read(f.getProperty(),"$./tv/regular_tv_appearance/character.values[0].text").toString();
					
					if(f.getProperty().toString().contains("\\/tv\\/regular_tv_appearance\\/series"))
						if(JsonPath.read(f.getProperty(),"$./tv/regular_tv_appearance/series").toString().contains("valuetype"))
							filmName = JsonPath.read(f.getProperty(),"$./tv/regular_tv_appearance/series.values[0].text").toString();
					
					actorsfilms.add(new Film(filmName, character));
				}
				
				actor.setTvShows(actorsfilms);
			}
		}
	}
	
	private void processLeagueType(){
		//no check because there has to be a name
		String name = JsonPath.read(topic,"$.property['/type/object/name'].values[0].text").toString();
		
		String sport = null;
		String championship = null;
		String slogan = null;
		String website = null;
		String description = null;
		
		//extract notable properties
		if(topic.toString().contains("\\/sports\\/sports_league\\/sport"))
			if(JsonPath.read(topic,"$.property['/sports/sports_league/sport']").toString().contains("valuetype"))
				sport = JsonPath.read(topic,"$.property['/sports/sports_league/sport'].values[0].text").toString();
		if(topic.toString().contains("\\/sports\\/sports_league\\/championship"))
			if(JsonPath.read(topic,"$.property['/sports/sports_league/championship']").toString().contains("valuetype"))
				championship = JsonPath.read(topic,"$.property['/sports/sports_league/championship'].values[0].text").toString();
		if(topic.toString().contains("\\/organization\\/organization\\/slogan"))
			if(JsonPath.read(topic,"$.property['/organization/organization/slogan']").toString().contains("valuetype"))
				slogan = JsonPath.read(topic,"$.property['/organization/organization/slogan'].values[0].text").toString();
		
		if(topic.toString().contains("\\/common\\/topic\\/official_website"))
			if(JsonPath.read(topic,"$.property['/common/topic/official_website']").toString().contains("valuetype"))
				website = JsonPath.read(topic,"$.property['/common/topic/official_website'].values[0].text").toString();
		
		if(topic.toString().contains("\\/common\\/topic\\/description"))
			if(JsonPath.read(topic,"$.property['/common/topic/description']").toString().contains("valuetype"))
				description = JsonPath.read(topic,"$.property['/common/topic/description'].values[0].value").toString();
		
		
		List<Team> teams = new ArrayList<Team>();
		
		//extract the teams that participate in the league
		if(topic.toString().contains("\\/sports\\/sports_league\\/teams")){
			if(JsonPath.read(topic,"$.property['/sports/sports_league/teams']").toString().contains("valuetype")){
				String leagueteams = JsonPath.read(topic,"$.property['/sports/sports_league/teams']").toString();
				Values lteams = gson.fromJson(leagueteams, Values.class);
				for(Value lt : lteams.getValues()){
					if(lt.getProperty().toString().contains("\\/sports\\/sports_league_participation\\/team")){
						if(JsonPath.read(lt.getProperty(),"$./sports/sports_league_participation/team").toString().contains("valuetype")){
							String team_name = JsonPath.read(lt.getProperty(),"$./sports/sports_league_participation/team.values[0].text").toString();
							teams.add(new Team(team_name,sport));
						}
					}
						
				}
			}
		}
		
		this.league = new League(name,sport,championship);
		this.league.setSlogan(slogan);
		this.league.setWebsite(website);
		this.league.setDescription(description);
		this.league.setTeams(teams);
		
	}
	
	private void processTeamType(){
		//no check because there has to be a name 
		String name = JsonPath.read(topic,"$.property['/type/object/name'].values[0].text").toString();
		
		String sport = null;
		String description = null;
		String arena = null;
		String foundedIn = null;
		
		List<String> locations = new ArrayList<String>();
		List<String> championships = new ArrayList<String>();
		
		List<Coach> coaches = new ArrayList<Coach>();
		List<League> leagues = new ArrayList<League>();
		List<Player> players = new ArrayList<Player>();
		
		//extract notable properties
		if(topic.toString().contains("\\/sports\\/sports_team\\/sport"))
			if(JsonPath.read(topic,"$.property['/sports/sports_team/sport']").toString().contains("valuetype"))
				sport = JsonPath.read(topic,"$.property['/sports/sports_team/sport'].values[0].text").toString();
		if(topic.toString().contains("\\/common\\/topic\\/description"))
			if(JsonPath.read(topic,"$.property['/common/topic/description']").toString().contains("valuetype"))
				description = JsonPath.read(topic,"$.property['/common/topic/description'].values[0].value").toString();
		if(topic.toString().contains("\\/sports\\/sports_team\\/arena_stadium"))
			if(JsonPath.read(topic,"$.property['/sports/sports_team/arena_stadium']").toString().contains("valuetype"))
				arena = JsonPath.read(topic,"$.property['/sports/sports_team/arena_stadium'].values[0].text").toString();
		if(topic.toString().contains("\\/sports\\/sports_team\\/founded"))
			if(JsonPath.read(topic,"$.property['/sports/sports_team/founded']").toString().contains("valuetype"))
				foundedIn = JsonPath.read(topic,"$.property['/sports/sports_team/founded'].values[0].text").toString();
		
		//extract team's locations
		if(topic.toString().contains("\\/sports\\/sports_team\\/location")){
			if(JsonPath.read(topic,"$.property['/sports/sports_team/location']").toString().contains("valuetype")){
				String locs = JsonPath.read(topic,"$.property['/sports/sports_team/location']").toString();
				Values team_locations = gson.fromJson(locs, Values.class);
				for(Value loc : team_locations.getValues()){
					locations.add(loc.getText());
				}
			}
		}
		//extract team's championship
		if(topic.toString().contains("\\/sports\\/sports_team\\/championships")){
			if(JsonPath.read(topic,"$.property['/sports/sports_team/championships']").toString().contains("valuetype")){
				String champs = JsonPath.read(topic,"$.property['/sports/sports_team/championships']").toString();
				Values team_championships = gson.fromJson(champs, Values.class);
				for(Value champ : team_championships.getValues()){
					championships.add(champ.getText());
				}
			}
		}
		
		//extract team's coaches
		if(topic.toString().contains("\\/sports\\/sports_team\\/coaches")){
			if(JsonPath.read(topic,"$.property['/sports/sports_team/coaches']").toString().contains("valuetype")){
				String team_coaches = JsonPath.read(topic,"$.property['/sports/sports_team/coaches']").toString();
				Values tcoaches = gson.fromJson(team_coaches, Values.class);
				
				for(Value tcoach : tcoaches.getValues()){
					String coach_name = null;
					String coach_from = null;
					String coach_to = null;
					
					List<String> coach_positions = new ArrayList<String>();
				
					if(tcoach.getProperty().toString().contains("\\/sports\\/sports_team_coach_tenure\\/coach"))
						if(JsonPath.read(tcoach.getProperty(),"$./sports/sports_team_coach_tenure/coach").toString().contains("valuetype"))
							coach_name = JsonPath.read(tcoach.getProperty(),"$./sports/sports_team_coach_tenure/coach.values[0].text").toString();
					if(tcoach.getProperty().toString().contains("\\/sports\\/sports_team_coach_tenure\\/from"))
						if(JsonPath.read(tcoach.getProperty(),"$./sports/sports_team_coach_tenure/from").toString().contains("valuetype"))
							coach_from = JsonPath.read(tcoach.getProperty(),"$./sports/sports_team_coach_tenure/from.values[0].text").toString();
					if(tcoach.getProperty().toString().contains("\\/sports\\/sports_team_coach_tenure\\/position")){
						if(JsonPath.read(tcoach.getProperty(),"$./sports/sports_team_coach_tenure/position").toString().contains("valuetype")){
							String cpos = JsonPath.read(tcoach.getProperty(),"$./sports/sports_team_coach_tenure/position").toString();
							int count = Integer.parseInt(cpos.substring(cpos.indexOf("count=") + ("count=").length()).substring(0, 1));
							for(int i=0;i<count;i++){
								coach_positions.add(JsonPath.read(tcoach.getProperty(),"$./sports/sports_team_coach_tenure/position.values["+i+"].text").toString());
							}
						}
					}
					if(tcoach.getProperty().toString().contains("\\/sports\\/sports_team_coach_tenure\\/to")){
						if(JsonPath.read(tcoach.getProperty(),"$./sports/sports_team_coach_tenure/to").toString().contains("valuetype"))
							coach_to = JsonPath.read(tcoach.getProperty(),"$./sports/sports_team_coach_tenure/to.values[0].text").toString();
					}
					
					if(coach_to == null)
						coach_to = "now";
					
					Coach coach = new Coach(coach_name);
					coach.setFrom(coach_from);
					coach.setTo(coach_to);
					coach.setPositions(coach_positions);
					coaches.add(coach);
				}
			}
		}
		//extract the leagues this team has participated in
		if(topic.toString().contains("\\/sports\\/sports_team\\/league")){
			if(JsonPath.read(topic,"$.property['/sports/sports_team/league']").toString().contains("valuetype")){
				String team_leagues = JsonPath.read(topic,"$.property['/sports/sports_team/league']").toString();
				Values tleagues = gson.fromJson(team_leagues, Values.class);
				for(Value tleague : tleagues.getValues()){
					String league_name = name;
					
					if(tleague.getProperty().toString().contains("\\/sports\\/sports_league_participation\\/league")){
						if(JsonPath.read(tleague.getProperty(),"$./sports/sports_league_participation/league").toString().contains("valuetype")){
							league_name = JsonPath.read(tleague.getProperty(),"$./sports/sports_league_participation/league.values[0].text").toString();
							leagues.add(new League(league_name));
						}
					}
				}
			}
		}
		//extract team's players
		if(topic.toString().contains("\\/sports\\/sports_team\\/roster")){
			if(JsonPath.read(topic,"$.property['/sports/sports_team/roster']").toString().contains("valuetype")){
				String team_players = JsonPath.read(topic,"$.property['/sports/sports_team/roster']").toString();
				Values tplayers = gson.fromJson(team_players, Values.class);
				for(Value tplayer : tplayers.getValues()){
					String player_name = null;
					String player_num = null;
					String player_from = null;
					String player_to = null;
					
					List<String> player_positions = new ArrayList<String>();
					
					if(tplayer.getProperty().toString().contains("\\/sports\\/sports_team_roster\\/player"))
						if(JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/player").toString().contains("valuetype"))
							player_name = JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/player.values[0].text").toString();
					if(tplayer.getProperty().toString().contains("\\/sports\\/sports_team_roster\\/from"))
						if(JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/from").toString().contains("valuetype"))
							player_from = JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/from.values[0].text").toString();
					if(tplayer.getProperty().toString().contains("\\/sports\\/sports_team_roster\\/position")){
						if(JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/position").toString().contains("valuetype")){
							String ppos = JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/position").toString();
							int count = Integer.parseInt(ppos.substring(ppos.indexOf("count=") + ("count=").length()).substring(0, 1));
							for(int i=0;i<count;i++){
								player_positions.add(JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/position.values["+i+"].text").toString());
							}
						}
					}
					if(tplayer.getProperty().toString().contains("\\/sports\\/sports_team_roster\\/to"))
						if(JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/to").toString().contains("valuetype"))
							player_to = JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/to.values[0].text").toString();
					if(tplayer.getProperty().toString().contains("\\/sports\\/sports_team_roster\\/number"))
						if(JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/number").toString().contains("valuetype"))
							player_num = JsonPath.read(tplayer.getProperty(),"$./sports/sports_team_roster/number.values[0].text").toString();
					
					if(player_to == null)
						player_to = "now";
					
					Player player = new Player(player_name);
					player.setFrom(player_from);
					player.setNumber(player_num);
					player.setTo(player_to);
					player.setPositions(player_positions);
					
					players.add(player);
				}
			}
		}
		
		this.team = new Team(name,sport);
		this.team.setDescription(description);
		this.team.setArena(arena);
		this.team.setFoundedIn(foundedIn);
		this.team.setLocations(locations);
		this.team.setChampionships(championships);
		this.team.setCoaches(coaches);
		this.team.setLeagues(leagues);
		this.team.setPlayersRoster(players);
	}
	
	private Organization existsInOrganizations(List<Organization> organizations, String org_name){
		if(organizations == null || organizations.isEmpty())
			return null;
		
		for(Organization o : organizations){
			if(o.getName().equals(org_name))
				return o;
		}
		
		return null;
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
