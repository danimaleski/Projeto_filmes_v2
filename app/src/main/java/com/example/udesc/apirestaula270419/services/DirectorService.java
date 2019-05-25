package com.example.udesc.apirestaula270419.services;

import com.example.udesc.apirestaula270419.models.Director;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DirectorService {

    private String baseUrl;
    private String repositoryName;
    private String fullUrl;
    private URL url;
    private HttpURLConnection connection;

    public DirectorService() {
        this.baseUrl = "http://10.0.2.2:8080"; //Eh o mesmo que localhost:8080
        this.repositoryName = "directors";
        this.fullUrl = this.baseUrl + "/" + this.repositoryName;
    }

    public Director getById(String id) {

        StringBuffer content = new StringBuffer();

        try {
            this.url = new URL(this.fullUrl + "/" + id);
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

        Director d = new Director();
        try {
            JSONObject director = new JSONObject(content.toString());

            d.setId(director.getString("id"));
            d.setName(director.getString("name"));
            d.setBirth(director.getString("birth"));
            d.setCountry(director.getString("country"));
            //d.setMovies(director.getString("movies"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return d;
    }

    public List<Director> getAll() {

        StringBuffer content = new StringBuffer();

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

        List<Director> lista = new ArrayList<>();

        try {

            JSONArray list = new JSONArray(content.toString());

            for (int i = 0; i < list.length(); i++) {
                JSONObject director = list.getJSONObject(i);

                Director d = new Director();

                d.setId(director.getString("id"));
                d.setName(director.getString("name"));

                lista.add(d);
                //System.out.println(movie);
            }

            //System.out.println(content.toString());

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return lista;
    }
}
