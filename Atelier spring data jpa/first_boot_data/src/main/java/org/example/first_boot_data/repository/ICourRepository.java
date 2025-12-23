package org.example.first_boot_data.repository;

import org.example.first_boot_data.entity.Cour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourRepository extends JpaRepository<Cour , Integer> {
}
