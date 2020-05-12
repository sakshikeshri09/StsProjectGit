package org.capg.dao;

import org.capg.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TestDao  extends JpaRepository<Test,String>{

	
}
