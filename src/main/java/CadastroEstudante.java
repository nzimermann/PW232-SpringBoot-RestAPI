import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CadastroEstudante {

    public static void main(String... args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Alunos-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /*
        Estudante e1 = new Estudante();
        e1.setId(1000);
        e1.setNome("Gabriel");
 */
        Classes clase = entityManager.find(Classes.class, 1);
        Estudante aluno = entityManager.find(Estudante.class, 1);
        Matricula matricula = entityManager.find(Matricula.class, 1);

        System.out.println("\n\n\n\n TESTES: ");
        System.out.println("Nome da classe da matricula: " + matricula.getClasses().getMateria());

/*
        Classes c1 = new Classes();
        c1.setClassesID(1);
        c1.setDescrisao("Interwbs");
        c1.setMateria("Web");
 
        Matricula m1 = new Matricula();
        m1.setId(1);
        m1.setClasses(clase);
        m1.setEstudante(aluno);
 */
        entityManager.getTransaction().begin();


        entityManager.getTransaction().commit();



        entityManager.close();
        entityManagerFactory.close();
    }

}
