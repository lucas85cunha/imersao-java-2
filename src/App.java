import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        
        final String ANSI_RESET = "\u001B[0m";
        final String YELLOW = "\033[0;33m";
        final String PURPLE_BACKGROUND = "\u001B[45m";
        String estrela = "\u2B50";

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        // fazer uma conexão HTTP e buscar os top 250 filmes
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String,String>> listaFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> filme : listaFilmes) {
            System.out.println("");
            System.out.println("Titulo do Filme: " + filme.get("title"));
            System.out.println("Capa do Filme: " + filme.get("image"));
            System.out.println(PURPLE_BACKGROUND + "Classificacao: " + filme.get("rank") + ANSI_RESET);

            double nota = Double.parseDouble(filme.get("imDbRating"));
            double notaArredondada = Math.round(nota);
            for (int i = 1; i <= notaArredondada; i++)
                System.out.print(YELLOW + estrela + ANSI_RESET);

            System.out.println(" ");
        }

    }
}
