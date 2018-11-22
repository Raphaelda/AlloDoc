package entities;

import java.io.Serializable;

public class Drug implements Serializable
{

	private static final long serialVersionUID = 1L;
	private long drug_Id;
	private	float drug_Price;
	private	String drug_Name;
	private Boolean whith_Ordinance;
	private short drug_BoxQuantity;
	
	//Constructor
	public Drug(float drug_Price, String drug_Name,
			Boolean whith_Ordinance, short drug_BoxQuantity) {
		this.drug_Price = drug_Price;
		this.drug_Name = drug_Name;
		this.whith_Ordinance = whith_Ordinance;
		this.drug_BoxQuantity = drug_BoxQuantity;
	}
	public Drug() {
		drug_Id = 0;
		drug_Price = 0;
		drug_Name = "";
		whith_Ordinance = false;
		drug_BoxQuantity = 0;
	}
   
	//Methods
    @Override
     public boolean equals(Object obj)
       {
           if (obj == this)
               return true;
           if (obj == null || obj.getClass()!= this.getClass())
               return false;
           Drug drug = (Drug) obj;
           return (this.drug_BoxQuantity==drug.drug_BoxQuantity
                && this.drug_Id==drug.drug_Id
                && this.drug_Name.equals(drug.drug_Name)
                && this.drug_Price==drug.drug_Price
                && this.whith_Ordinance==drug.whith_Ordinance);
       }
	
	//GET AND SET
	public long getDrug_Id() {
		return drug_Id;
	}
	public void setDrug_Id(long drug_Id) {
		this.drug_Id = drug_Id;
	}
	public float getDrug_Price() {
		return drug_Price;
	}
	public void setDrug_Price(float f) {
		drug_Price = f;
	}
	public String getDrug_Name() {
		return drug_Name;
	}
	public void setDrug_Name(String drug_Name) {
		this.drug_Name = drug_Name;
	}
	public Boolean getWhith_Ordinance() {
		return whith_Ordinance;
	}
	public void setWhith_Ordinance(Boolean whith_Ordinance) {
		this.whith_Ordinance = whith_Ordinance;
	}
	public short getDrug_BoxQuantity() {
		return drug_BoxQuantity;
	}
	public void setDrug_BoxQuantity(short drug_BoxQuantity) {
		this.drug_BoxQuantity = drug_BoxQuantity;
	}
}
