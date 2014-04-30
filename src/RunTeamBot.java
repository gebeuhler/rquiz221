import org.jibble.pircbot.*;
import java.io.IOException;

public class RunTeamBot
{
	public static void main(String[] args) throws NickAlreadyInUseException
	{
      // Now start our bot up.
      TeamBot teamBot = new TeamBot();
        
      // Enable debugging output.
      teamBot.setVerbose(true);
       
		while(true)
		{	
			try{
		   	// Connect to the IRC server.
		   	teamBot.connect("irc.freenode.net");
				break;
			}
			
			catch(NickAlreadyInUseException e)
			{
				teamBot.setBotName(teamBot.getName() + "_");
				teamBot.log(e.getMessage());
			}
			
			catch(IrcException e)
			{
				teamBot.log("The server will not let you join!");
				teamBot.log(e.getMessage());
				System.exit(0);
			}
			catch(IOException e)
			{
				teamBot.log("Unable to connect to server!");
				teamBot.log(e.getMessage());
				System.exit(0);
			}
		}

      // Join the #pircbot channel.
      teamBot.joinChannel("#george-test-123");
	}
}
