package br.edu.iff.jogoforca.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.texto.LetraTextoFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.texto.BonecoTextoFactory;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory {

    private static ElementoGraficoTextoFactory soleInstance = null;

    private BonecoTextoFactory bonecoFactory;

    private LetraTextoFactory letraFactory;

    public ElementoGraficoTextoFactory getSoleInstance() {
        if (ElementoGraficoTextoFactory.soleInstance == null) {
            ElementoGraficoTextoFactory.soleInstance = new ElementoGraficoTextoFactory();
        }

        return ElementoGraficoTextoFactory.soleInstance;
    }

    private ElementoGraficoTextoFactory() {
        this.bonecoFactory = BonecoTextoFactory.getSoleInscance();
        this.letraFactory = LetraTextoFactory.getSoleInstance();
    }

    public Boneco getBoneco() {
        return this.bonecoFactory.getBoneco();
    }

    public Letra getLetra(char codigo) {
        return this.letraFactory.getLetra(codigo);
    }

    public Letra getLetraEncoberta() {
        return this.letraFactory.getLetraEncoberta();
    }
}
