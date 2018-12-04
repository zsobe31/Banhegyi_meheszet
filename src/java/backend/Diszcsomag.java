
package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zsobe31
 */
@Entity
@Table(name = "diszcsomag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diszcsomag.findAll", query = "SELECT d FROM Diszcsomag d")
    , @NamedQuery(name = "Diszcsomag.findById", query = "SELECT d FROM Diszcsomag d WHERE d.id = :id")
    , @NamedQuery(name = "Diszcsomag.findByNev", query = "SELECT d FROM Diszcsomag d WHERE d.nev = :nev")
    , @NamedQuery(name = "Diszcsomag.findByMennyiseg", query = "SELECT d FROM Diszcsomag d WHERE d.mennyiseg = :mennyiseg")
    , @NamedQuery(name = "Diszcsomag.findByAra", query = "SELECT d FROM Diszcsomag d WHERE d.ara = :ara")})
public class Diszcsomag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nev")
    private String nev;
    @Basic(optional = false)
    @Column(name = "mennyiseg")
    private String mennyiseg;
    @Basic(optional = false)
    @Column(name = "ara")
    private String ara;

    public Diszcsomag() {
    }

    public Diszcsomag(Integer id) {
        this.id = id;
    }

    public Diszcsomag(Integer id, String nev, String mennyiseg, String ara) {
        this.id = id;
        this.nev = nev;
        this.mennyiseg = mennyiseg;
        this.ara = ara;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(String mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public String getAra() {
        return ara;
    }

    public void setAra(String ara) {
        this.ara = ara;
    }
    
    // adatok lekérése
    public static List<Diszcsomag> getAllDiszcsomag(EntityManager em){
        List<Diszcsomag> disz = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllDiszcsomag");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Diszcsomag d = em.find(Diszcsomag.class, elem[0]);
            disz.add(d);
        }
        
        return disz;
    }
    
    // hozzáadás
    public static Diszcsomag addNewDiszcsomag(EntityManager em, String nev, String mennyiseg, String ara){
        Diszcsomag d = null; // new Akac();
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewDiszcsomag");
            spq.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
            spq.setParameter("nevIN", nev);
            spq.registerStoredProcedureParameter("mennyisegIN", String.class, ParameterMode.IN);
            spq.setParameter("mennyisegIN", mennyiseg);
            spq.registerStoredProcedureParameter("araIN", String.class, ParameterMode.IN);
            spq.setParameter("araIN", ara);     
            spq.execute();
            
            StoredProcedureQuery spq2 = em.createStoredProcedureQuery("lastInsertId");
            spq2.registerStoredProcedureParameter("idOUT", Integer.class, ParameterMode.OUT);
            spq2.execute();
            Object o = spq2.getOutputParameterValue("idOUT");
            int id = Integer.parseInt(o.toString());
            
            d = em.find(Diszcsomag.class, id);
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        return d;
    }
    
    // törlése
    public static Diszcsomag deleteDiszcsomag(EntityManager em, int id){
        Diszcsomag d1 = null;
        
        try{
            StoredProcedureQuery spq3 = em.createStoredProcedureQuery("deleteDiszcsomag");
            spq3.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq3.setParameter("idIN", id);
            spq3.execute();
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return d1;
    }
    
    // módosítás
    public static Diszcsomag updateDiszcsomag(EntityManager em, String nev, String mennyiseg, String ara, int id){
        Diszcsomag d2 = null;
        
        try{
            StoredProcedureQuery spq4 = em.createStoredProcedureQuery("updateDiszcsomag");
            spq4.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
            spq4.setParameter("nevIN", nev);
            spq4.registerStoredProcedureParameter("mennyisegIN", String.class, ParameterMode.IN);
            spq4.setParameter("mennyisegIN", mennyiseg);
            spq4.registerStoredProcedureParameter("araIN", String.class, ParameterMode.IN);
            spq4.setParameter("araIN", ara);
            spq4.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq4.setParameter("idIN", id);
            spq4.execute();
            
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return d2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diszcsomag)) {
            return false;
        }
        Diszcsomag other = (Diszcsomag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Diszcsomag[ id=" + id + " ]";
    }
    
}
