package br.com.neolog.welcomekit.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.neolog.welcomekit.productCategory.ProductCategory;

/**
 * @author denis.goncalves
 *
 */
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
	@SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private Integer id;
	@Column(name = "name", nullable = false)
	@NotNull
	@Size(min = 1, max = 255)
	private String name;
	@Column(unique = true, nullable = false)
	private int code;
	@Column(name = "price", nullable = false)
	@Positive
	private double price;
	@Column(name = "weight", nullable = true)
	@Positive
	private double weight;
	@Column(name = "volume", nullable = true)
	@Positive
	private double volume;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "product_category", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY_ID"), nullable = false)
	private ProductCategory productCategory;

	protected Product() {

	}

	public Product(final String name, final int code, final double price, final double weigth, final double volume,
			final ProductCategory productCategory) {
		this.name = name;
		this.code = code;
		this.price = price;
		this.weight = weigth;
		this.volume = volume;
		this.productCategory = productCategory;

	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Product(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productCategory == null) ? 0 : productCategory.hashCode());
		temp = Double.doubleToLongBits(volume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (code != other.code)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productCategory == null) {
			if (other.productCategory != null)
				return false;
		} else if (!productCategory.equals(other.productCategory))
			return false;
		if (Double.doubleToLongBits(volume) != Double.doubleToLongBits(other.volume))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

}
