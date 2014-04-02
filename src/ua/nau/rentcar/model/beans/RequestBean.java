package ua.nau.rentcar.model.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * It's created on DB fields
 * It is used to transfer data to attributes
 * @author Geniy
 *
 */
public class RequestBean {
	
	private Integer id;
	private Integer userFK;
	private Integer carFK;
	private String beginDate;
	private String endDate;
	private String passport;
	private String requestMessage;
	private String responseMessage;
	private Integer cost;
	private String status;
	
		
	public RequestBean() {
		id = 0;
		userFK = 0;
		carFK = 0;					
		beginDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		endDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		passport = new String();
		requestMessage = new String();
		responseMessage = new String();
		cost = 0;
		status = "PENDING";
	}

	public RequestBean(Integer id, Integer userFK, Integer carFK,
			String beginDate, String endDate, String passport,
			String requestMessage, String responseMessage, Integer cost,
			String status) {
		super();
		this.id = id;
		this.userFK = userFK;
		this.carFK = carFK;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.passport = passport;
		this.requestMessage = requestMessage;
		this.responseMessage = responseMessage;
		this.cost = cost;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserFK() {
		return userFK;
	}
	public void setUserFK(Integer userFK) {
		this.userFK = userFK;
	}
	public Integer getCarFK() {
		return carFK;
	}
	public void setCarFK(Integer carFK) {
		this.carFK = carFK;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}	
