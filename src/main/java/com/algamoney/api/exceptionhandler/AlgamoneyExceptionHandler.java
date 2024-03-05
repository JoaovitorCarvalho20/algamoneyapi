package com.algamoney.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Manipulador global de exceções personalizado para a aplicação Algamoney.
 */
@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

	
	
	
	
	
    @Autowired
    private MessageSource messageSource;

    /**
     * Manipula exceções relacionadas à leitura de mensagens HTTP não válidas.
     *
     * @param ex       A exceção lançada.
     * @param headers  Os cabeçalhos HTTP.
     * @param status   O status HTTP.
     * @param request  A solicitação da web.
     * @return Uma ResponseEntity contendo informações sobre o erro.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.getCause() !=null ? ex.getCause().toString():ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Manipula exceções relacionadas a argumentos de método não válidos.
     *
     * @param ex       A exceção lançada.
     * @param headers  Os cabeçalhos HTTP.
     * @param status   O status HTTP.
     * @param request  A solicitação da web.
     * @return Uma ResponseEntity contendo informações sobre o erro.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Cria uma lista de erros a partir de um BindingResult.
     *
     * @param bindingResult O BindingResult contendo os erros de validação.
     * @return Uma lista de erros formatados.
     */
    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemDesenvolvedor = fieldError.toString();
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }
    
    
    // tratando a exception para quando tentar apagar um codigo que ja foi apagado ele vai retornar um 404
    
    @ExceptionHandler({org.springframework.dao.EmptyResultDataAccessException.class })
   @ResponseStatus(HttpStatus.NOT_FOUND)//404
    public void EmptyResultDataAccessException(RuntimeException ex ) {
    	
    }
    
    
    

    /**
     * Classe interna representando um objeto de erro com mensagens para o usuário e desenvolvedor.
     */
    public static class Erro {

        private String mensagemUsuarioString;
        private String mensagemDesenvolvedorString;

        /**
         * Construtor para criar um objeto Erro.
         *
         * @param mensagemUsuarioString      A mensagem para o usuário.
         * @param mensagemDesenvolvedorString A mensagem para o desenvolvedor.
         */
        public Erro(String mensagemUsuarioString, String mensagemDesenvolvedorString) {
            super();
            this.mensagemUsuarioString = mensagemUsuarioString;
            this.mensagemDesenvolvedorString = mensagemDesenvolvedorString;
        }

        /**
         * Obtém a mensagem para o usuário.
         *
         * @return A mensagem para o usuário.
         */
        public String getMensagemUsuarioString() {
            return mensagemUsuarioString;
        }

        /**
         * Define a mensagem para o usuário.
         *
         * @param mensagemUsuarioString A mensagem para o usuário.
         */
        public void setMensagemUsuarioString(String mensagemUsuarioString) {
            this.mensagemUsuarioString = mensagemUsuarioString;
        }

        /**
         * Obtém a mensagem para o desenvolvedor.
         *
         * @return A mensagem para o desenvolvedor.
         */
        public String getMensagemDesenvolvedorString() {
            return mensagemDesenvolvedorString;
        }

        /**
         * Define a mensagem para o desenvolvedor.
         *
         * @param mensagemDesenvolvedorString A mensagem para o desenvolvedor.
         */
        public void setMensagemDesenvolvedorString(String mensagemDesenvolvedorString) {
            this.mensagemDesenvolvedorString = mensagemDesenvolvedorString;
        }
    }
}
