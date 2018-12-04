
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
@Table(name = "tavaszivirag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tavaszivirag.findAll", query = "SELECT t FROM Tavaszivirag t")
    , @NamedQuery(name = "Tavaszivirag.findById", query = "SELECT t FROM Tavaszivirag t WHERE t.id = :id")
    , @NamedQuery(name = "Tavaszivirag.findByNev", query = "SELECT t FROM Tavaszivirag t WHERE t.nev = :nev")
    , @NamedQuery(name = "Tavaszivirag.findByMennyiseg", query = "SELECT t FROM Tavaszivirag t WHERE t.mennyiseg = :mennyiseg")
    , @NamedQuery(name = "Tavaszivirag.findByAra", query = "SELECT t FROM Tavaszivirag t WHERE t.ara = :ara")})
public class Tavaszivirag implements Serializable {

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

    public Tavaszivirag() {
    }

    public Tavaszivirag(Integer id) {
        this.id = id;
    }

    public Tavaszivirag(Integer id, String nev, String mennyiseg, String ara) {
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
    public static List<Tavaszivirag> getAllTavaszivirag(EntityManager em){
        List<Tavaszivirag> tavasz = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllTavaszivirag");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Tavaszivirag t = em.find(Tavaszivirag.class, elem[0]);
            tavasz.add(t);
        }
        
        return tavasz;
    }
    
    // hozzáadás
    public static Tavaszivirag addNewTavaszivirag(EntityManager em, String nev, String mennyiseg, String ara){
        Tavaszivirag t = null; // new Tavaszivirag();
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewTavaszivirag");
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
            
            t = em.find(Tavaszivirag.class, id);
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        return t;
    }
    
    // törlése
    public static Tavaszivirag deleteTavaszivirag(EntityManager em, int id){
        Tavaszivirag t1 = null;
        
        try{
            StoredProcedureQuery spq3 = em.createStoredProcedureQuery("deleteTavaszivirag");
            spq3.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq3.setParameter("idIN", id);
            spq3.execute();
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return t1;
    }
    
    // módosítás
    public static Tavaszivirag updateTavaszivirag(EntityManager em, String nev, String mennyiseg, String ara, int id){
        Tavaszivirag t2 = null;
        
        try{
            StoredProcedureQuery spq4 = em.createStoredProcedureQuery("updateTavaszivirag");
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
        
        return t2;
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
        if (!(object instanceof Tavaszivirag)) {
            return false;
        }
        Tavaszivirag other = (Tavaszivirag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Tavaszivirag[ id=" + id + " ]";
    }
    
}
