
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
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zsobe31
 */
@Entity
@Table(name = "velemenyek")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Velemenyek.findAll", query = "SELECT v FROM Velemenyek v")
    , @NamedQuery(name = "Velemenyek.findById", query = "SELECT v FROM Velemenyek v WHERE v.id = :id")
    , @NamedQuery(name = "Velemenyek.findBySzerzo", query = "SELECT v FROM Velemenyek v WHERE v.szerzo = :szerzo")
    , @NamedQuery(name = "Velemenyek.findByLeiras", query = "SELECT v FROM Velemenyek v WHERE v.leiras = :leiras")})
public class Velemenyek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "szerzo")
    private String szerzo;
    @Basic(optional = false)
    @Column(name = "leiras")
    private String leiras;

    public Velemenyek() {
    }

    public Velemenyek(Integer id) {
        this.id = id;
    }

    public Velemenyek(Integer id, String szerzo, String leiras) {
        this.id = id;
        this.szerzo = szerzo;
        this.leiras = leiras;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }
    
    // adatok lekérése
    public static List<Velemenyek> getAllVelemenyek(EntityManager em){
        List<Velemenyek> velem = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllVelemenyek");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Velemenyek v = em.find(Velemenyek.class, elem[0]);
            velem.add(v);
        }
        
        return velem;
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
        if (!(object instanceof Velemenyek)) {
            return false;
        }
        Velemenyek other = (Velemenyek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Velemenyek[ id=" + id + " ]";
    }
    
}
