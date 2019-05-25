package com.example.udesc.apirestaula270419.services;

import com.example.udesc.apirestaula270419.models.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MoviesService {

    private String baseUrl;
    private String repositoryName;
    private String fullUrl;
    private URL url;
    private HttpURLConnection connection;

    public MoviesService() {
        this.baseUrl = "http://10.0.2.2:8080"; //Eh o mesmo que localhost:8080
        this.repositoryName = "movies";
        this.fullUrl = this.baseUrl + "/" + this.repositoryName;
    }

    public List<Movie> getAll() {

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
    public Movie getDetailsMovie(String id) {

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
            m.setDuration(movie.getInt("duration"));
            m.setYear(movie.getInt("year"));
            m.setCost(movie.getDouble("cost"));
            m.setCurrency(movie.getString("currency"));

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return m;
    }

    public boolean deleteMovie(
            String id
    ) {
        try {

            String idURL = "/" + id;

            this.url = new URL(this.fullUrl + idURL);
            this.connection = (HttpURLConnection) this.url.openConnection();
            this.connection.setRequestMethod("DELETE");
            this.connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            this.connection.setRequestProperty("Accept", "application/json");
            this.connection.setDoOutput(true);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(this.connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean addMovie(
            String id,
            String title,
            int duration,
            double cost,
            int year,
            String currency
    ) {
        try {
            this.url = new URL(this.fullUrl);
            this.connection = (HttpURLConnection) this.url.openConnection();
            this.connection.setRequestMethod("POST");
            this.connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            this.connection.setRequestProperty("Accept", "application/json");
            this.connection.setDoOutput(true);

            String jsonInputString = "{\"id\": \"" + id
                    +"\", \"title\" : \"" + title
                    +"\", \"duration\" : " +  duration
                    + ",\"cost\" : " + cost
                    + ",\"year\" : " + year
                    + ",\"currency\" : \"" + currency
                    + "\"}";

            try (OutputStream os = this.connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(this.connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
