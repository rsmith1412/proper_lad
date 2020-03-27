package com.robert.properLad.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Size(min=3, message="Product name is required.")
	private String name;
	@Size(min=5, message="Description is required.")
	@Column(length = 5535)
	private String description;
	@Size(min=1)
	private String mainImageUrl;
//	@Size(min=3, message="Category is required.")
//	private String category;
	@Size(min=3, message="Image link is required.")
	private String url;
	@DecimalMin(value="0.01", inclusive=false)
	@Digits(integer=10, fraction=2)
	private BigDecimal price;
	@Min(0)
	private Integer inventory_count;
	@Size(min=2, message="Brand is required.")
	private String brand;
	@Size(min=2, message="Size is required.")
	private String size;
	@Min(0)
	private Integer quantity_sold = 0;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        	name="products_orders",
        	joinColumns = @JoinColumn(name="product_id"),
        	inverseJoinColumns = @JoinColumn(name="order_id")
        )
    private List<Order> orders;
    
    @Size(min=3, message="Category is required.")
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        	name="products_categories",
        	joinColumns = @JoinColumn(name="product_id"),
        	inverseJoinColumns = @JoinColumn(name="category_id")
        )
    private List<Category> categories;
    
    @OneToMany(mappedBy="product", fetch = FetchType.LAZY)
    private List<Review> reviews;
    
    @OneToMany(mappedBy="product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Image> images;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;
    
    public Product() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMainImageUrl() {
		return mainImageUrl;
	}

	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getInventory_count() {
		return inventory_count;
	}

	public void setInventory_count(Integer inventory_count) {
		this.inventory_count = inventory_count;
	}
	
	public Integer getQuantity_sold() {
		return quantity_sold;
	}

	public void setQuantity_sold(Integer quantity_sold) {
		this.quantity_sold = quantity_sold;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<Image> getImages() {
		return images; 
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
}
