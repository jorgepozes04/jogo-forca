package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import java.util.Random;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

public class RodadaSorteioFactory extends RodadaFactoryImpl {

    private static RodadaSorteioFactory soleInstance = null;

    public static void createSoleInstance(RodadaRepository rodadaRepository, TemaRepository temaRepository,
            PalavraRepository palavraRepository) {
        if (RodadaSorteioFactory.soleInstance == null)
            RodadaSorteioFactory.soleInstance = new RodadaSorteioFactory(rodadaRepository, temaRepository,
                    palavraRepository);
    }

    public static RodadaSorteioFactory getSoleInstance() {
        return RodadaSorteioFactory.soleInstance;
    }

    private RodadaSorteioFactory(RodadaRepository rodadaRepository, TemaRepository temaRepository,
            PalavraRepository palavraRepository) {
        super(rodadaRepository, temaRepository, palavraRepository);
    }

    @Override
    public Rodada getRodada(Jogador jogador) {

        Random rnd = new Random();

        Tema[] temas = getTemaRepository().getTodos();
        if (temas.length == 0) {
            throw new IllegalStateException("Nao possui temas cadatrados");
        }
        int idx = rnd.nextInt(temas.length);

        Tema temaSorteado = temas[idx];

        Palavra[] palavras = getPalavraRepository().getPorTema(temaSorteado);
        if (palavras.length == 0) {
            throw new IllegalStateException("Nao possui palavras cadastradas");
        }
        int numeroMaxPalavras = Math.min(palavras.length, Rodada.getMaxPalavras());
        int qtde = rnd.nextInt(numeroMaxPalavras) + 1;

        Palavra[] palavrasSorteadas = new Palavra[qtde];
        for (int i = 0; i < qtde; i++) {
            int aleatorio = rnd.nextInt(palavras.length);
            boolean jaFoiSorteada = false;

            for (int j = 0; j < i; j++) {
                if (palavrasSorteadas[j] == palavras[aleatorio]) {
                    jaFoiSorteada = true;
                    break;
                }
            }

            if (jaFoiSorteada)
                i--;
            else
                palavrasSorteadas[i] = palavras[aleatorio];
        }

        Rodada rodada = Rodada.criar(getProximoId(), palavrasSorteadas, jogador);
        try {
            getRodadaRepository().inserir(rodada);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        return rodada;
    }

}