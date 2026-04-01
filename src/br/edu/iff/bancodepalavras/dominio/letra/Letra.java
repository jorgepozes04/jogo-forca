package br.edu.iff.bancodepalavras.dominio.letra;

public abstract class Letra {
    private char codigo;

    protected Letra(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    public abstract void exibir(Object contexto);

    @Override
    public boolean equals(Object o) {
        Letra outraLetra = (Letra) o;

        if (o instanceof Letra) {
            return this.codigo == outraLetra.getCodigo() && this.getClass().equals(outraLetra.getClass());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return codigo;
    }

    public final String toString() {
        return String.valueOf(this.codigo);
    }
}
