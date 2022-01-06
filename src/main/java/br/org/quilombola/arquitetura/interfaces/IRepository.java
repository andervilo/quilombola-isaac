package br.org.quilombola.arquitetura.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<E,T> extends JpaRepository<E, T>, JpaSpecificationExecutor<E> {

}
