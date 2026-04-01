package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

public class BonecoTextoFactory implements BonecoFactory {
    private static BonecoTextoFactory soleInstance = null;

    private BonecoTextoFactory() {
    }

    public static BonecoTextoFactory getSoleInscance() {
        if (BonecoTextoFactory.soleInstance == null) {
            BonecoTextoFactory.soleInstance = new BonecoTextoFactory();
        }
        return BonecoTextoFactory.soleInstance;
    }

    public Boneco getBoneco() {
        return BonecoTexto.getSoleInstance();
    }
}
