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
	CommandLineRunner init(RepositoryEstudante repository) {
		return args -> {
			//repository.deleteAll();
			IntStream.range(4, 7)
			.mapToObj(i -> {
				Estudante e = new Estudante();
				e.setId(i);
				e.setNome("Estudante" + i);
				e.setIdade(i);
				return e;
			})
			.map(v -> repository.save(v))
			.forEach(System.out::println);
			
		};
	}
}
