package org.primefaces.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProvider {

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("ExampleDatasource");
        insertData();
    }

    @Produces
    @RequestScoped
    public EntityManager produce() {
        return emf.createEntityManager();
    }

    public void disposes(@Disposes EntityManager em) {
        em.close();
    }

    private void insertData() {
        EntityManager em = produce();
        em.getTransaction().begin();

        ChildJpa child1 = ChildJpa.builder().id(1l).stringCol("Child 1").numberCol(456l)
                .decimalCol(BigDecimal.valueOf(456.45))
                .dateCol(new Date()).build();
        ChildJpa child2 = ChildJpa.builder().id(2l).stringCol("Child 2").numberCol(456l)
                .decimalCol(BigDecimal.valueOf(456.45))
                .dateCol(new Date()).build();
        em.persist(child1);
        em.persist(child2);

        em.persist(TestJpa.builder().id(1l).stringCol("New York").numberCol(123l).decimalCol(BigDecimal.valueOf(123.45))
                .dateCol(new Date()).child(child1).build());
        em.persist(TestJpa.builder().id(2l).stringCol("Los Angeles").numberCol(234l)
                .decimalCol(BigDecimal.valueOf(234.45)).dateCol(new Date()).child(child1).build());
        em.persist(TestJpa.builder().id(3l).stringCol("Philladelphia").numberCol(345l)
                .decimalCol(BigDecimal.valueOf(345.45)).dateCol(new Date()).child(child1).build());
        em.persist(TestJpa.builder().id(4l).stringCol("Detroit").numberCol(456l).decimalCol(BigDecimal.valueOf(456.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(5l).stringCol("Boston").numberCol(567l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(6l).stringCol("Keps").numberCol(568l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(7l).stringCol("Cap").numberCol(569l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(8l).stringCol("Canada").numberCol(570l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(9l).stringCol("Bahamas").numberCol(547l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(10l).stringCol("Washington D.C").numberCol(527l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(11l).stringCol("Cory in the House").numberCol(517l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(12l).stringCol("That's so Raven").numberCol(597l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(13l).stringCol("I am running out of").numberCol(5227l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(14l).stringCol("Imagination").numberCol(417l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(15l).stringCol("Andy Saled").numberCol(52347l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(16l).stringCol("Para Kim").numberCol(56787l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());

        em.getTransaction().commit();
    }
}
