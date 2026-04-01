package br.edu.iff.jogoforca.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory {

    private static ElementoGraficoImagemFactory soleInstance = null;

    private BonecoImagemFactory bonecoFactory;

    private LetraImagemFactory letraFactory;

    public ElementoGraficoImagemFactory getSoleInstance() {
        if (ElementoGraficoImagemFactory.soleInstance == null) {
            ElementoGraficoImagemFactory.soleInstance = new ElementoGraficoImagemFactory();
        }

        return ElementoGraficoImagemFactory.soleInstance;
    }

    private ElementoGraficoImagemFactory() {
        this.bonecoFactory = BonecoImagemFactory.getSoleInscance();
        this.letraFactory = LetraImagemFactory.getSoleInstance();
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
