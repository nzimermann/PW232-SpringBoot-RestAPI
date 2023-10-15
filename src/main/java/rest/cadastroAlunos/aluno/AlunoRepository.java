package rest.cadastroAlunos.aluno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	@Query("SELECT a FROM Aluno a WHERE a.email = ?1")
	Optional<Aluno> findAlunoByEmail(String email);
}
