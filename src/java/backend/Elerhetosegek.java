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
