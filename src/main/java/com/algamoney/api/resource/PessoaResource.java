package com.algamoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.PessoaService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

/**
 * Controlador REST para manipulação de recursos relacionados a pessoas.
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private final PessoaService pessoaService;

    /**
     * Construtor que injeta uma instância de PessoaService.
     *
     * @param pessoaService Serviço responsável por operações relacionadas a pessoas.
     */
   
    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    /**
     * Lista todas as pessoas.
     *
     * @return Lista de pessoas.
     */
    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    /**
     * Cria uma nova pessoa.
     *
     * @param pessoa   A pessoa a ser criada.
     * @param response Resposta HTTP.
     * @return Resposta com a pessoa criada e o código de status HTTP correspondente.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    /**
     * Busca uma pessoa pelo código.
     *
     * @param codigo Código da pessoa a ser buscada.
     * @return A pessoa encontrada ou vazio se não existir.
     */
    @GetMapping("/{codigo}")
    public Optional<Pessoa> buscarCategoriaPeloCodigo(@PathVariable Long codigo) {
        return pessoaRepository.findById(codigo);
    }

    /**
     * Remove uma pessoa pelo código.
     *
     * @param codigo Código da pessoa a ser removida.
     */
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void remover(@PathVariable Long codigo) {
        pessoaRepository.deleteById(codigo);
    }

    /**
     * Atualiza uma pessoa pelo código.
     *
     * @param codigo  Código da pessoa a ser atualizada.
     * @param pessoa  Dados atualizados da pessoa.
     * @return A pessoa atualizada ou 404 se não encontrada.
     */
    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(codigo, pessoa);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    /**
     * Atualiza a propriedade 'ativo' de uma pessoa.
     *
     * @param codigo Código da pessoa a ser atualizada.
     * @param ativo  Novo status 'ativo'.
     */
    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropiedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        // Chama o serviço para atualizar o status 'ativo' da pessoa com o código fornecido
        pessoaService.atualizarPessoaAtivo(codigo, ativo);
    }
}





