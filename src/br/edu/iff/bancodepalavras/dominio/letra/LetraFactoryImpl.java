package br.edu.iff.bancodepalavras.dominio.letra;

import java.util.ArrayList;

public abstract class LetraFactoryImpl implements LetraFactory {

    private char asterisco = '*';
    private ArrayList<Letra> pool = new ArrayList<>();
    private Letra encoberta = null;

    protected abstract Letra criarLetra(char codigo);

    protected LetraFactoryImpl() {
    }

    @Override
    public final Letra getLetra(char codigo) {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getCodigo() == codigo) {
                return pool.get(i);
            }
        }
        Letra novaLetra = criarLetra(codigo);

        pool.add(novaLetra);

        return novaLetra;
    }

    @Override
    public Letra getLetraEncoberta() {
        if (encoberta == null) {
            this.encoberta = criarLetra(asterisco);
        }
        return this.encoberta;
    }

}
