package com.example.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ModelSaveDatabase.ModelSaveSubscription;

public interface JPARepository extends JpaRepository<ModelSaveSubscription, Long>{

}
