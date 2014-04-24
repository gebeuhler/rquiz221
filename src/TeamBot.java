import java.util.*;

public class TeamBot
{
	private class Team
	{
		private String name;
		private List<String> members;

		public Team()
		{
			this.members = new ArrayList<String>();
		}

		public Team(String name, List<String> members)
		{
			this.name = name;
			this.members = members;
		}

		public String getName()
		{
			return this.name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public List<String> getMembers()
		{
			return this.members;
		}

		public void setMembers(List<String> members)
		{
			this.members = members;
		}
	}

	private Map<String, Team> teams;

	public TeamBot()
	{
		this.teams = new HashMap<String, Team>();
	}

	public void parse(String[] args)
	{
		if(args.length == 0)
		{
			System.out.println("Incorrect usage doofus");
		}

		if(args[0] == "create" && args[1] == "team" && args[2] != null)
		{
			createTeam(args[2]);
		}
	}

	private boolean createTeam(String teamName)
	{
		Team targetTeam = teams.get(teamName);		
		if(targetTeam != null)
			//team already exists; exit
			return false;
		
		//create list of members for team
		List<String> members = targetTeam.getMembers();
		if(members == null)
		{
			targetTeam.setMembers(new ArrayList<String>());
			members = targetTeam.getMembers();
		}

		members.add("person1");
		//create team and add member to it
		Team newTeam = new Team(teamName, members);
		teams.put("team1", newTeam);

		return true;
	}

/*
create team <teamname>
join <teamname>
leave team
show teams
show team <teamname>
show my team
delete team <teamname>
reset
*/
}
