package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import java.util.ArrayList;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaRodadaRepository implements RodadaRepository {

    private ArrayList<Rodada> pool = new ArrayList<>();
    private static MemoriaRodadaRepository soleInstance = null;

    public static MemoriaRodadaRepository getSoleInstance() {
        if (MemoriaRodadaRepository.soleInstance == null)
            MemoriaRodadaRepository.soleInstance = new MemoriaRodadaRepository();

        return MemoriaRodadaRepository.soleInstance;
    }

    private MemoriaRodadaRepository() {
    }

    @Override
    public long getProximoId() {
        return pool.size();
    }

    @Override
    public Rodada getPorId(long id) {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getId() == id) {
                return this.pool.get(i);
            }
        }
        return null;
    }

    @Override
    public Rodada[] getPorJogador(Jogador jogador) {
        ArrayList<Rodada> rodadas = new ArrayList<>();

        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getJogador().getId() == jogador.getId()) {
                rodadas.add(this.pool.get(i));
            }
        }
        return rodadas.toArray(new Rodada[0]);
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {
        this.pool.add(rodada);
    }

    @Override
    public void atualizar(Rodada rodada) throws RepositoryException {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getId() == rodada.getId()) {
                this.pool.set(i, rodada);
            }
        }
    }

    @Override
    public void remover(Rodada rodada) throws RepositoryException {
        pool.remove(rodada);
    }

}