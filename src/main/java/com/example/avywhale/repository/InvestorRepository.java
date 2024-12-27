package com.example.avywhale.repository;

import com.example.avywhale.model.Investment;
import com.example.avywhale.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {

}
