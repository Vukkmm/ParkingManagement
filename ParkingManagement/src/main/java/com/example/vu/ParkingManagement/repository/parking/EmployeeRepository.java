package com.example.vu.ParkingManagement.repository.parking;

import com.example.vu.ParkingManagement.entity.parking.Employee;
import com.example.vu.ParkingManagement.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {

    Employee findByEmployeeCode(String employeeCode);

    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 
        THEN true ELSE false END FROM Employee r
        WHERE r.employeeCode = :employeeCode
        """)
    boolean checkEmployeeCodeExist(String employeeCode);
}
