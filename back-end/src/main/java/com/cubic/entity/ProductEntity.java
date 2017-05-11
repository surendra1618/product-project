package com.cubic.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
@NamedQueries({
		@NamedQuery(name = "ProductEntity.SearchByName", query = "select p from ProductEntity p where UPPER(p.name) like UPPER(:p1)"),
		@NamedQuery(name = "ProductEntity.SearchByUPC", query = "select p from ProductEntity p where UPPER(p.upcCode) like UPPER(:p1)"),
		@NamedQuery(name = "ProductEntity.SearchByProductNumber", query = "select p from ProductEntity p where UPPER(p.productNum) like UPPER(:p1)"),
		@NamedQuery(name = "ProductEntity.SearchAll", query = "select p from ProductEntity p"),
		@NamedQuery(name = "ProductEntity.SearchByExactNameForPost", query = "select count(p) from ProductEntity p where"
				+ " UPPER(p.name) like UPPER(:p1) OR UPPER(p.upcCode) like UPPER(:p2)"),
		@NamedQuery(name = "ProductEntity.SearchByExactNameForPut", query = "select count(p) from ProductEntity p where"
				+ " UPPER(p.pk) <> UPPER(:p1) AND (UPPER(p.name) like UPPER(:p2) OR UPPER(p.upcCode) like UPPER(:p3))") })
public class ProductEntity {

	@Id
	@Column(name = "PRODUCT_PK")
	@SequenceGenerator(name = "productSeq", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "productSeq")
	private Long pk;

	@Column(name = "PRODUCT_NUM")
	@SequenceGenerator(name = "productIdSeq", sequenceName = "PRODUCTID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "productIdSeq")
	private Long productNum;

	@Column(name = "PRODUCT_UPC")
	private String upcCode;

	@Column(name = "PRODUCT_NAME")
	private String name;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "VERSION")
	private String version;

	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Column(name = "DATE_CREATED")
	private Date createdDate;

	@Column(name = "DATE_ACTIVE")
	private Date activeDate;

	@Column(name = "DATE_INACTIVE")
	private Date inactiveDate;

	@Column(name = "PRODUCT_PRICE")
	private Long price;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "QTY_CURRENT")
	private Long qtyCurrent;

	@Column(name = "QTY_THRESHOLD")
	private Long qtyThreshold;

	@Column(name = "QTY_ORDER")
	private Long qtyOrder;

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

	@Override
	public String toString() {
		return "ProductEntity [pk=" + pk + ", productNum=" + productNum + ", upcCode=" + upcCode + ", name=" + name
				+ ", category=" + category + ", version=" + version + ", manufacturer=" + manufacturer
				+ ", createdDate=" + createdDate + ", activeDate=" + activeDate + ", inactiveDate=" + inactiveDate
				+ ", price=" + price + ", description=" + description + ", qtyCurrent=" + qtyCurrent + ", qtyThreshold="
				+ qtyThreshold + ", qtyOrder=" + qtyOrder + "]";
	}

}
