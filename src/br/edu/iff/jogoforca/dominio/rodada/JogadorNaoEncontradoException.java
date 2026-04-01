package br.edu.iff.jogoforca.dominio.rodada;

public class JogadorNaoEncontradoException extends Exception {

    private String jogador;

    public JogadorNaoEncontradoException(String jogador) {
        super(jogador);

        setJogador(jogador);
    }

    private void setJogador(String jogador) {
        if (jogador == null || jogador.isEmpty())
            throw new IllegalArgumentException("O jogador nao pode ser nulo nem vazio");

        this.jogador = jogador;
    }

    public String getJogador() {
        return this.jogador;
    }

}