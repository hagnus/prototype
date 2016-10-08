package org.jboss.html5.model;
// Generated 25-Sep-2016 11:42:16 by Hibernate Tools 4.0.1.Final

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * VendorTag generated by hbm2java
 */
@Entity
@Table(name = "vendor_tag", catalog = "4fashion")
public class VendorTag implements Serializable {
	private static final long serialVersionUID = -8097626710000954617L;
	
	private long vendorTagId;
	private Vendor vendor;
	private Tag tag;

	public VendorTag() {
	}

	public VendorTag(long vendorTagId, Vendor vendor, Tag tag) {
		this.vendorTagId = vendorTagId;
		this.vendor = vendor;
		this.tag = tag;
	}

	@Id
	@Column(name = "VENDOR_TAG_ID", unique = true, nullable = false)
	public long getVendorTagId() {
		return this.vendorTagId;
	}

	public void setVendorTagId(long vendorTagId) {
		this.vendorTagId = vendorTagId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VENDOR_FK", nullable = false)
	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TAG_FK", nullable = false)
	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
