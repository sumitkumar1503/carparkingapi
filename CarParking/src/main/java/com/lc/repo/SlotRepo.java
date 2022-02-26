package com.lc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lc.entity.Slot;
@Repository
public interface SlotRepo extends JpaRepository<Slot, Integer> {

}
