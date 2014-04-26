import java.util.*;
import org.jibble.pircbot.*;

public class TeamBot extends PircBot
{
	private class Team
	{
		private String name;
		private List<String> members;

		public Team()
		{
			this.members = new ArrayList<String>();
		}

		public Team( String name,  List<String> members)
		{
			this.name = name;
			this.members = members;
		}

		public String getName()
		{
			return this.name;
		}

		public void setName( String name)
		{
			this.name = name;
		}

		public List<String> getMembers()
		{
			return this.members;
		}

		public void setMembers( List<String> members)
		{
			this.members = members;
		}
	}

	private Map<String, Team> teams;

	public TeamBot()
	{
		this.teams = new HashMap<String, Team>();
		this.setName("TeamBot1234");
	}

	public void onMessage( String channel,  String sender,
                        String login,  String hostname,  String message) {
      if (message.equalsIgnoreCase("time")) {
            String time = new java.util.Date().toString();
				//Send to channel            
				sendMessage(channel, sender + ": The time is now " + time);
				//send PM				
				//sendMessage(sender, sender + ": The time is now " + time);
      }
		//create team <teamname>
		if (message.matches("create team \\w+"))
		{
			//TODO: Move this logic to?
			String[] splitMessage = message.split("\\s+");
			if(createTeam(sender, splitMessage[2]))
				sendMessage(channel, "team " + splitMessage[2] + " created!");
			else
				sendMessage(channel, "team " + splitMessage[2] + " already exists!");
		}
		//show teams
		if(message.matches("show teams"))
		{
			sendMessage(channel, showTeams());
		}
		//show team <teamname>
		if(message.matches("show team \\w+"))
		{
			//TODO: Move this logic to?
			 String[] splitMessage = message.split("\\s+");
			sendMessage(channel, showTeam(splitMessage[2]));
		}
		//join team <teamname>
		if(message.matches("join team \\w+"))
		{
			String[] splitMessage = message.split("\\s+");
			if(joinTeam(splitMessage[2], sender))
				sendMessage(channel, sender + " has joined team " + splitMessage[2] + "!");
			else
				sendMessage(channel, sender + " cannot join team " + splitMessage[2] + "!");
		}
    }

	private boolean createTeam( String memberName,  String teamName)
	{
		Team targetTeam = teams.get(teamName);		
		if(targetTeam != null)
			//team already exists; exit
			return false;

		//create member list
		 List<String> members = new ArrayList<String>();
		members.add(memberName);
		//create team and add member to it
		Team newTeam = new Team(teamName, members);
		teams.put(teamName, newTeam);

		return true;
	}

	private String showTeams()
	{
		String retval = "";
		//TODO: condense this duplicate code A
		for(String teamName : teams.keySet())
		{
			retval += teamName + ", ";
		}
		//remove final comma space, kind of a hack think of something better!
		retval = retval.substring(0, retval.length()-2);
		return retval;
	}

	private String showTeam(String teamName)
	{
		String retval = "";
		if(teams.get(teamName) == null)
		{
			retval = "Team does not exist!";
		}
		else
		{
			retval += "team " + teams.get(teamName).getName() + ": ";
			//TODO: condense this duplicate code A
			for(String memberName : teams.get(teamName).getMembers())
			{
				retval += memberName + ", ";
			}
			//remove final comma space, kind of a hack think of something better!
			retval = retval.substring(0, retval.length()-2);
		}
		return retval;
	}
	
	private boolean joinTeam(String teamName, String memberName)
	{
		if(teams.get(teamName) != null)
		{
			teams.get(teamName).getMembers().add(memberName);
			return true;
		}
		
		return false;
		
	}

/*
create team <teamname> DONE
join <teamname> DONE
leave team
show teams DONE
show team <teamname> DONE
show my team
delete team <teamname>
reset
*/
}
