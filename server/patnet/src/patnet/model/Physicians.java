package patnet.model;

public class Physicians {
	protected int providerId;
	protected String lastName;
	protected String firstName;
	protected String middleName;
	protected String credential;
	protected String gender;
	protected String streetAddress1;
	protected String streetAddress2;
	protected String city;
	protected int zipCode;
	protected String state;
	protected String primarySpecialty;
	protected String secondarySpecialties;
	
	public Physicians(int providerId, String lastName, String firstName, String middleName, String credential,
			String gender, String streetAddress1, String streetAddress2, String city, int zipCode, String state,
			String primarySpecialty, String secondarySpecialties) {
		super();
		this.providerId = providerId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.credential = credential;
		this.gender = gender;
		this.streetAddress1 = streetAddress1;
		this.streetAddress2 = streetAddress2;
		this.city = city;
		this.zipCode = zipCode;
		this.state = state;
		this.primarySpecialty = primarySpecialty;
		this.secondarySpecialties = secondarySpecialties;
	}
	
	public Physicians(int providerId) {
		this.providerId = providerId;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPrimarySpecialty() {
		return primarySpecialty;
	}

	public void setPrimarySpecialty(String primarySpecialty) {
		this.primarySpecialty = primarySpecialty;
	}

	public String getSecondarySpecialties() {
		return secondarySpecialties;
	}

	public void setSecondarySpecialties(String secondarySpecialties) {
		this.secondarySpecialties = secondarySpecialties;
	}
	
	
}
