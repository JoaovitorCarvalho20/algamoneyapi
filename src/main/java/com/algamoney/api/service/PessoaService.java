package com.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;

/**
 * Serviço responsável por operações relacionadas a pessoas.
 */
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    /**
     * Construtor que injeta uma instância de PessoaRepository.
     *
     * @param pessoaRepository Repositório de pessoas.
     */
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * Atualiza uma pessoa existente no repositório.
     *
     * @param codigo Código da pessoa a ser atualizada.
     * @param pessoa Dados atualizados da pessoa.
     * @return A pessoa atualizada.
     * @throws EmptyResultDataAccessException Se a pessoa não for encontrada.
     */
    public Pessoa atualizarPessoa(Long codigo, Pessoa pessoa) {
        Optional<Pessoa> pessoaOptional = encontrarPessoaSalva(codigo);

        Pessoa pessoaSalva = pessoaOptional.get();
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");

        // Salva a pessoa atualizada no repositório
        return pessoaRepository.save(pessoaSalva);
    }

    /**
     * Busca e retorna uma pessoa salva no repositório pelo código.
     *
     * @param codigo Código da pessoa a ser buscada.
     * @return Optional contendo a pessoa encontrada, ou vazio se não existir.
     * @throws EmptyResultDataAccessException Se a pessoa não for encontrada.
     */
    private Optional<Pessoa> encontrarPessoaSalva(Long codigo) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);

        if (!pessoaOptional.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaOptional;
    }

    /**
     * Atualiza o status 'ativo' de uma pessoa.
     *
     * @param codigo Código da pessoa a ser atualizada.
     * @param ativo  Novo status 'ativo'.
     */
    public void atualizarPessoaAtivo(Long codigo, Boolean ativo) {
        Optional<Pessoa> pessoaOptional = encontrarPessoaSalva(codigo);
        Pessoa pessoaSalva = pessoaOptional.get();
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }
}
