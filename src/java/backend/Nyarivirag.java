
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
    
    // adatok lekérése
    public static List<Nyarivirag> getAllNyarivirag(EntityManager em){
        List<Nyarivirag> nyari = new ArrayList();
        
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllNyarivirag");
        List<Object[]> elemek = spq.getResultList();
        for(Object[] elem : elemek){
            Nyarivirag ny = em.find(Nyarivirag.class, elem[0]);
            nyari.add(ny);
        }
        
        return nyari;
    }
    
    // hozzáadás
    public static Nyarivirag addNewNyarivirag(EntityManager em, String nev, String mennyiseg, String ara){
        Nyarivirag ny = null; // new Akac();
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewNyarivirag");
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
            
            ny = em.find(Nyarivirag.class, id);
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        return ny;
    }
    
    // törlése
    public static Nyarivirag deleteNyarivirag(EntityManager em, int id){
        Nyarivirag ny1 = null;
        
        try{
            StoredProcedureQuery spq3 = em.createStoredProcedureQuery("deleteNyarivirag");
            spq3.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq3.setParameter("idIN", id);
            spq3.execute();
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
        
        return ny1;
    }
    
    // módosítás
    public static Nyarivirag updateNyarivirag(EntityManager em, String nev, String mennyiseg, String ara, int id){
        Nyarivirag ny2 = null;
        
        try{
            StoredProcedureQuery spq4 = em.createStoredProcedureQuery("updateNyarivirag");
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
        
        return ny2;
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
