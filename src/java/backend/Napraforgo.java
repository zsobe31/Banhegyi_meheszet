
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
@Table(name = "napraforgo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Napraforgo.findAll", query = "SELECT n FROM Napraforgo n")
    , @NamedQuery(name = "Napraforgo.findById", query = "SELECT n FROM Napraforgo n WHERE n.id = :id")
    , @NamedQuery(name = "Napraforgo.findByNev", query = "SELECT n FROM Napraforgo n WHERE n.nev = :nev")
    , @NamedQuery(name = "Napraforgo.findByMennyiseg", query = "SELECT n FROM Napraforgo n WHERE n.mennyiseg = :mennyiseg")
    , @NamedQuery(name = "Napraforgo.findByAra", query = "SELECT n FROM Napraforgo n WHERE n.ara = :ara")})
public class Napraforgo implements Serializable {

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

    public Napraforgo() {
    }

    public Napraforgo(Integer id) {
        this.id = id;
    }

    public Napraforgo(Integer id, String nev, String mennyiseg, String ara) {
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
    public static List<Napraforgo> getAllNapraforgo(EntityManager em){
        List<Napraforgo> nap = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllNapraforgo");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Napraforgo n = em.find(Napraforgo.class, elem[0]);
            nap.add(n);
        }
        
        return nap;
    }
    
    // hozzáadás
    public static Napraforgo addNewNapraforgo(EntityManager em, String nev, String mennyiseg, String ara){
        Napraforgo n = null; // new Akac();
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewNapraforgo");
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
            
            n = em.find(Napraforgo.class, id);
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        return n;
    }
    
    // törlése
    public static Napraforgo deleteNapraforgo(EntityManager em, int id){
        Napraforgo n1 = null;
        
        try{
            StoredProcedureQuery spq3 = em.createStoredProcedureQuery("deleteNapraforgo");
            spq3.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq3.setParameter("idIN", id);
            spq3.execute();
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return n1;
    }
    
    // módosítás
    public static Napraforgo updateNapraforgo(EntityManager em, String nev, String mennyiseg, String ara, int id){
        Napraforgo n2 = null;
        
        try{
            StoredProcedureQuery spq4 = em.createStoredProcedureQuery("updateNapraforgo");
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
        
        return n2;
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
        if (!(object instanceof Napraforgo)) {
            return false;
        }
        Napraforgo other = (Napraforgo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Napraforgo[ id=" + id + " ]";
    }
    
}
