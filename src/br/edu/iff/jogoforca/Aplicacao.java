package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory;
import br.edu.iff.jogoforca.emmemoria.MemoriaRepositoryFactory;
import br.edu.iff.jogoforca.texto.ElementoGraficoTextoFactory;

public class Aplicacao {

    private final String[] TIPOS_REPOSITORY_FACTORY = { "memoria" };
    private final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = { "texto" };
    private final String[] TIPOS_RODADA_FACTORY = { "sorteio" };

    private static Aplicacao soleInstance = null;

    private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
    private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

    public Aplicacao getSoleInstance() {
        if (Aplicacao.soleInstance == null) {
            Aplicacao.soleInstance = new Aplicacao();
        }

        return Aplicacao.soleInstance;
    }

    private Aplicacao() {
    }

    public void configurar() {
        RepositoryFactory repositoryFactory = getRepositoryFactory();

        TemaFactoryImpl.createSoleInstance(repositoryFactory.getTemaRepository());
        PalavraFactoryImpl.createSoleInstance(repositoryFactory.getPalavraRepository());
        JogadorFactoryImpl.createSoleInstance(repositoryFactory.getJogadorRepository());
        RodadaSorteioFactory.createSoleInstance(
                repositoryFactory.getRodadaRepository(),
                repositoryFactory.getTemaRepository(),
                repositoryFactory.getPalavraRepository());

        Palavra.setLetraFactory(getLetraFactory());
        Rodada.setBonecoFactory(getBonecoFactory());

        PalavraAppService.createSoleInstance(
                repositoryFactory.getTemaRepository(),
                repositoryFactory.getPalavraRepository(),
                getPalavraFactory());

        RodadaAppService.createSoleInstance(
                getRodadaFactory(),
                repositoryFactory.getRodadaRepository(),
                repositoryFactory.getJogadorRepository());
    }

    public String[] getTiposRepositoryFactory() {
        String[] copia = this.TIPOS_REPOSITORY_FACTORY;
        return copia;
    }

    public void setTipoRepositoryFactory(String tipo) {
        this.tipoRepositoryFactory = tipo;
    }

    public RepositoryFactory getRepositoryFactory() {
        if (this.tipoRepositoryFactory.toLowerCase().equals(TIPOS_ELEMENTO_GRAFICO_FACTORY[0].toLowerCase())) {
            return MemoriaRepositoryFactory.getSoleInstance();
        }
        return null;
    }

    public String[] getTiposElementoGraficoFactory() {
        String[] copia = this.TIPOS_ELEMENTO_GRAFICO_FACTORY;
        return copia;
    }

    public void setTipoElementoGraficoFactory(String tipo) {
        this.tipoElementoGraficoFactory = tipo;
    }

    private ElementoGraficoFactory getElementoGraficoFactory() {
        if (tipoElementoGraficoFactory.toLowerCase().equals(TIPOS_ELEMENTO_GRAFICO_FACTORY[0].toLowerCase())) {
            return ElementoGraficoTextoFactory.getSoleInstance();
        }
        return null;
    }

    public BonecoFactory getBonecoFactory() {
        return this.getElementoGraficoFactory();
    }

    public LetraFactory getLetraFactory() {
        return this.getElementoGraficoFactory();
    }

    public String[] getTiposRodadaFactory() {
        String[] copia = this.TIPOS_RODADA_FACTORY;

        return copia;
    }

    public void setTipoRodadaFactory(String tipo) {
        this.tipoRodadaFactory = tipo;
    }

    public RodadaFactory getRodadaFactory() {
        if (tipoRodadaFactory.toLowerCase().equals(TIPOS_RODADA_FACTORY[0].toLowerCase())) {
            return RodadaSorteioFactory.getSoleInstance();
        }
        return null;
    }

    public TemaFactory getTemaFactory() {
        return TemaFactoryImpl.getSoleInstance();
    }

    public PalavraFactory getPalavraFactory() {
        return PalavraFactoryImpl.getSoleInstance();
    }

    public JogadorFactory getJogadorFactory() {
        return JogadorFactoryImpl.getSoleInstance();
    }
}
