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
