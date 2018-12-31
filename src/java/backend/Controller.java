
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author zsobe31
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        
            try {
            PrintWriter out = response.getWriter();    
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banhegyi_meheszetPU");
            EntityManager em = emf.createEntityManager();
            
            if(request.getParameter("task").equals("betoltA")){
                List<Akac> akacok = Akac.getAllAkac(em);
                JSONArray akacokA = new JSONArray();
                for(Akac a : akacok){
                    JSONObject j = new JSONObject();               
                    j.put("id", a.getId());
                    j.put("nev", a.getNev());
                    j.put("mennyiseg", a.getMennyiseg());
                    j.put("ara", a.getAra());
                    akacokA.put(j);
                }
                out.print(akacokA.toString()); 
            }
            
            if(request.getParameter("task").equals("betoltH")){
                List<Hars> harsok = Hars.getAllHars(em);
                JSONArray harsokH = new JSONArray();
                for(Hars h : harsok){
                    JSONObject j = new JSONObject();               
                    j.put("id", h.getId());
                    j.put("nev", h.getNev());
                    j.put("mennyiseg", h.getMennyiseg());
                    j.put("ara", h.getAra());
                    harsokH.put(j);
                }
                out.print(harsokH.toString()); 
            }
            
            if(request.getParameter("task").equals("betoltN")){
                List<Nyarivirag> nyarik = Nyarivirag.getAllNyarivirag(em);
                JSONArray nyarikN = new JSONArray();
                for(Nyarivirag n : nyarik){
                    JSONObject j = new JSONObject();               
                    j.put("id", n.getId());
                    j.put("nev", n.getNev());
                    j.put("mennyiseg", n.getMennyiseg());
                    j.put("ara", n.getAra());
                    nyarikN.put(j);
                }
                out.print(nyarikN.toString()); 
            }
            
            if(request.getParameter("task").equals("betoltT")){
                List<Tavaszivirag> tavaszik = Tavaszivirag.getAllTavaszivirag(em);
                JSONArray tavaszikT = new JSONArray();
                for(Tavaszivirag t : tavaszik){
                    JSONObject j = new JSONObject();               
                    j.put("id", t.getId());
                    j.put("nev", t.getNev());
                    j.put("mennyiseg", t.getMennyiseg());
                    j.put("ara", t.getAra());
                    tavaszikT.put(j);
                }
                out.print(tavaszikT.toString()); 
            }
            
            if(request.getParameter("task").equals("betoltR")){
                List<Repce> repcek = Repce.getAllRepce(em);
                JSONArray repcekR = new JSONArray();
                for(Repce r : repcek){
                    JSONObject j = new JSONObject();               
                    j.put("id", r.getId());
                    j.put("nev", r.getNev());
                    j.put("mennyiseg", r.getMennyiseg());
                    j.put("ara", r.getAra());
                    repcekR.put(j);
                }
                out.print(repcekR.toString()); 
            }
            
            if(request.getParameter("task").equals("betoltF")){
                List<Napraforgo> napra = Napraforgo.getAllNapraforgo(em);
                JSONArray napraNF = new JSONArray();
                for(Napraforgo n : napra){
                    JSONObject j = new JSONObject();               
                    j.put("id", n.getId());
                    j.put("nev", n.getNev());
                    j.put("mennyiseg", n.getMennyiseg());
                    j.put("ara", n.getAra());
                    napraNF.put(j);
                }
                out.print(napraNF.toString()); 
            }
            
            if(request.getParameter("task").equals("betoltD")){
                List<Diszcsomag> diszek = Diszcsomag.getAllDiszcsomag(em);
                JSONArray diszekD = new JSONArray();
                for(Diszcsomag d : diszek){
                    JSONObject j = new JSONObject();               
                    j.put("id", d.getId());
                    j.put("nev", d.getNev());
                    j.put("mennyiseg", d.getMennyiseg());
                    j.put("ara", d.getAra());
                    diszekD.put(j);
                }
                out.print(diszekD.toString()); 
            }
            
            if(request.getParameter("task").equals("betoltV")){
                List<Velemenyek> velemenyek = Velemenyek.getAllVelemenyek(em);
                JSONArray velemenyekV = new JSONArray();
                for(Velemenyek v : velemenyek){
                    JSONObject j = new JSONObject();               
                    j.put("id", v.getId());
                    j.put("leiras", v.getLeiras());
                    j.put("szerzo", v.getSzerzo());
                    velemenyekV.put(j);
                }
                out.print(velemenyekV.toString()); 
            }
            
            if(request.getParameter("task").equals("login")){
                String user = request.getParameter("username");
                String passwd = request.getParameter("password");
                Felhasznalo f = Felhasznalo.login(em, user, passwd);
                if(f != null){
                    request.getSession().setAttribute("user", f);
                    //request.getSession().getAttribute("user");
                    JSONObject j = new JSONObject();
                    j.put("result", "Üdvözlünk kedves " + f.getFelhasznalonev());
                    out.print(j.toString());
                }//end of valid user
                else{
                    JSONObject j = new JSONObject();
                    j.put("result", "Hibás felhasználónév, vagy jelszó");
                    out.print(j.toString());
                }//end of non-valid user
            }//end of login
            
            
            
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
