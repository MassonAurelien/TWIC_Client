package com.twic_client.Servlet;


import com.google.gson.Gson;
import com.twic_client.Beans.VilleModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;


@WebServlet(name = "Distance", value = "/distance")
public class DistanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VilleModel[] villes = this.getVille("http://localhost:8181/ville");
        HttpSession session = request.getSession();
        session.setAttribute("listVilles",villes);
        session.setAttribute("afficher",false);
        this.getServletContext().getRequestDispatcher("/WEB-INF/distance.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String codeVille1 = request.getParameter("ville1");
        String codeVille2 = request.getParameter("ville2");
        VilleModel[] ville1 = this.getVille("http://localhost:8181/ville?codePostal="+codeVille1);
        VilleModel[] ville2 = this.getVille("http://localhost:8181/ville?codePostal="+codeVille2);

        request.setAttribute("distance",this.calculDistance(ville1,ville2));

        HttpSession session = request.getSession();
        session.setAttribute("afficher",true);
        this.getServletContext().getRequestDispatcher("/WEB-INF/distance.jsp").forward(request, response);
    }

    public VilleModel[] getVille(String url) throws IOException {
        JSONArray json = new JSONArray(IOUtils.toString(new URL(url), Charset.forName("UTF-8")));
        VilleModel[] villes = new Gson().fromJson(json.toString(),VilleModel[].class);
        return villes;
    }

    public String calculDistance(VilleModel[] ville1, VilleModel[] ville2){
        VilleModel villeUn = new VilleModel();
        VilleModel villeDeux = new VilleModel();
        if(ville1.length >= 1 || ville2.length >= 1){
            villeUn = ville1[0];
            villeDeux = ville2[0];
        }
        else if (ville1.length == 0 && ville2.length == 0){
            return "0";
        }
        Double ville1Lat = Double.parseDouble(villeUn.getLatitude());
        Double ville1Long = Double.parseDouble(villeUn.getLongitude());
        Double ville2Lat = Double.parseDouble(villeDeux.getLatitude());
        Double ville2Long = Double.parseDouble(villeDeux.getLongitude());
        Double distance = 6371*Math.acos(Math.sin(ville1Lat)*Math.sin(ville2Lat)+Math.cos(ville1Lat)*Math.cos(ville2Lat)*Math.cos(ville2Long-ville1Long));
        return distance.toString();
    }
}
