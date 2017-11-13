package patnet.model;

import java.util.HashMap;

public class Organizations extends BaseModel{
	private Long OrganizationId;
	private String Name;
	private String Address;
	private String City;
	private String State;
	private Integer ZipCode;
	private Integer Phone;
	private String Location;
	
	public Organizations(Long organizationid, String name, String address, String city, String state, Integer zipCode,
			Integer phone, String location) {
		super();
		OrganizationId = organizationid;
		Name = name;
		Address = address;
		City = city;
		State = state;
		ZipCode = zipCode;
		Phone = phone;
		Location = location;
	}


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

	public Integer getZipcode(){
		return ZipCode;
	}

	public void setZipcode(Integer ZipCode){
		this.ZipCode=ZipCode;
	}

	public Integer getPhone(){
		return Phone;
	}

	public void setPhone(Integer Phone){
		this.Phone=Phone;
	}

	public String getLocation(){
		return Location;
	}

	public void setLocation(String Location){
		this.Location=Location;
	}

	public Long getOrganizationid() {
		return OrganizationId;
	}

	public void setOrganizationid(Long organizationid) {
		OrganizationId = organizationid;
	}
	
	public HashMap<String, String> toMap(){
		HashMap<String, String> orgMap = new HashMap<String, String>();
		orgMap.put("OrganizationId",OrganizationId.toString());
		orgMap.put("Name",Name);
		orgMap.put("City",City);
		orgMap.put("State",State);
		orgMap.put("ZipCode",ZipCode.toString());
		orgMap.put("Phone",Phone.toString());
		orgMap.put("Location",Location);
		return orgMap;
	}
}