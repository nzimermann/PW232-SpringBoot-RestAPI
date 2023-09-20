import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "matricula")
public class Matricula {
    
    @Id
    @Column(name = "matricula_id")
    private Integer id;

    @ManyToMany
    @JoinColumn(name = "estudante_id")
    Estudante estudante;

    @ManyToMany
    @JoinColumn(name = "classes_id")
    Classes classes;
}
