import java.util.*;

public class TeamBot
{
	private class Team
	{
		private String name;
		private List<String> members;

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

	private Set<String, Team> teams;

	public TeamBot()
	{
		this.teams = new HashSet<String, Team>();
	}

	public void parse(String[] args)
	{
		if(args.length == 0)
		{
			System.out.println("Incorrect usage doofus");
		}

		if(args[0] == "create" && args[1] == "team" && args[2] != null)
		{
			//create team and add person to it
			Team team = new Team();
			teams.put("test", new Team
		}
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
