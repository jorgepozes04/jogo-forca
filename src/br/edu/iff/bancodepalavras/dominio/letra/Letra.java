package br.edu.iff.bancodepalavras.dominio.letra;

import br.edu.iff.dominio.ObjetoDominio;

public abstract class Letra {
    private char codigo;

    protected Letra(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    public void exibir(Object contexto) {}

    @Override
    public boolean equals(Object o){
        if(o instanceof Letra){
            return this == o;
        }
        return false;
    }

    //TODO
    //public int hashCode();

    public final String toString(){
        return this.codigo + "";
    }
}
