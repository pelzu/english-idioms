package com.example.idiom.repository.idiom;

import com.example.idiom.model.idiom.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomRepository extends JpaRepository<Idiom, Long> {
}
