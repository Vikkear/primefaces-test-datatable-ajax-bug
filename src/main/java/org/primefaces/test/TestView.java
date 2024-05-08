package org.primefaces.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.model.LazyDataModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Named
@ViewScoped
public class TestView implements Serializable {

    private String string;

    @Inject
    private EntityManager em;
    private LazyDataModel<TestJpa> lazyDataModel;

    @Getter
    @Setter
    private List<TestJpa> testJpaList;

    @PostConstruct
    public void init() {
        string = "Welcome to PrimeFaces!!!";
        testJpaList = new ArrayList<TestJpa>();
        testJpaList.add(createNewTestJpa(1L, "New York", 123L));
        testJpaList.add(createNewTestJpa(2L, "Los Angeles", 124L));
        testJpaList.add(createNewTestJpa(3L, "Philadelphia", 125L));
        testJpaList.add(createNewTestJpa(4L, "Detroit", 126L));
        testJpaList.add(createNewTestJpa(5L, "Boston", 127L));
        testJpaList.add(createNewTestJpa(6L, "Borås", 128L));
    }

    private TestJpa createNewTestJpa(Long id, String stringCol, Long numberCol) {
        return TestJpa.builder()
                .id(id)
                .stringCol(stringCol)
                .numberCol(numberCol)
                .decimalCol(BigDecimal.valueOf(123.45))
                .dateCol(new Date())
                .build();
    }

    public void removeTestJpa(TestJpa testJpaToRemove) {
        testJpaList.remove(testJpaToRemove);
    }

}
