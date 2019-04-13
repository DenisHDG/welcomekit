package br.com.neolog.welcomekit.productCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product_category")
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_category_id_seq")
	@SequenceGenerator(name = "product_category_id_seq", sequenceName = "product_category_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private Integer id;

	@Column(name = "code", nullable = false)
	@Positive
	private int code;

	@Column(name = "name", nullable = false)
	@Size(min = 1, max = 255)
	private String name;

	public ProductCategory(final int code, final String name) {
		this.code = code;
		this.name = name;
	}

	protected ProductCategory() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ProductCategory other = (ProductCategory) obj;
		if (code != other.code)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}