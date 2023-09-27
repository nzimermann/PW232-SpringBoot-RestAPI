package RestSpring.CadastroAlunos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEstudante extends JpaRepository<Estudante, Integer> {}