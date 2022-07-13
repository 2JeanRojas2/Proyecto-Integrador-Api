package com.NewDestiny.repository;

import com.NewDestiny.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
        @Query
        List<Product> findAllByCategoryId(Long categoryId);
        @Query
        List<Product> findAllByCityId(Long cityId);
        @Query
        List<Product> findAllBySchedulesStartDateAndSchedulesEndDate(LocalDate startDate, LocalDate endDate);

}

