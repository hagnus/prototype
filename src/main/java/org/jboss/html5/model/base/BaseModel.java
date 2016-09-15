package org.jboss.html5.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.html5.model.access.User;

@MappedSuperclass
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseModel {
	
	public enum TpOperationEnum { 
		I("INSERT"), U("UPDATE"), D("DELETE");
		
		private final String value;
		
		TpOperationEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}	
	};	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@XmlElement
	private User user;
	
	@Column(name="TP_OPERATION")
	@Enumerated(EnumType.STRING)
	@XmlElement
	private TpOperationEnum tpOperation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DH_OPERATION")
	@XmlElement
	private Date dhOperation;
	
		
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TpOperationEnum getTpOperation() {
		return tpOperation;
	}

	public void setTpOperation(TpOperationEnum tpOperation) {
		this.tpOperation = tpOperation;
	}

	public Date getDhOperation() {
		return dhOperation;
	}

	public void setDhOperation(Date dhOperation) {
		this.dhOperation = dhOperation;
	}
	
	public void setDhOperationToNow() {
		setDhOperation(new Date());
	}	
}
