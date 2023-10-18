package rest.cadastroAlunos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import rest.cadastroAlunos.aluno.Aluno;
import rest.cadastroAlunos.aluno.AlunoRepository;
import rest.cadastroAlunos.disciplina.Disciplina;
import rest.cadastroAlunos.disciplina.DisciplinaRepository;

@SpringBootApplication
public class CadastroAlunosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroAlunosApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
		AlunoRepository alunoRepository,
		DisciplinaRepository disciplinaRepository) {
		return args -> {
			alunoRepository.save(new Aluno("Aluno", "aluno@mail.com", 20));
			alunoRepository.save(new Aluno("Aluna", "aluna@mail.com", 19));
			disciplinaRepository.save(new Disciplina("Geografia", "descricao geografia"));
			disciplinaRepository.save(new Disciplina("Biologia", "descricao biologia"));
		};
	}
}
