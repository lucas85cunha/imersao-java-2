import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo {
    public List<Conteudo> extrairConteudos(String json) {

        // extrair só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaAtributos = parser.parse(json);
        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : listaAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");
            var conteudo = new Conteudo(titulo, urlImagem);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
