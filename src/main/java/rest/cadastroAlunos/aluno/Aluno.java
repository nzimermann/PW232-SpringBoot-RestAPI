package rest.cadastroAlunos.aluno;

import java.security.InvalidParameterException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import rest.cadastroAlunos.disciplina.Disciplina;

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
	private List<Disciplina> disciplinas;

	public Aluno() {}

	public Aluno(Integer id, String nome, String email, Integer idade) {
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
		this.setIdade(idade);
	}

	public Aluno(String nome, String email, Integer idade) {
		this.setNome(nome);
		this.setEmail(email);
		this.setIdade(idade);
	}

	public Integer getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

	public Integer getIdade() {
		return this.idade;
	}

	private void setId(Integer id) {
		if (id < 1) {
			throw new InvalidParameterException("ID deve ser maior que 0");
		}
		this.id = id;
	}

	protected void setNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new InvalidParameterException("Nome deve ser preenchido");
		}
		this.nome = nome;
	}

	protected void setEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new InvalidParameterException("Email deve ser preenchido");
		}
		this.email = email;
	}

	protected void setIdade(Integer idade) {
		if (idade < 1 || idade > 130) {
			throw new InvalidParameterException("Idade invalida");
		}
		this.idade = idade;
	}
}
