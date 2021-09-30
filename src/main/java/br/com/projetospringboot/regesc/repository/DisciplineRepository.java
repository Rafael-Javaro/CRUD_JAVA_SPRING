package br.com.projetospringboot.regesc.repository;

import br.com.projetospringboot.regesc.orm.Discipline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends CrudRepository<Discipline, Long> {
}

