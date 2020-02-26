package branch;

public class Branch {
	private int id;
	private String name;
	private String city;
	public void setId(int id) throws IllegalAccessException {
		if(id<0) {
			throw new IllegalAccessException("invalid number");
		}
		this.id=id;
		}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
