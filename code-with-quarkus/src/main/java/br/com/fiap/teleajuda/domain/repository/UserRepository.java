package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.User;

public interface UserRepository {
    User criar(User user);
    User buscarUser(int id) throws EntidadeNaoLocalizada;
    void editar(User user);
}
