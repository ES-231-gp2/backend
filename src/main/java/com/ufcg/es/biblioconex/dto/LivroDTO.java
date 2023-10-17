package com.ufcg.es.biblioconex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    @JsonProperty("isbn")
    @NotBlank(message = "O ISBN não pode ser vazio")
    @ISBN(type = ISBN.Type.ANY, message = "O ISBN deve ser válido")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "O título não pode ser vazio")
    private String titulo;

    @JsonProperty("autores")
    @Builder.Default
    @Size.List(@Size(min = 1, message = "Deve haver pelo menos um autor"))
    private Set<String> autores = new HashSet<>();

    @JsonProperty("editora")
    @NotBlank(message = "A editora não pode ser vazia")
    private String editora;

    @JsonProperty("ano")
    @NotBlank(message = "O ano não pode ser vazio")
    private String ano;

    @JsonProperty("paginas")
    private String paginas;

    @JsonProperty("edicao")
    private Integer edicao;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("capa")
    private String capa;

    public static LivroDTO fromJson(String isbn, String json) {
        LivroDTO livro = null;

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (rootNode.get("totalItems").asInt() > 0) {
            livro = new LivroDTO();
            JsonNode volumeInfoNode = rootNode.get("items").get(0).get("volumeInfo");

            livro.setIsbn(isbn);
            if (volumeInfoNode.has("title")) {
                livro.setTitulo(volumeInfoNode.get("title").asText());
            }
            if (volumeInfoNode.has("title")) {
                livro.setTitulo(volumeInfoNode.get("title").asText());
            }
            if (volumeInfoNode.has("publisher")) {
                livro.setEditora(volumeInfoNode.get("publisher").asText());
            }
            if (volumeInfoNode.has("publishedDate")) {
                livro.setAno(volumeInfoNode.get("publishedDate").asText().substring(0, 4));
            }
            if (volumeInfoNode.has("pageCount")) {
                livro.setPaginas(volumeInfoNode.get("pageCount").asText());
            }
            if (volumeInfoNode.has("description")) {
                livro.setDescricao(volumeInfoNode.get("description").asText());
            }
            if (volumeInfoNode.has("imageLinks")) {
                livro.setCapa(volumeInfoNode.get("imageLinks").get("thumbnail").asText().replace("&edge=curl", ""));
            }

            ArrayNode autoresNode = (ArrayNode) volumeInfoNode.get("authors");
            for (JsonNode autorNode : autoresNode) {
                livro.getAutores().add(autorNode.asText());
            }
        }
        return livro;
    }
}
