package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.dominio.ObjetoDominioImpl;

public class Tema extends ObjetoDominioImpl {

    private String nome;

    private Tema(long id, String nome) {
        super(id);
        setNome(nome);
    }

    public static Tema criar(long id, String nome) {
        return new Tema(id, nome);
    }

    public static Tema reconstituir(long id, String nome) {
        return new Tema(id, nome);
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
}
