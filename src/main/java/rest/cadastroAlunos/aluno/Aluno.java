package rest.cadastroAlunos.aluno;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rest.cadastroAlunos.disciplina.Disciplina;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="nome")
	private String nome;

	@Column(name="email")
	private String email;

	@Column(name="idade")
	private Integer idade;

	@ManyToMany(mappedBy = "alunos")
	@Getter(lombok.AccessLevel.PROTECTED)
	private List<Disciplina> disciplinas;
}
