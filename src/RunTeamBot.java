import org.jibble.pircbot.*;

public class RunTeamBot
{
	public static void main(String[] args) throws Exception
	{
      // Now start our bot up.
      TeamBot teamBot = new TeamBot();
        
      // Enable debugging output.
      teamBot.setVerbose(true);
       
      // Connect to the IRC server.
      teamBot.connect("irc.freenode.net");

      // Join the #pircbot channel.
      teamBot.joinChannel("#george-test-123");
		
		//teamBot.parse(args);
	}
}
