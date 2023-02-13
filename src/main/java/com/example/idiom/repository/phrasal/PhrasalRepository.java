package com.example.idiom.repository.phrasal;


import com.example.idiom.model.phrasal.PhrasalVerb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhrasalRepository extends JpaRepository<PhrasalVerb, Long> {
}
