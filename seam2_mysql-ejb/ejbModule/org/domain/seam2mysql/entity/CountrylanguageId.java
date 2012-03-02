package org.domain.seam2mysql.entity;
// Generated 27/Fev/2012 17:12:47 by Hibernate Tools 3.4.0.CR1


import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * CountrylanguageId generated by hbm2java
 */
@Embeddable
public class CountrylanguageId  implements java.io.Serializable {


     private String countryCode;
     private String language;

    public CountrylanguageId() {
    }

    public CountrylanguageId(String countryCode, String language) {
       this.countryCode = countryCode;
       this.language = language;
    }
   


    @Column(name="CountryCode", nullable=false, length=3)
    @NotNull
    @Length(max=3)
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    @Column(name="Language", nullable=false, length=30)
    @NotNull
    @Length(max=30)
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CountrylanguageId) ) return false;
		 CountrylanguageId castOther = ( CountrylanguageId ) other; 
         
		 return ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) )
 && ( (this.getLanguage()==castOther.getLanguage()) || ( this.getLanguage()!=null && castOther.getLanguage()!=null && this.getLanguage().equals(castOther.getLanguage()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         result = 37 * result + ( getLanguage() == null ? 0 : this.getLanguage().hashCode() );
         return result;
   }   


}


