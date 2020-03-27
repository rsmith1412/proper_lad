package com.robert.properLad.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Entity
@Table(name="orders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	/////Shipping Address/////
	@Size(min=1, message="Please provide a first name.")
	private String shipping_first_name;
	@Size(min=1, message="Please provide a last name.")
	private String shipping_last_name;
	@Size(min=1, message="Please provide an address.")
	private String shipping_address;
	private String shipping_unit;
	@Size(min=1, message="Please provide a city.")
	private String shipping_city;
	@Size(min=2, max=2, message="Please provide a state.")
	private String shipping_state;
	@Size(min=5, message="Please provide a zip code.")
	private String shipping_zip_code;
	/////Billing address/////
	private boolean sameAsShip = false;
	@Size(min=1, message="Please provide a first name.")
	private String billing_first_name;
	@Size(min=1, message="Please provide a last name.")
	private String billing_last_name;
	@Size(min=1, message="Please provide a billing address.")
	private String billing_address;
	private String billing_unit;
	@Size(min=1, message="Please provide the billing city.")
	private String billing_city;
	@Size(min=2, max=2, message="Please provide the billing state state.")
	private String billing_state;
	@Size(min=5, message="Please provide the billing zip code.")
	private String billing_zip_code;
	@DecimalMin(value="0.01", inclusive=false)
	@Digits(integer=10, fraction=2)
	private BigDecimal total_price;
	private String status;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    	name="products_orders",
    	joinColumns = @JoinColumn(name="order_id"),
    	inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> products;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    public Order() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShipping_first_name() {
		return shipping_first_name;
	}

	public void setShipping_first_name(String shipping_first_name) {
		this.shipping_first_name = shipping_first_name;
	}

	public String getShipping_last_name() {
		return shipping_last_name;
	}

	public void setShipping_last_name(String shipping_last_name) {
		this.shipping_last_name = shipping_last_name;
	}

	public String getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}

	public String getShipping_unit() {
		return shipping_unit;
	}

	public void setShipping_unit(String shipping_unit) {
		this.shipping_unit = shipping_unit;
	}

	public String getShipping_city() {
		return shipping_city;
	}

	public void setShipping_city(String shipping_city) {
		this.shipping_city = shipping_city;
	}

	public String getShipping_state() {
		return shipping_state;
	}

	public void setShipping_state(String shipping_state) {
		this.shipping_state = shipping_state;
	}

	public String getShipping_zip_code() {
		return shipping_zip_code;
	}

	public void setShipping_zip_code(String shipping_zip_code) {
		this.shipping_zip_code = shipping_zip_code;
	}

	public boolean isSameAsShip() {
		return sameAsShip;
	}

	public void setSameAsShip(boolean sameAsShip) {
		this.sameAsShip = sameAsShip;
	}

	public String getBilling_first_name() {
		return billing_first_name;
	}

	public void setBilling_first_name(String billing_first_name) {
		this.billing_first_name = billing_first_name;
	}

	public String getBilling_last_name() {
		return billing_last_name;
	}

	public void setBilling_last_name(String billing_last_name) {
		this.billing_last_name = billing_last_name;
	}

	public String getBilling_address() {
		return billing_address;
	}

	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}

	public String getBilling_unit() {
		return billing_unit;
	}

	public void setBilling_unit(String billing_unit) {
		this.billing_unit = billing_unit;
	}

	public String getBilling_city() {
		return billing_city;
	}

	public void setBilling_city(String billing_city) {
		this.billing_city = billing_city;
	}

	public String getBilling_state() {
		return billing_state;
	}

	public void setBilling_state(String billing_state) {
		this.billing_state = billing_state;
	}

	public String getBilling_zip_code() {
		return billing_zip_code;
	}

	public void setBilling_zip_code(String billing_zip_code) {
		this.billing_zip_code = billing_zip_code;
	}

	public BigDecimal getTotal_price() {
		return total_price;
	}

	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
}
