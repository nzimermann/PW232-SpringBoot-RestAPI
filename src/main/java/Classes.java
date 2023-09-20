import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "classes")
public class Classes {

    @Id
    @Column(name = "classesID")
    private Integer classesID;

    @Column(name = "materia")
    private String materia;

    @Column(name = "descrisao")
    private String descrisao;

    @OneToMany(mappedBy = "classes")
    Set<Matricula> classesMatricula;

    public Integer getClassesID() {
        return classesID;
    }

    public void setClassesID(Integer classesID) {
        this.classesID = classesID;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getDescrisao() {
        return descrisao;
    }

    public void setDescrisao(String descrisao) {
        this.descrisao = descrisao;
    }
}
