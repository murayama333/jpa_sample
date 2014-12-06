import murayama.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by murayama on 2014/12/06.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("JPA Sample");

        Book book = new Book();
        book.setTitle("Spring book");
        book.setPrice(3000);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sample");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(book);
        tx.commit();

        Book book1 = em.find(Book.class, 1l);
        System.out.println(book1.getTitle());
        System.out.println(book1.getPrice());
    }
}
