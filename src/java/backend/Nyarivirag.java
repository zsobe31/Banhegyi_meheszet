/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "nyarivirag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nyarivirag.findAll", query = "SELECT n FROM Nyarivirag n")
    , @NamedQuery(name = "Nyarivirag.findById", query = "SELECT n FROM Nyarivirag n WHERE n.id = :id")
    , @NamedQuery(name = "Nyarivirag.findByNev", query = "SELECT n FROM Nyarivirag n WHERE n.nev = :nev")
    , @NamedQuery(name = "Nyarivirag.findByMennyiseg", query = "SELECT n FROM Nyarivirag n WHERE n.mennyiseg = :mennyiseg")
    , @NamedQuery(name = "Nyarivirag.findByAra", query = "SELECT n FROM Nyarivirag n WHERE n.ara = :ara")})
public class Nyarivirag implements Serializable {

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

    public Nyarivirag() {
    }

    public Nyarivirag(Integer id) {
        this.id = id;
    }

    public Nyarivirag(Integer id, String nev, String mennyiseg, String ara) {
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
        if (!(object instanceof Nyarivirag)) {
            return false;
        }
        Nyarivirag other = (Nyarivirag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Nyarivirag[ id=" + id + " ]";
    }
    
}
