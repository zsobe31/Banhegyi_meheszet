
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
@Table(name = "repce")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Repce.findAll", query = "SELECT r FROM Repce r")
    , @NamedQuery(name = "Repce.findById", query = "SELECT r FROM Repce r WHERE r.id = :id")
    , @NamedQuery(name = "Repce.findByNev", query = "SELECT r FROM Repce r WHERE r.nev = :nev")
    , @NamedQuery(name = "Repce.findByMennyiseg", query = "SELECT r FROM Repce r WHERE r.mennyiseg = :mennyiseg")
    , @NamedQuery(name = "Repce.findByAra", query = "SELECT r FROM Repce r WHERE r.ara = :ara")})
public class Repce implements Serializable {

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

    public Repce() {
    }

    public Repce(Integer id) {
        this.id = id;
    }

    public Repce(Integer id, String nev, String mennyiseg, String ara) {
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
    public static List<Repce> getAllRepce(EntityManager em){
        List<Repce> rep = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllRepce");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Repce r = em.find(Repce.class, elem[0]);
            rep.add(r);
        }
        
        return rep;
    }
    
    // hozzáadás
    public static Repce addNewRepce(EntityManager em, String nev, String mennyiseg, String ara){
        Repce r = null; // new Akac();
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewRepce");
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
            
            r = em.find(Repce.class, id);
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        return r;
    }
    
    // törlése
    public static Repce deleteRepce(EntityManager em, int id){
        Repce r1 = null;
        
        try{
            StoredProcedureQuery spq3 = em.createStoredProcedureQuery("deleteRepce");
            spq3.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq3.setParameter("idIN", id);
            spq3.execute();
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return r1;
    }
    
    // módosítás
    public static Repce updateRepce(EntityManager em, String nev, String mennyiseg, String ara, int id){
        Repce r2 = null;
        
        try{
            StoredProcedureQuery spq4 = em.createStoredProcedureQuery("updateRepce");
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
        
        return r2;
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
        if (!(object instanceof Repce)) {
            return false;
        }
        Repce other = (Repce) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Repce[ id=" + id + " ]";
    }
    
}
