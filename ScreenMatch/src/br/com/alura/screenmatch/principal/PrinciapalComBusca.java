package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.alura.screenmatch.modelos.ErrodeConversaoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;

public class PrinciapalComBusca {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o que deseja consultar: ");
        var busca = "";
        List<Titulo> titulos = new ArrayList<Titulo>();
        while (!busca.equalsIgnoreCase("sair"));
        {
            busca = input.nextLine();
           
            try {
                String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=4107437d";
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .create();
                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println(meuTitulo);
            } catch (IllegalArgumentException | ErrodeConversaoException e) {
                System.out.println("Aconteceu o erro: ");
                System.out.println(e.getMessage());
            } 
        }
        System.out.println("Programa finalizado!");
        
    }

}
