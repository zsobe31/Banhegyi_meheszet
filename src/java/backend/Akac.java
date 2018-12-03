/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "akac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Akac.findAll", query = "SELECT a FROM Akac a")
    , @NamedQuery(name = "Akac.findById", query = "SELECT a FROM Akac a WHERE a.id = :id")
    , @NamedQuery(name = "Akac.findByNev", query = "SELECT a FROM Akac a WHERE a.nev = :nev")
    , @NamedQuery(name = "Akac.findByMennyiseg", query = "SELECT a FROM Akac a WHERE a.mennyiseg = :mennyiseg")
    , @NamedQuery(name = "Akac.findByAra", query = "SELECT a FROM Akac a WHERE a.ara = :ara")})
public class Akac implements Serializable {

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

    public Akac() {
    }

    public Akac(Integer id) {
        this.id = id;
    }

    public Akac(Integer id, String nev, String mennyiseg, String ara) {
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
    
    public static List<Akac> getAllAkac(EntityManager em){
        List<Akac> akacok = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllAkac");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Akac a = em.find(Akac.class, elem[0]);
            akacok.add(a);
        }
        
        return akacok;
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
        if (!(object instanceof Akac)) {
            return false;
        }
        Akac other = (Akac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Akac[ id=" + id + " ]";
    }
    
}
