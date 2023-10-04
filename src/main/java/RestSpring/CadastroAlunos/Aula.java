package RestSpring.CadastroAlunos;


//import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;
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
public class Aula {
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public void setId(int i) {
		// TODO Auto-generated method stub
		this.id = i;
	}
}
