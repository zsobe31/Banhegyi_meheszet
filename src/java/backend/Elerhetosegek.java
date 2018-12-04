
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
@Table(name = "elerhetosegek")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elerhetosegek.findAll", query = "SELECT e FROM Elerhetosegek e")
    , @NamedQuery(name = "Elerhetosegek.findById", query = "SELECT e FROM Elerhetosegek e WHERE e.id = :id")
    , @NamedQuery(name = "Elerhetosegek.findByIcon", query = "SELECT e FROM Elerhetosegek e WHERE e.icon = :icon")
    , @NamedQuery(name = "Elerhetosegek.findByUrl", query = "SELECT e FROM Elerhetosegek e WHERE e.url = :url")})
public class Elerhetosegek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "icon")
    private String icon;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;

    public Elerhetosegek() {
    }

    public Elerhetosegek(Integer id) {
        this.id = id;
    }

    public Elerhetosegek(Integer id, String icon, String url) {
        this.id = id;
        this.icon = icon;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    // adatok lekérése
    public static List<Elerhetosegek> getAllElerhetosegek(EntityManager em){
        List<Elerhetosegek> eler = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllElerhetosegek");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Elerhetosegek e = em.find(Elerhetosegek.class, elem[0]);
            eler.add(e);
        }
        
        return eler;
    }
    
    // hozzáadás
    public static Elerhetosegek addNewElerhetosegek(EntityManager em, String icon, String url){
        Elerhetosegek e = null; // new Akac();
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewElerhetosegek");
            spq.registerStoredProcedureParameter("iconIN", String.class, ParameterMode.IN);
            spq.setParameter("iconIN", icon);
            spq.registerStoredProcedureParameter("urlIN", String.class, ParameterMode.IN);
            spq.setParameter("urlIN", url);  
            spq.execute();
            
            StoredProcedureQuery spq2 = em.createStoredProcedureQuery("lastInsertId");
            spq2.registerStoredProcedureParameter("idOUT", Integer.class, ParameterMode.OUT);
            spq2.execute();
            Object o = spq2.getOutputParameterValue("idOUT");
            int id = Integer.parseInt(o.toString());
            
            e = em.find(Elerhetosegek.class, id);
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        return e;
    }
    
    // törlése
    public static Elerhetosegek deleteElerhetosegek(EntityManager em, int id){
        Elerhetosegek e1 = null;
        
        try{
            StoredProcedureQuery spq3 = em.createStoredProcedureQuery("deleteElerhetosegek");
            spq3.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq3.setParameter("idIN", id);
            spq3.execute();
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return e1;
    }
    
    // módosítás
    public static Elerhetosegek updateElerhetosegek(EntityManager em, String icon, String url, int id){
        Elerhetosegek e2 = null;
        
        try{
            StoredProcedureQuery spq4 = em.createStoredProcedureQuery("updateElerhetosegek");
            spq4.registerStoredProcedureParameter("iconIN", String.class, ParameterMode.IN);
            spq4.setParameter("iconIN", icon);
            spq4.registerStoredProcedureParameter("urlIN", String.class, ParameterMode.IN);
            spq4.setParameter("urlIN", url);
            spq4.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq4.setParameter("idIN", id);
            spq4.execute();
            
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return e2;
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
        if (!(object instanceof Elerhetosegek)) {
            return false;
        }
        Elerhetosegek other = (Elerhetosegek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Elerhetosegek[ id=" + id + " ]";
    }
    
}
