package patnet.model;

/**
 * @author Dilip Makwana
 *
 */
public class Users {
	protected String Username;
	protected String Password;
	protected Type type;

	public enum Type {
		ORDINARY, ORGANIZATION, PHYSICIAN
	}

	protected int OrganizationId;
	protected int PhysicianId;
	protected String FirstName;
	protected String LastName;

	public Users(String username) {
		super();
		Username = username;
	}

	public Users(String username, String fn) {
		super();
		Username = username;
		FirstName = fn;
	}

	public Users(String username, String password, Type type, int organizationId, int physicianId, String firstName,
			String lastName) {
		super();
		Username = username;
		Password = password;
		this.type = type;
		OrganizationId = organizationId;
		PhysicianId = physicianId;
		FirstName = firstName;
		LastName = lastName;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getOrganizationId() {
		return OrganizationId;
	}

	public void setOrganizationId(int organizationId) {
		OrganizationId = organizationId;
	}

	public int getPhysicianId() {
		return PhysicianId;
	}

	public void setPhysicianId(int physicianId) {
		PhysicianId = physicianId;
	}

	public String getUserName() {
		return Username;
	}

	public void setUserName(String userName) {
		this.Username = userName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		this.LastName = lastName;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Users [Username=" + Username + ", Password=" + Password + ", OrganizationId=" + OrganizationId
				+ ", PhysicianId=" + PhysicianId + ", FirstName=" + FirstName + ", LastName=" + LastName + "]";
	}

}
