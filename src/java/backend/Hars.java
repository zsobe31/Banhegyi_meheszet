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
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zsobe31
 */
@Entity
@Table(name = "hars")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hars.findAll", query = "SELECT h FROM Hars h")
    , @NamedQuery(name = "Hars.findById", query = "SELECT h FROM Hars h WHERE h.id = :id")
    , @NamedQuery(name = "Hars.findByNev", query = "SELECT h FROM Hars h WHERE h.nev = :nev")
    , @NamedQuery(name = "Hars.findByMennyiseg", query = "SELECT h FROM Hars h WHERE h.mennyiseg = :mennyiseg")
    , @NamedQuery(name = "Hars.findByAra", query = "SELECT h FROM Hars h WHERE h.ara = :ara")})
public class Hars implements Serializable {

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

    public Hars() {
    }

    public Hars(Integer id) {
        this.id = id;
    }

    public Hars(Integer id, String nev, String mennyiseg, String ara) {
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
    
    public static List<Hars> getAllHars(EntityManager em){
        List<Hars> harsok = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllHars");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Hars h = em.find(Hars.class, elem[0]);
            harsok.add(h);
        }
        
        return harsok;
    }
    
    // hozzáadás
    public static Hars addNewHars(EntityManager em, String nev, String mennyiseg, String ara){
        Hars h = null; // new Hars();
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewHars");
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
            
            h = em.find(Hars.class, id);
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        return h;
    }
    
    // törlése
    public static Hars deleteHars(EntityManager em, int id){
        Hars h1 = null;
        
        try{
            StoredProcedureQuery spq3 = em.createStoredProcedureQuery("deleteHars");
            spq3.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq3.setParameter("idIN", id);
            spq3.execute();
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return h1;
    }
    
    // módosítás
    public static Hars updateHars(EntityManager em, String nev, String mennyiseg, String ara, int id){
        Hars h2 = null;
        
        try{
            StoredProcedureQuery spq4 = em.createStoredProcedureQuery("updateHars");
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
        
        return h2;
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
        if (!(object instanceof Hars)) {
            return false;
        }
        Hars other = (Hars) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Hars[ id=" + id + " ]";
    }
    
}
