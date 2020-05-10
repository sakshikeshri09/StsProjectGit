package org.capg.dao;

import org.capg.entities.DiagnosticCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DiagnosticCenterDao extends JpaRepository<DiagnosticCenter,String> {

	DiagnosticCenter save(DiagnosticCenter center);

}
