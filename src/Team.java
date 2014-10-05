import java.util.HashSet;
import java.util.Set;

/**
 * Created by george.e.beuhler on 10/4/2014.
 */
public class Team {

    private String name;
    private Set<String> members;

    public Team() {
        this.members = new HashSet<String>();
        this.name = "";
    }

    public Team(String teamName) {
        this.members = new HashSet<String>();
        this.name = teamName;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }

    public void addMember(String memberName) {
        members.add(memberName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
