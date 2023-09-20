import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.mapping.Set;

@Entity
@Table(name = "matricula")
public class Matricula {
    
    @Id
    @Column(name = "matricula_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "estudante_id")
    Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "classes_id")
    Classes classes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    
}
