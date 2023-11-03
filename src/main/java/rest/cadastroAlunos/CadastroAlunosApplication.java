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
			alunoRepository.save(Aluno.builder().nome("Aluno").email("aluno@mail.com").idade(20).build());
			alunoRepository.save(Aluno.builder().nome("Aluna").email("aluna@mail.com").idade(19).build());
			disciplinaRepository.save(Disciplina.builder().nome("Geografia").descricao("descricao geografia").build());
			disciplinaRepository.save(Disciplina.builder().nome("Biologia").descricao("descricao biologia").build());
		};
	}
}
