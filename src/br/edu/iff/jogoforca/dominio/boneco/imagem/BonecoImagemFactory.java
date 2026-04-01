package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

public class BonecoImagemFactory implements BonecoFactory {
    private static BonecoImagemFactory soleInstance = null;

    private BonecoImagemFactory() {
    }

    public static BonecoImagemFactory getSoleInscance() {
        if (BonecoImagemFactory.soleInstance == null) {
            BonecoImagemFactory.soleInstance = new BonecoImagemFactory();
        }
        return BonecoImagemFactory.soleInstance;
    }

    public Boneco getBoneco() {
        return BonecoImagem.getSoleInstance();
    }
}
