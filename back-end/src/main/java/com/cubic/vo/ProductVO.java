package com.cubic.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductVO {

	private Long pk;

	private Long productNum;

	private String upcCode;

	private String name;

	private String category;

	private String version;

	private String manufacturer;

	private Date createdDate;

	private Date activeDate;

	private Date inactiveDate;

	private Long price;

	private String description;

	private Long qtyCurrent;

	private Long qtyThreshold;

	private Long qtyOrder;

	private boolean activeStatus = false;

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public String getUpcCode() {
		return upcCode;
	}

	public void setUpcCode(String upcCode) {
		this.upcCode = upcCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	public Date getInactiveDate() {
		return inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getQtyCurrent() {
		return qtyCurrent;
	}

	public void setQtyCurrent(Long qtyCurrent) {
		this.qtyCurrent = qtyCurrent;
	}

	public Long getQtyThreshold() {
		return qtyThreshold;
	}

	public void setQtyThreshold(Long qtyThreshold) {
		this.qtyThreshold = qtyThreshold;
	}

	public Long getQtyOrder() {
		return qtyOrder;
	}

	public void setQtyOrder(Long qtyOrder) {
		this.qtyOrder = qtyOrder;
	}

	public boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		return "ProductVO [pk=" + pk + ", productNum=" + productNum + ", upcCode=" + upcCode + ", name=" + name
				+ ", category=" + category + ", version=" + version + ", manufacturer=" + manufacturer
				+ ", createdDate=" + createdDate + ", activeDate=" + activeDate + ", inactiveDate=" + inactiveDate
				+ ", price=" + price + ", description=" + description + ", qtyCurrent=" + qtyCurrent + ", qtyThreshold="
				+ qtyThreshold + ", qtyOrder=" + qtyOrder + "]";
	}

}
