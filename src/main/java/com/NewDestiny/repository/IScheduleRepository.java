package com.NewDestiny.repository;

import com.NewDestiny.model.entity.Product;
import com.NewDestiny.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query
    List<Schedule> findAllByUserEntityId(Long userEntityId);

    @Query
    List<Schedule> findAllByProductId(Long productId);
}
