package ua.nau.rentcar.model.beans;

/**
 * It's created on DB fields
 * It is used to transfer data to attributes
 * @author Geniy
 *
 */
public class OrderBean {
	
	private Integer id;
	private Integer requestFK;
	private String comment;
	private String status;
	private Integer cost;
	private Integer additionalCost;
	private Integer repairCost;
	
	public OrderBean(){
		id = 0;
		requestFK = 0;
		comment = new String();
		status = "OPENED";
		cost = 0;
		additionalCost = 0;
		repairCost = 0;
	}
	
	public OrderBean(Integer id, Integer requestFK, String comment,
			String status, Integer cost, Integer additionalCost,
			Integer repairCost) {
		super();
		this.id = id;
		this.requestFK = requestFK;
		this.comment = comment;
		this.status = status;
		this.cost = cost;
		this.additionalCost = additionalCost;
		this.repairCost = repairCost;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRequestFK() {
		return requestFK;
	}
	public void setRequestFK(Integer requestFK) {
		this.requestFK = requestFK;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Integer getAdditionalCost() {
		return additionalCost;
	}
	public void setAdditionalCost(Integer additionalCost) {
		this.additionalCost = additionalCost;
	}
	public Integer getRepairCost() {
		return repairCost;
	}
	public void setRepairCost(Integer repairCost) {
		this.repairCost = repairCost;
	}
}
