package br.edu.iff.bancodepalavras.dominio.letra.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;

public class LetraImagemFactory extends LetraFactoryImpl {
    private static LetraImagemFactory soleInstance = null;

    private LetraImagemFactory() {
    }

    public static LetraImagemFactory getSoleInstance() {
        if (LetraImagemFactory.soleInstance == null) {
            LetraImagemFactory.soleInstance = new LetraImagemFactory();
        }
        return LetraImagemFactory.soleInstance;
    }

    protected Letra criarLetra(char codigo) {
        return new LetraImagem(codigo);
    }
}
