package RestSpring.CadastroAlunos;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CadastroAlunosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroAlunosApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RepositoryAula repository) {
		return args -> {
			repository.deleteAll();
			IntStream.range(1, 4)
			.mapToObj(i -> {
				Aula a = new Aula();
				Estudante e = new Estudante();
			//	e.setId(i);
				e.setIdade(i);
				e.setNome("Estudante" + i);
				//a.setId(i);
				a.setMateria("Materia"+i);
				a.setDescricao("Descricao");
				a.addEstudante(e);

				e.addAula(a);
				
				return a;
			})
			.map(v -> repository.save(v))
			.forEach(System.out::println);
			
		};
	}
}
