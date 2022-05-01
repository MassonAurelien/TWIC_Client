package com.twic_client.Servlet;

import com.google.gson.Gson;
import com.twic_client.Beans.VilleModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="ListeVilleServlet", value="/ListeVille")
public class ListeVilleServlet extends HttpServlet {

    private int nbVilleAffiche;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        VilleModel[] villes = this.getVille("http://localhost:8181/ville");
        HttpSession session = request.getSession();

        List<VilleModel> villeAffiche = new ArrayList<>();
        VilleModel ville = new VilleModel();

        for(int i=0; i<nbVilleAffiche ; i++){
            ville = villes[i];
            villeAffiche.add(ville);
        }
        int numPage = 1;
        request.setAttribute("villesAffichees", villeAffiche);
        request.setAttribute("numPage",numPage);


        this.getServletContext().getRequestDispatcher("/WEB-INF/ville.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int numPage = Integer.parseInt(request.getParameter("numPage"));
        request.setAttribute("numPage",numPage);

        VilleModel[] villes = this.getVille("http://localhost:8181/ville");

        int begin;
        if(numPage == 1) begin = 0;
        else begin = 50*(numPage-1);

        List<VilleModel> villeAffiche = new ArrayList<>();
        VilleModel ville = new VilleModel();

        for(int i = begin; i<begin+50;i++) {
            if (i < villes.length) {
                ville = villes[i];
                villeAffiche.add(ville);
            }
        }
        request.setAttribute("villesAffichees", villeAffiche);

        this.getServletContext().getRequestDispatcher("/WEB-INF/ville.jsp").forward(request, response);
    }

    public VilleModel[] getVille(String url) throws IOException {
        JSONArray json = new JSONArray(IOUtils.toString(new URL(url), Charset.forName("UTF-8")));
        VilleModel[] villes = new Gson().fromJson(json.toString(),VilleModel[].class);
        return villes;
    }
}
