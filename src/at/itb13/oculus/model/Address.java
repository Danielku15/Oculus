package at.itb13.oculus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
	private static final long serialVersionUID = -3958731646759638837L;
	
	private String _street;
	private String _streetNumber;
	private String _zip;
	private String _city;
	private String _country;
	
	public Address() {}
	
	public Address(String street, String streetNumber, String zip, String city, String country) {
		_street = street;
		_streetNumber = streetNumber;
		_zip = zip;
		_city = city;
		_country = country;
	}
	
	@Column(name = "street")
	public String getStreet() {
		return _street;
	}

	public void setStreet(String street) {
		_street = street;
	}

	@Column(name = "streetnumber")
	public String getStreetNumber() {
		return _streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		_streetNumber = streetNumber;
	}
	
	@Column(name = "zip")
	public String getZip() {
		return _zip;
	}

	public void setZip(String zip) {
		_zip = zip;
	}
	
	@Column(name = "city")
	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}
	
	@Column(name = "country")
	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}
}
