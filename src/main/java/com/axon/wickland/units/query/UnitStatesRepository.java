package com.axon.wickland.units.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitStatesRepository extends JpaRepository<UnitStates, String> {

    @Query()
    List<UnitStates> findByUnitTypeId(String unitTypeId);
}
