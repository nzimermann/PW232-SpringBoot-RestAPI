package rest.cadastroAlunos.disciplina;

import java.security.InvalidParameterException;
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

import rest.cadastroAlunos.aluno.Aluno;

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
	private List<Aluno> alunos;

	public Disciplina() {}

	public Disciplina(Integer id, String nome, String descricao) {
		this.setId(id);
		this.setNome(nome);
		this.setDescricao(descricao);
	}

	public Disciplina(String nome, String descricao)  {
		this.setNome(nome);
		this.setDescricao(descricao);
	}

	public Integer getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescricao() {
		return this.descricao;
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

	protected void setDescricao(String descricao) {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new InvalidParameterException("Nome deve ser preenchido");
		}
		this.descricao = descricao;
	}

	protected void addAluno(Aluno aluno) {
		if (aluno == null) {
			throw new IllegalArgumentException("Aluno nulo");
		}
		alunos.add(aluno);
	}
}
