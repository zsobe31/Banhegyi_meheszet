
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
@Table(name = "partnerek")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partnerek.findAll", query = "SELECT p FROM Partnerek p")
    , @NamedQuery(name = "Partnerek.findById", query = "SELECT p FROM Partnerek p WHERE p.id = :id")
    , @NamedQuery(name = "Partnerek.findByNev", query = "SELECT p FROM Partnerek p WHERE p.nev = :nev")
    , @NamedQuery(name = "Partnerek.findByUrl", query = "SELECT p FROM Partnerek p WHERE p.url = :url")
    , @NamedQuery(name = "Partnerek.findByIcon", query = "SELECT p FROM Partnerek p WHERE p.icon = :icon")})
public class Partnerek implements Serializable {

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
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @Column(name = "icon")
    private String icon;

    public Partnerek() {
    }

    public Partnerek(Integer id) {
        this.id = id;
    }

    public Partnerek(Integer id, String nev, String url, String icon) {
        this.id = id;
        this.nev = nev;
        this.url = url;
        this.icon = icon;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    // adatok lekérése
    public static List<Partnerek> getAllPartnerek(EntityManager em){
        List<Partnerek> par = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllPartnerek");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Partnerek p = em.find(Partnerek.class, elem[0]);
            par.add(p);
        }
        
        return par;
    }
    
    // hozzáadás
    public static Partnerek addNewPartnerek(EntityManager em, String nev, String url, String icon){
        Partnerek p = null; // new Partnerek();
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewPartnerek");
            spq.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
            spq.setParameter("nevIN", nev);
            spq.registerStoredProcedureParameter("urlIN", String.class, ParameterMode.IN);
            spq.setParameter("urlIN", url);
            spq.registerStoredProcedureParameter("iconIN", String.class, ParameterMode.IN);
            spq.setParameter("iconIN", icon);     
            spq.execute();
            
            StoredProcedureQuery spq2 = em.createStoredProcedureQuery("lastInsertId");
            spq2.registerStoredProcedureParameter("idOUT", Integer.class, ParameterMode.OUT);
            spq2.execute();
            Object o = spq2.getOutputParameterValue("idOUT");
            int id = Integer.parseInt(o.toString());
            
            p = em.find(Partnerek.class, id);
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        return p;
    }
    
    // törlése
    public static Partnerek deletePartnerek(EntityManager em, int id){
        Partnerek p1 = null;
        
        try{
            StoredProcedureQuery spq3 = em.createStoredProcedureQuery("deletePartnerek");
            spq3.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq3.setParameter("idIN", id);
            spq3.execute();
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return p1;
    }
    
    // módosítás
    public static Partnerek updatePartnerek(EntityManager em, String nev, String url, String icon, int id){
        Partnerek p2 = null;
        
        try{
            StoredProcedureQuery spq4 = em.createStoredProcedureQuery("updatePartnerek");
            spq4.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
            spq4.setParameter("nevIN", nev);
            spq4.registerStoredProcedureParameter("urlIN", String.class, ParameterMode.IN);
            spq4.setParameter("urlIN", url);
            spq4.registerStoredProcedureParameter("iconIN", String.class, ParameterMode.IN);
            spq4.setParameter("iconIN", icon);
            spq4.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq4.setParameter("idIN", id);
            spq4.execute();
            
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return p2;
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
        if (!(object instanceof Partnerek)) {
            return false;
        }
        Partnerek other = (Partnerek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Partnerek[ id=" + id + " ]";
    }
    
}
