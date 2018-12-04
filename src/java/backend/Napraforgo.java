
package backend;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
