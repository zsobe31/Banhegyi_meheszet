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