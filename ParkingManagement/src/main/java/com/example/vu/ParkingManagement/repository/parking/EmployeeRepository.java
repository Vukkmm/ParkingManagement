package com.example.vu.ParkingManagement.repository.parking;

import com.example.vu.ParkingManagement.entity.parking.Employee;
import com.example.vu.ParkingManagement.repository.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {

    Employee findByEmployeeCode(String employeeCode);

    @Query("""
        SELECT CASE WHEN COUNT(r) > 0
        THEN true ELSE false END FROM Employee r
        WHERE r.employeeCode = :employeeCode
        """)
    boolean checkEmployeeCodeExist(String employeeCode);

    @Query("""
    SELECT e.employeeCode
    FROM Employee e
    ORDER BY e.employeeCode DESC
    """)
    List<String> findLatestEmployeeCode(Pageable pageable);

}
