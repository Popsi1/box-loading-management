package com.example.polarisdigitechassessment.repository;

import com.example.polarisdigitechassessment.data.enums.BoxState;
import com.example.polarisdigitechassessment.data.model.Box;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {

	Optional<Box> findByTxref(String txref);

	Optional<List<Box>> findByBoxState(BoxState boxState);

	@Query("select t from Box t order by t.createdDate desc")
	Page<Box> allBoxes(Pageable pageable);

}