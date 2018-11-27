import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import murayama.Book;

/**
 * Created by murayama on 2014/12/06.
 */
public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sample");
		EntityManager em = emf.createEntityManager();

		example1(em);
		
		example2(em);
		example3(em);

		example4(em);
		example3(em);

		example5(em);
		example3(em);

		em.close();
		emf.close();
	}

	private static void example1(EntityManager em) {
		System.out.println("JPA Sample - example1");

		Book book = new Book();
		book.setTitle("Spring book");
		book.setPrice(3000);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(book);
		tx.commit();

		Book book1 = em.find(Book.class, 1l);
		System.out.println(book1.getTitle());
		System.out.println(book1.getPrice());
	}

	private static void example2(EntityManager em) {
		System.out.println("JPA Sample - example2");

		Book book1 = new Book();
		book1.setTitle("Java book");
		book1.setPrice(2000);

		Book book2 = new Book();
		book2.setTitle("HTML book");
		book2.setPrice(1000);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(book1);
		em.persist(book2);
		tx.commit();
	}

	private static void example3(EntityManager em) {
		System.out.println("JPA Sample - example3");

		TypedQuery<Book> query = em.createNamedQuery("Book.findAll", Book.class);
		List<Book> bookList = query.getResultList();
		bookList.forEach(b -> {
			System.out.println(b.getId() + "," + b.getTitle() + "," + b.getPrice());
		});
	}

	private static void example4(EntityManager em) {
		System.out.println("JPA Sample - example4");

		Book book = em.find(Book.class, 3l);
		book.setTitle("HTML5 Book");
		book.setPrice(1500);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(book);
		tx.commit();
	}	

	private static void example5(EntityManager em) {
		System.out.println("JPA Sample - example5");

		Book book = em.find(Book.class, 3l);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(book);
		tx.commit();
	}	

}
