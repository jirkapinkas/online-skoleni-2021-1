package com.test.eshopweb.repository;

import com.test.eshopweb.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    /*
     * Efektivni tvorba selectu, varianta 1:
     * left join fetch
     */
    // select * from item left join category on item.category_id = category.category_id
//    @Query("select i from Item i left join fetch i.category")
//    List<Item> findAllFetchCategory(Sort sort);

    // select * from item left join category on item.category_id = category.category_id where item.item_id = ?1
//    @Query("select i from Item i left join fetch i.category where i.id = ?1")
//    Optional<Item> findByIdFetchCategory(int id);

    /*
     * Efektivni tvorba selectu, varianta 2:
     * entity graph
     */
    @Override
    @EntityGraph(Item.GRAPH_CATEGORY)
    List<Item> findAll(Sort sort);

    @Override
    @EntityGraph(Item.GRAPH_CATEGORY)
    Optional<Item> findById(Integer integer);

    /*
     * Metody, ktere vykonaji prislusny select na zaklade jejich nazvu
     */
    // select * from item where item.name = ?1
    List<Item> findByName(String name);

    // select * from item where item.price >= ?1 and item.price <= ?2
    List<Item> findByPriceBetween(double minPrice, double maxPrice);

    // select * from item where lower(item.name) like lower(?1)
    List<Item> findByNameContainingIgnoreCase(String name);
    Page<Item> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Item> findByNameContainingIgnoreCase(String name, Sort sort);

    /*
     * Metody, ktere vykonaji select na zaklade HQL / JPQL
     */
    @Query("select i from Item i where lower(i.name) like lower(:namePart)")
    List<Item> searchInNames(@Param("namePart") String namePart); //namePart musi byt neco jako "%java%"
    @Query("select i from Item i where lower(i.name) like lower(?1)")
    Page<Item> searchInNames(String namePart, Pageable pageable);
    @Query("select i from Item i where lower(i.name) like lower(?1)")
    List<Item> searchInNames(String namePart, Sort sort);

    @Query("select i.name, i.price from Item i")
    List<Object[]> find();

    /*
     * Metody, ktere vykonaji select na zaklade SQL
     */
    @Query(nativeQuery = true, value = "select i.name from item i")
    List<String> findNames();

    /*
     * Metody, ktere meni stav databaze
     */
    @Transactional
    @Modifying
    @Query("update Item i set i.price = i.price * 1.1")
    void increacePriceBy10Percent();

    // Pokrocilejsi metody jdou delat pres EntityManager:
    // https://dzone.com/articles/accessing-the-entitymanager-from-spring-data-jpa

    // Jak na dynamicke selecty:
    // Pomoci QueryDSL:
    // https://www.baeldung.com/rest-api-search-language-spring-data-querydsl
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.extensions.querydsl

}
