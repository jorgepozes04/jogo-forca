package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

import java.util.ArrayList;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaJogadorRepository implements JogadorRepository {

    private static MemoriaJogadorRepository soleInstance = null;
    private ArrayList<Jogador> pool = new ArrayList<>();

    public static MemoriaJogadorRepository getSoleInstance() {
        if (MemoriaJogadorRepository.soleInstance == null) {
            MemoriaJogadorRepository.soleInstance = new MemoriaJogadorRepository();
        }
        return MemoriaJogadorRepository.soleInstance;
    }

    private MemoriaJogadorRepository() {
    }

    @Override
    public long getProximoId() {
        return this.pool.size();
    }

    @Override
    public Jogador getPorId(long id) {
        return this.pool.get((int) id);
    }

    @Override
    public Jogador getPorNome(String nome) {
        for (Jogador jogador : pool) {
            if (jogador.getNome().equals(nome)) {
                return jogador;
            }
        }
        return null;
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {
        this.pool.add(jogador);
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {
        for (int i = 0; i < this.pool.size(); i++) {
            if (jogador.getId() == this.pool.get(i).getId()) {
                this.pool.set(i, jogador);
            }
        }
    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {
        this.pool.remove(jogador);
    }
}
