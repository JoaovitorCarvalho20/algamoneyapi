package com.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.LancamentoRepository;

/**
 * Controlador REST para manipulação de recursos relacionados a lançamentos.
 */
@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * Lista todos os lançamentos.
     *
     * @return Lista de lançamentos.
     */
    @GetMapping
    public List<Lancamento> listar() {
        return lancamentoRepository.findAll();
    }

    /**
     * Cria um novo lançamento a partir dos dados fornecidos no corpo da requisição.
     *
     * @param lancamento O lançamento a ser criado.
     * @param response   Objeto de resposta HTTP utilizado para configurar o cabeçalho de localização.
     * @return ResponseEntity contendo o lançamento criado e o código de status HTTP 201 (Created).
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    /**
     * Busca um lançamento pelo seu código.
     *
     * @param codigo O código do lançamento a ser buscado.
     * @return ResponseEntity contendo o lançamento encontrado, ou vazio se não existir.
     */
    @GetMapping("/{codigo}")
    //funçao para busca uma categoria
    public Optional<Categoria> buscarlANCAMENTOPeloCodigo(@PathVariable Long codigo) {
        return Optional.empty();
    }
    /**
     * Remove um lançamento pelo seu código.
     *
     * @param codigo O código do lançamento a ser removido.
     */
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void remover(@PathVariable Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }
}
