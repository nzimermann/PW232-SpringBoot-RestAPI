package rest.cadastroAlunos.disciplina;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{

	@Query("SELECT d FROM Disciplina d WHERE d.nome = ?1")
	Optional<Disciplina> findDisciplinaByName(String nome);
}
