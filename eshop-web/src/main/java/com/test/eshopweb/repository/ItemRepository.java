package com.test.eshopweb.repository;

import com.test.eshopweb.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {

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
    @Query("select i from Item i where lower(i.name) like lower(?1)")
    List<Item> searchInNames(String namePart); //namePart musi byt neco jako "%java%"
    @Query("select i from Item i where lower(i.name) like lower(?1)")
    Page<Item> searchInNames(String namePart, Pageable pageable);
    @Query("select i from Item i where lower(i.name) like lower(?1)")
    List<Item> searchInNames(String namePart, Sort sort);

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

}
