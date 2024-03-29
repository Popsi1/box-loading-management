package com.example.polarisdigitechassessment.repository;

import com.example.polarisdigitechassessment.data.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<List<Item>> findItemByBox_Id(Long id);
}
