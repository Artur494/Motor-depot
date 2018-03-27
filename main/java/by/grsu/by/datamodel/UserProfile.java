package by.grsu.by.datamodel;


import java.util.Date;

public class UserProfile extends AbstractModel {

	private UserCredentials credentials;

	private String firstName;

	private String lastName;

	private Date created;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public UserCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(UserCredentials credentials) {
		this.credentials = credentials;
	}

}

