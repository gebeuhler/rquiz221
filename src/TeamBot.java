import java.util.*;
import org.jibble.pircbot.*;

public class TeamBot extends PircBot
{
	private Map<String, List<String>> teams;

	public TeamBot()
	{
		this.teams = new HashMap<String, List<String>>();
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
				sendMessage(channel, sender + ": team " + splitMessage[2] + " created!");
			else
				sendMessage(channel, sender + ": team " + splitMessage[2] + " already exists!");
		}
		//show teams
		if(message.matches("show teams"))
		{
			if(teams.isEmpty())
				sendMessage(channel, sender + ": no teams exist!");
			else	
				sendMessage(channel, showTeams());
		}
		//show team <teamname>
		if(message.matches("show team \\w+"))
		{
			//TODO: Move this logic to?
			String[] splitMessage = message.split("\\s+");
			String teamName = splitMessage[2];
			
			if(doesTeamExist(teamName))
				sendMessage(channel, sender + ": " + getTeamMembers(teamName));
			else
				sendMessage(channel, sender + ": team " + teamName + " does not exist!");
		}
		//join team <teamname>
		if(message.matches("join team \\w+"))
		{
			String[] splitMessage = message.split("\\s+");
			String teamName = splitMessage[2];
			
			if(doesTeamExist(teamName))
			{
				if(joinTeam(teamName, sender))
					sendMessage(channel, sender + ": you have joined team " + teamName + "!");
				else
					sendMessage(channel, sender + ": you already belong to team " + teamName + "!");
			}
			else		
				sendMessage(channel, sender + ": team " + teamName + " does not exist!");
		}
		/*
		//leave team
		if(message.matches("leave team"))
		{
			if(leaveTeam(sender))
			//only input is sender
		}
		*/
    }

	private boolean createTeam(String memberName,  String teamName)
	{
		if(teams.get(teamName) != null)
		{
			//team already exists, exit
			return false;
		}
		
		//create new team
		List<String> members = new ArrayList<String>();
		members.add(memberName);
		teams.put(teamName, members);
		return true;
	}
	
	//assumes teams exist already
	private String showTeams()
	{
		String teamList = "";
		Set<String> teamNames = teams.keySet();
		int size = teamNames.size();
		
		for(String teamName : teamNames)
		{
			teamList += teamName;
			if(--size != 0)
				teamList += ", ";
		}
		
		return teamList;
	}

	//assume team exists already
	private String getTeamMembers(String teamName)
	{
		String memberList = "team " + teamName + ": ";
		List<String> members = teams.get(teamName);
		int size = members.size();
		
		for(String memberName : members)
		{
			memberList += memberName;
			if(--size != 0)
				memberList += ", ";
		}
		
		return memberList;
	}
	
	//assumes team already exists!!
	private boolean joinTeam(String teamName, String memberName)
	{
		List <String> members = teams.get(teamName);
	
		if(members.contains(memberName))
			return false;
			
		members.add(memberName);
		return true;
		
	}
	
	private boolean doesTeamExist(String teamName)
	{
		if(teams.get(teamName) != null)
		{
			return true;
		}
		return false;
	}
	/*
	private boolean leaveTeam(String memberName)
	{
		if(teams.get(teamName) != null)
		{
			teams.get(teamName).getMembers().remove(memberName)
			return true;
		}
		return false;
	}
	*/

//data structure currently: map<String teamName, Team>
//Team is: name, list of members
//simplify to: map<String teamName, List<String>>
//worth a try in new branch


/*
create team <teamname> DONE
join <teamname> 
leave team
show teams DONE
show team <teamname> 
show my team
delete team <teamname>
reset
*/
}
