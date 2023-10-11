package RestSpring.CadastroAlunos;

import java.util.HashSet;
import java.util.Set;

//import org.hibernate.annotations.ManyToAny;
//import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToMany;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Aula {
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String materia;
	private String descricao;

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private Set<Estudante> estudantes = new HashSet<Estudante>();

	public void addEstudante(Estudante e) {
		this.estudantes.add(e);
	}

	public void setId(int i) {
		// TODO Auto-generated method stub
		this.id = i;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "estudante_classe", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "id") })
	public Set<Estudante> getEstudantes() {
		return estudantes;
	}
}
