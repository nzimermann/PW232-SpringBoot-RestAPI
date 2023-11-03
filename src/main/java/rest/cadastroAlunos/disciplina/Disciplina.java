package rest.cadastroAlunos.disciplina;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rest.cadastroAlunos.aluno.Aluno;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="disciplina")
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome")
	private String nome;

	@Column(name="descricao")
	private String descricao;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="aluno_disciplina", joinColumns = {@JoinColumn(name="disciplina.id")}, inverseJoinColumns = {@JoinColumn(name="aluno.id")})
	@Getter(lombok.AccessLevel.PROTECTED)
	@Setter(lombok.AccessLevel.PROTECTED)
	private List<Aluno> alunos;

	protected void addAluno(Aluno aluno) {
		alunos.forEach(a -> {System.out.println(a.getEmail());}); // TODO
		if (aluno == null) {
			throw new IllegalArgumentException("Aluno nulo");
		}
		this.alunos.add(aluno);
	}
}
