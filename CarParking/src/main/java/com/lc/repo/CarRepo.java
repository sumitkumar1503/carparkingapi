package com.lc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lc.entity.CarParking;
@Repository
public interface CarRepo extends JpaRepository<CarParking, Integer> {

	@Query("select s.id from Slot s where s.status='AVAILABLE'")
	public Integer availableslot();
	
	@Query(value ="select * from car_parking cp where cp.slotid = :slotid order by cp.allotmentdate desc LIMIT :limit ",nativeQuery = true)
	public CarParking getParking(@Param("slotid") Integer slotid,@Param("limit")  Integer limit);
	
	@Query(value ="select * from car_parking cp where cp.carnumber = :carnumber order by cp.allotmentdate desc LIMIT :limit ",nativeQuery = true)
	public CarParking getParkingByCarNumber(@Param("carnumber") String carnumber,@Param("limit")  Integer limit);
}
