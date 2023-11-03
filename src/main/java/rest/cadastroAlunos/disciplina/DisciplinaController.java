package rest.cadastroAlunos.disciplina;

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

import rest.cadastroAlunos.aluno.Aluno;
import rest.cadastroAlunos.aluno.AlunoRepository;

@RestController
public class DisciplinaController {

	private final DisciplinaRepository disciplinaRepository;
	private final AlunoRepository alunoRepository;

	@Autowired
	public DisciplinaController(
		DisciplinaRepository disciplinaRepository, 
		AlunoRepository alunoRepository) {
		this.disciplinaRepository = disciplinaRepository;
		this.alunoRepository = alunoRepository;
	}

	@GetMapping("/disciplina")
	public List<Disciplina> getDisciplinas() {
		return disciplinaRepository.findAll();
	}

	@GetMapping("/disciplina{id}")
	public Disciplina getDisciplina(@PathVariable("id") Integer id) {
		try {
			return disciplinaRepository.findById(id).orElseThrow();
		} catch (NoSuchElementException | IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada", e);
		}
	}

	@GetMapping("/disciplina{id}/alunos")
	public List<Aluno> getDisciplinaAlunos(@PathVariable("id") Integer id) {
		return getDisciplina(id).getAlunos();
	}

	@GetMapping("/disciplina{id}/nome")
	public String getDisciplinaNome(@PathVariable("id") Integer id) {
		return getDisciplina(id).getNome();
	}

	@PostMapping("/disciplina")
	public void addDisciplina(@RequestBody Disciplina disciplina) {
		uniqueName(disciplina.getNome());
		disciplinaRepository.save(disciplina);
	}

	@PostMapping("/disciplina{disciplina_id}{aluno_id}")
	public void addAlunoDisciplina(Integer disciplina_id, Integer aluno_id) {
		Disciplina disciplina = getDisciplina(disciplina_id);
		if (aluno_id != null && alunoRepository.existsById(aluno_id)) {
			disciplina.addAluno(alunoRepository.findById(aluno_id).get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno nao encontrado");
		}
		disciplinaRepository.save(disciplina);
	}

	@PutMapping("/disciplina{id}")
	public void updateDisciplina(
		@RequestBody Disciplina novaDisciplina, 
		@PathVariable(required=false) Integer id) {
		if (id == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID deve ser informado");

		disciplinaRepository.findById(id).map(disciplina -> {
			String nome = novaDisciplina.getNome(), desc = novaDisciplina.getDescricao();
			if (nome != null && !nome.isBlank() && uniqueName(nome)) {
				disciplina.setNome(nome);
			}
			if (desc != null && !desc.isBlank()) {
				disciplina.setDescricao(novaDisciplina.getDescricao());
			}
			return disciplinaRepository.save(disciplina);
		}).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		});
	}

	@DeleteMapping("/disciplina{id}")
	public void deleteDisciplina(@PathVariable(required=false) Integer id) {
		if (id != null && disciplinaRepository.existsById(id)) {
			disciplinaRepository.deleteById(id);
		}
	}

	private boolean uniqueName(String nome) {
		if (disciplinaRepository.findDisciplinaByName(nome).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nome ja cadastrado");
		}
		return true;
	}
}
