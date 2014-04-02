package ua.nau.rentcar.model.beans;

public class CarBean {
	
	private Integer id;
	private String mark;
	private String model;
	private String country;
	private String city;
	private String street;
	private Integer price;
	private String status;
	
	public CarBean() { }
	
	public CarBean(Integer id, String mark, String model, String country,
			String city, String street, Integer price, String status) {
		super();
		this.id = id;
		this.mark = mark;
		this.model = model;
		this.country = country;
		this.city = city;
		this.street = street;
		this.price = price;
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
