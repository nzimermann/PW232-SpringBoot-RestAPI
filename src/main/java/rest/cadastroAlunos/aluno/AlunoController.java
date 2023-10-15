package rest.cadastroAlunos.aluno;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlunoController {
	
	private final AlunoRepository alunoRepository;

	@Autowired
	public AlunoController(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	@GetMapping("/aluno")
	public List<Aluno> getAlunos() {
		return alunoRepository.findAll();
	}

	@GetMapping("/aluno{id}")
	public Aluno getAluno(@PathVariable("id") Integer id) {
		return alunoRepository.findById(id).orElse(null);
	}

	@PostMapping("/aluno")
	public void cadastrarAluno(@RequestBody Aluno aluno) {
		if (alunoRepository.findAlunoByEmail(aluno.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email ja cadastrado");
		}
		alunoRepository.save(aluno);
	}

	@PutMapping("/aluno{id}")
	public void updateAluno(@RequestBody Aluno novo_aluno, @PathVariable("id") Integer id) {
		alunoRepository.findById(id).map(aluno -> {
			aluno.setNome(novo_aluno.getNome());
			aluno.setEmail(novo_aluno.getEmail());
			aluno.setIdade(novo_aluno.getIdade());
			return alunoRepository.save(aluno);
		}).orElseGet(() -> {
			return alunoRepository.save(novo_aluno);
		});
	}

	@DeleteMapping("/aluno{id}")
	public void deleteAluno(@PathVariable("id") Integer id) {
		if (this.getAluno(id) != null) {
			alunoRepository.deleteById(id);
		}
	}
}
