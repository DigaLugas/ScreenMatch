package br.com.alura.screenmatch.modelos;

public class ErrodeConversaoException extends RuntimeException {
    private String mensagem;

    public ErrodeConversaoException(String mensagem){
        this.mensagem = mensagem;
    }
  

    public String getMessage() {
        return this.mensagem;
    }
}
