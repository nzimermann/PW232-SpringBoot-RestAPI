package RestSpring.CadastroAlunos;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/estudante"})
public class ControllerEstudante {
    
    private RepositoryEstudante repository;

    ControllerEstudante(RepositoryEstudante estudanterepository) {
        this.repository = estudanterepository;
    }

    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/id"})
    public ResponseEntity findById(@PathVariable Integer id) {
        return repository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudante create (@RequestBody Estudante estudante) {
        return repository.save(estudante);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update (@PathVariable("id") Integer id, @RequestBody Estudante estudante) {
        return repository.findById(id)
        .map(record -> {
            record.setNome(estudante.getNome());
            record.setIdade(estudante.getIdade());
            Estudante updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> delete (@PathVariable Integer id) {
        return repository.findById(id)
            .map(record -> {
                repository.deleteById(id);
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
    }


}
