package org.capg.dao;


import org.capg.entities.TestClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TestDao  extends JpaRepository<TestClass,String>{

	TestClass save(TestClass test);
}
