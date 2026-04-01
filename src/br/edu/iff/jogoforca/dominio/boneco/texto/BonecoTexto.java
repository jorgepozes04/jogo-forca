package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoTexto implements Boneco {
    private static BonecoTexto soleInstance = null;

    private BonecoTexto() {
    }

    public static BonecoTexto getSoleInstance() {
        if (BonecoTexto.soleInstance == null) {
            BonecoTexto.soleInstance = new BonecoTexto();
        }
        return BonecoTexto.soleInstance;
    }

    @Override
    public void exibir(Object contexto, int partes) {
        System.out.println("Boneco com " + partes + " partes.");
    }
}
