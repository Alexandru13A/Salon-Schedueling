package salon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ClientRezervation")

public class ClientRezervation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="fName")
	private String fName;
	@Column(name="lName")
	private String lName;
	@Column(name="pNumber")
	private int pNumber;
	@Column(name="hairRemoval")
	private String hairRemoval;
	@Column(name="eyebrows")
	private String eyebrows;
	@Column(name="manicure")
	private String manicure;
	@Column(name="pedicure")
	private String pedicure;
	

	public ClientRezervation() {
		super();
	}


	public ClientRezervation(long id, String fName, String lName, int pNumber, String hairRemoval, String eyebrows,
			String manicure, String pedicure) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.pNumber = pNumber;
		this.hairRemoval = hairRemoval;
		this.eyebrows = eyebrows;
		this.manicure = manicure;
		this.pedicure = pedicure;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public int getpNumber() {
		return pNumber;
	}


	public void setpNumber(int pNumber) {
		this.pNumber = pNumber;
	}


	public String getHairRemoval() {
		return hairRemoval;
	}


	public void setHairRemoval(String hairRemoval) {
		this.hairRemoval = hairRemoval;
	}


	public String getEyebrows() {
		return eyebrows;
	}


	public void setEyebrows(String eyebrows) {
		this.eyebrows = eyebrows;
	}


	public String getManicure() {
		return manicure;
	}


	public void setManicure(String manicure) {
		this.manicure = manicure;
	}


	public String getPedicure() {
		return pedicure;
	}


	public void setPedicure(String pedicure) {
		this.pedicure = pedicure;
	}



	@Override
	public String toString() {
		return "ClientRezervation [id=" + id + ", fName=" + fName + ", lName=" + lName + ", pNumber=" + pNumber
				+ ", hairRemoval=" + hairRemoval + ", eyebrows=" + eyebrows + ", manicure=" + manicure + ", pedicure="
				+ pedicure + "]";
	}

}
