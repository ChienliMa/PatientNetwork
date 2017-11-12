package patnet.model;

public class Organizations{
	private Long Organizationid;
	private String Name;
	private String Address;
	private String City;
	private String State;
	private int ZipCode;
	private int Phone;
	private String Location;

	public String getName(){
		return Name;
	}

	public void setName(String Name){
		this.Name=Name;
	}

	public String getAddress(){
		return Address;
	}

	public void setAddress(String Address){
		this.Address=Address;
	}

	public String getCity(){
		return City;
	}

	public void setCity(String City){
		this.City=City;
	}

	public String getState(){
		return State;
	}

	public void setState(String State){
		this.State=State;
	}

	public int getZipcode(){
		return ZipCode;
	}

	public void setZipcode(int ZipCode){
		this.ZipCode=ZipCode;
	}

	public int getPhone(){
		return Phone;
	}

	public void setPhone(int Phone){
		this.Phone=Phone;
	}

	public String getLocation(){
		return Location;
	}

	public void setLocation(String Location){
		this.Location=Location;
	}

	public Long getOrganizationid() {
		return Organizationid;
	}

	public void setOrganizationid(Long organizationid) {
		Organizationid = organizationid;
	}
}