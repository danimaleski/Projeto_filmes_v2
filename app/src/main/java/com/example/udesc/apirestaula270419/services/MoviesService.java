package com.example.udesc.apirestaula270419.services;

import com.example.udesc.apirestaula270419.models.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MoviesService{

    private String baseUrl;
    private String repositoryName;
    private String fullUrl;
    private URL url;
    private HttpURLConnection connection;

    public MoviesService(){
        this.baseUrl = "http://10.0.2.2:8080"; //Eh o mesmo que localhost:8080
        this.repositoryName = "movies";
        this.fullUrl = this.baseUrl+"/"+this.repositoryName;
    }

    public List<Movie> getAll(){

        StringBuffer content = new StringBuffer();

        //conexão de acordo com os dados do MoviesServices
        try {

            this.url = new URL(this.fullUrl);

            this.connection = (HttpURLConnection) this.url.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Movie> lista = new ArrayList<>();

        try {

            JSONArray list = new JSONArray(content.toString());

            for (int i = 0; i < list.length(); i++) {
                JSONObject movie = list.getJSONObject(i);

                Movie m = new Movie();

                m.setId(movie.getString("id"));
                m.setTitle(movie.getString("title"));
                m.setYear(movie.getInt("year"));
                lista.add(m);
                //System.out.println(movie);
            }

            //System.out.println(content.toString());

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return lista;
    }

    //TRANSFORMAR NUM MÉTODO QUE RECEBE UM ID
    public Movie getDetailsMovie(String id){

        //variáel que recebe os dados do filme
        StringBuffer content = new StringBuffer();

        //conexão de acordo com os dados do MoviesServices
        try {

            String idURL = "/" + id;

            this.url = new URL(this.fullUrl + idURL);

            this.connection = (HttpURLConnection) this.url.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Movie m = new Movie();

        try {

        JSONObject movie = new JSONObject(content.toString());

        m.setId(movie.getString("id"));
        m.setTitle(movie.getString("title"));
        m.setYear(movie.getInt("year"));
        m.setCost(movie.getDouble("cost"));

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return m;
    }

}
