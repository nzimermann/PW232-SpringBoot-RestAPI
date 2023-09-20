import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CadastroEstudante {

    public static void main(String... args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Clientes-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Estudante aluno = entityManager.find(Estudante.class, 1);
        System.out.println(aluno.getNome());

        entityManager.close();
        entityManagerFactory.close();
    }

}
