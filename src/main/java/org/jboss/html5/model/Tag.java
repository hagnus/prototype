package org.jboss.html5.model;
// Generated 25-Sep-2016 11:42:16 by Hibernate Tools 4.0.1.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tag generated by hbm2java
 */
@Entity
@Table(name = "tag", catalog = "4fashion")
public class Tag implements Serializable {
	private static final long serialVersionUID = -4087187927983759267L;
	
	private long tagId;
	private String name;
	private Set<VendorTag> vendorTags = new HashSet<VendorTag>(0);
	private Set<ProductTag> productTags = new HashSet<ProductTag>(0);

	public Tag() {
	}

	public Tag(long tagId, String name) {
		this.tagId = tagId;
		this.name = name;
	}

	public Tag(long tagId, String name, Set<VendorTag> vendorTags, Set<ProductTag> productTags) {
		this.tagId = tagId;
		this.name = name;
		this.vendorTags = vendorTags;
		this.productTags = productTags;
	}

	@Id
	@Column(name = "TAG_ID", unique = true, nullable = false)
	public long getTagId() {
		return this.tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	@Column(name = "NAME", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<VendorTag> getVendorTags() {
		return this.vendorTags;
	}

	public void setVendorTags(Set<VendorTag> vendorTags) {
		this.vendorTags = vendorTags;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<ProductTag> getProductTags() {
		return this.productTags;
	}

	public void setProductTags(Set<ProductTag> productTags) {
		this.productTags = productTags;
	}

}
