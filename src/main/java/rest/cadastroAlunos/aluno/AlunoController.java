package rest.cadastroAlunos.aluno;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import rest.cadastroAlunos.disciplina.Disciplina;

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
		try {
			return alunoRepository.findById(id).orElseThrow();
		} catch (NoSuchElementException | IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno nao encontrado", e);
		}
	}

	@GetMapping("/aluno{id}/email")
	public String getAlunoEmail(@PathVariable("id") Integer id) {
		return getAluno(id).getEmail();
	}

	@GetMapping("/aluno{id}/disciplinas")
	public List<Disciplina> getAlunoDisciplinas(@PathVariable("id") Integer id) {
		return getAluno(id).getDisciplinas();
	}

	@PostMapping("/aluno")
	public void cadastrarAluno(@RequestBody Aluno aluno) {
		uniqueEmail(aluno.getEmail());
		alunoRepository.save(aluno);
	}

	@PutMapping("/aluno{id}")
	public void updateAluno(@RequestBody Aluno novoAluno, @PathVariable(required=false) Integer id) {
		if (id == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID deve ser informado");
		
		alunoRepository.findById(id).map(aluno -> {
			String nome = novoAluno.getNome(), email = novoAluno.getEmail();
			if (nome != null && !nome.isBlank()) {
				aluno.setNome(nome);
			}
			if (email != null && !email.isBlank() && uniqueEmail(email)) {
				aluno.setEmail(email);
			}
			if (novoAluno.getIdade() != null) {
				aluno.setIdade(novoAluno.getIdade());
			}
			return alunoRepository.save(aluno);
		}).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno nao encontrado");
		});
	}

	@DeleteMapping("/aluno{id}")
	public void deleteAluno(@PathVariable(required=false) Integer id) {
		if (id != null && alunoRepository.existsById(id)) {
			alunoRepository.deleteById(id);
		}
	}

	private boolean uniqueEmail(String email) {
		if (alunoRepository.findAlunoByEmail(email).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email ja cadastrado");
		}
		return true;
	}
}
