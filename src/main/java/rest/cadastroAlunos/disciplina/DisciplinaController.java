package rest.cadastroAlunos.disciplina;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rest.cadastroAlunos.aluno.AlunoRepository;

@RestController
public class DisciplinaController {

	private final DisciplinaRepository disciplinaRepository;
	private final AlunoRepository alunoRepository;

	@Autowired
	public DisciplinaController(DisciplinaRepository disciplinaRepository, 
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
		return disciplinaRepository.findById(id).orElse(null);
	}

	@PostMapping("/disciplina")
	public void addDisciplina(@RequestBody Disciplina disciplina) {
		if (disciplinaRepository.findDisciplinaByName(disciplina.getNome()).isPresent()) {
			throw new IllegalArgumentException("Disciplina ja cadastrada");
		}
		disciplinaRepository.save(disciplina);
	}

	@PostMapping("/disciplina{disciplina_id}{aluno_id}")
	public void addAlunoDisciplina(Integer disciplina_id, Integer aluno_id) {
		Disciplina disciplina = disciplinaRepository.findById(disciplina_id).get();
		disciplina.addAluno(alunoRepository.findById(aluno_id).get());
		disciplinaRepository.save(disciplina);
	}

	@PutMapping("/disciplina{id}")
	public void updateDisciplina(@RequestBody Disciplina nova_disciplina, @PathVariable("id") Integer id) {
		disciplinaRepository.findById(id).map(disciplina -> {
			disciplina.setNome(nova_disciplina.getNome());
			disciplina.setDescricao(nova_disciplina.getDescricao());
			return disciplinaRepository.save(disciplina);
		}).orElseGet(() -> {
			return disciplinaRepository.save(nova_disciplina);
		});
	}

	@DeleteMapping("/disciplina{id}")
	public void deleteDisciplina(@PathVariable("id") Integer id) {
		if (this.getDisciplina(id) != null) {
			disciplinaRepository.deleteById(id);
		}
	}
}
