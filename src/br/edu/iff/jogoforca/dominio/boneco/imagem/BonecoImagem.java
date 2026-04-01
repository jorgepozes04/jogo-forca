package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoImagem implements Boneco {
    private static BonecoImagem soleInstance = null;

    private BonecoImagem() {
    }

    public static BonecoImagem getSoleInstance() {
        if (BonecoImagem.soleInstance == null) {
            BonecoImagem.soleInstance = new BonecoImagem();
        }
        return BonecoImagem.soleInstance;
    }

    @Override
    public void exibir(Object contexto, int partes) {
        System.out.println("Boneco com " + partes + " partes.");
    }
}
