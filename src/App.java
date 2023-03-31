import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ExtratorConteudo extrator = new ExtratorConteudoFilmes();

        //String url = "https://api.nasa.gov/planetary/apod?api_key=[TOKEN_NASA]";
        //ExtratorConteudo extrator = new ExtratorConteudoNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extrairConteudos(json);

        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();

        for (Conteudo conteudo : conteudos) {
            
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

    }
}
