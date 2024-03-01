package com.algamoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoney.api.model.Categoria;
import com.algamoney.api.repository.CategoriasRepository;


/** Controlador REST para manipulação de recursos relacionados a categorias.
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    /**
     * Repositório de categorias que será injetado automaticamente pelo Spring.
     */
    @Autowired
    private CategoriasRepository categoriasRepository;

    /**
     * Retorna uma lista de todas as categorias.
     *
     * @return Lista de categorias.
     */
    @GetMapping
    public List<Categoria> listar() {
        return categoriasRepository.findAll();
    }

    /**
     * Cria uma nova categoria a partir dos dados fornecidos no corpo da requisição.
     * Usando o Postmam
     * @param categoria  A categoria a ser criada.
     * @param response   Objeto de resposta HTTP utilizado para configurar o cabeçalho de localização.
     * @return ResponseEntity contendo a categoria criada e o código de status HTTP 201 (Created).
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriasRepository.save(categoria);

        // Constrói a URI da nova categoria e configura o cabeçalho de localização na resposta.
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    /**
     * Busca uma categoria pelo seu código.
     * Abusca e feita com a uri no postmam
     * @param codigo O código da categoria a ser buscada.
     * @return Optional contendo a categoria encontrada, ou vazio se não existir.
     */
    @GetMapping("/{codigo}")
    public Optional<Categoria> buscarCategoriaPeloCodigo(@PathVariable Long codigo) {
        return categoriasRepository.findById(codigo);
    }
}
