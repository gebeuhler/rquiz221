import java.util.*;
import org.jibble.pircbot.*;

public class TeamBot extends PircBot
{
	private Map<String, List<String>> teams;

	public TeamBot()
	{
		this.teams = new HashMap<String, List<String>>();
		this.setName("TeamBot");
	}

	public void onMessage( String channel,  String sender,
                        String login,  String hostname,  String message) 
	{
      if (message.equalsIgnoreCase("time")) {
            String time = new java.util.Date().toString();
				sendMessage(channel, sender + ": The time is now " + time);
      }
		//create team <teamname>
		if (message.matches("create team \\w+"))
		{
			String[] splitMessage = message.split("\\s+");
			String teamName = splitMessage[2];

			if(createTeam(sender, splitMessage[2]))
				sendMessage(channel, sender + ": team " + teamName + " created!");
			else
				sendMessage(channel, sender + ": team " + teamName + " already exists!");
		}
		//show teams
		if(message.matches("show teams"))
		{
			if(teams.isEmpty())
				sendMessage(channel, sender + ": no teams exist!");
			else	
				sendMessage(channel, sender + ": " + showTeams());
		}
		//show team <teamname>
		if(message.matches("show team \\w+"))
		{
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
				sendMessage(channel, sender + ": team " + teamName + " does not exist! Try creating it.");
		}
		//leave team
		if(message.matches("leave team"))
		{
			if(leaveTeam(sender, true))
				sendMessage(channel, sender + ": you have left your team!");
			else
				sendMessage(channel, sender + ": you are not on a team!");
			//only input is sender
		}
		//show my team
		//Does not handle user being on multiple teams. Will return the first one found.
		if(message.matches("show my team"))
		{
			if(leaveTeam(sender, false))
				sendMessage(channel, sender + ": you are on team " + showMyTeam(sender) + "!");
			else
				sendMessage(channel, sender + ": you are not on a team!");
		}
		//delete team <teamname>
		if(message.matches("delete team \\w+"))
		{
			String[] splitMessage = message.split("\\s+");
			String teamName = splitMessage[2];
			
			if(doesTeamExist(teamName))
			{
				deleteTeam(teamName);
				sendMessage(channel, sender + ": team " + teamName + " has been deleted!");
			}
			else
				sendMessage(channel, sender + ": team " + teamName + " does not exist!");
		}
		//reset
		if(message.matches("reset"))
		{
			deleteAllTeams();
			sendMessage(channel, sender + ": all teams deleted!");
		}
		
    }

	private boolean createTeam(String memberName,  String teamName)
	{
		if(teams.get(teamName) != null && !teams.get(teamName).isEmpty())
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
		String memberList = "team " + teamName + " contains these users: ";
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
		if(teams.get(teamName) != null  && !teams.get(teamName).isEmpty())
		{
			return true;
		}
		return false;
	}
	
	private boolean leaveTeam(String memberName, boolean deleteFlag)
	{
		//first check if user belongs to team
		for(List<String> members: teams.values())
		{
			if(members.contains(memberName))
			{
				if(deleteFlag)
					members.remove(memberName);
				return true;
			}
		}

		return false;
	}

	private String showMyTeam(String memberName)
	{
		for(Map.Entry<String, List<String>> entry: teams.entrySet())
		{
			if(entry.getValue().contains(memberName))
			{
				return entry.getKey();
			}	
		}
		
		return "";
	}

	//assume team exists already
	private void deleteTeam(String teamName)
	{
		teams.remove(teamName);
	}

	private void deleteAllTeams()
	{
		teams = new HashMap<String, List<String>>();
	}

	//Using this to get around protected modifier of setName() so it can be called in main method. Not sure if I like this solution
	public void setBotName(String name)
	{
		setName(name);
	}
}
