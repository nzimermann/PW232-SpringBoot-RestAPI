package RestSpring.CadastroAlunos;

//import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Estudante {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String nome;
	private int idade;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	@ManyToMany(mappedBy = "estudantes")
	private Set<Aula> aulas = new HashSet<Aula>();
	public void addAula(Aula a) {
		this.aulas.add(a);
	}
	public Set<Aula> getAulas() {
		return aulas;
	}
	
	
	
}
