package com.axon.wickland.units.query

import com.axon.wickland.units.api.UnitCreatedEvent
import com.axon.wickland.units.api.UnitDto
import com.axon.wickland.units.api.UnitTypeDeletedEvent
import com.axon.wickland.units.api.UnitUpdatedEvent
import com.axon.wickland.units.util.CreateUnitConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler

class UnitProjection(private val unitRepository: UnitStatesRepository) {


    //save unit record

    @EventHandler
    fun save(event:UnitCreatedEvent){

        val unitState= UnitStates();
        unitState.unitTypeId=event.unitTypeId
        unitState.unitTypeName=event.unitTypeName
        unitState.unitOccupation=event.unitOccupation

        this.unitRepository.save(unitState)
    }

    //update unit record

    @EventHandler
    fun update(updatedEvent: UnitUpdatedEvent){

        val parkState=unitRepository.findById(updatedEvent.unitTypeId).map { r ->
            r.unitTypeId=updatedEvent.unitTypeId
            r
        }
        unitRepository.save(parkState.get())

    }

    //delete unit record

    @EventHandler
    fun deleteUnit(event: UnitTypeDeletedEvent){
        val State=unitRepository.findById(event.unitTypeId)

        if(State.isPresent)
            unitRepository.delete(State.get())
    }

    //find all unit records

    @QueryHandler(queryName =  "findAllUnits")
    fun findAll():List<UnitDto>{
        val units=this.unitRepository.findAll()
        if(!units.isEmpty()){
            val dto=units.map { r ->
                UnitDto(r.unitTypeId, r.unitTypeName, r.unitOccupation)
            }
            return dto
        }else
            return emptyList()
    }

    //find single unit record base unitTypeId

    @QueryHandler(queryName = "findByUnitTypeId")
    fun findById(unitTypeId: String): UnitDto{
        val result=unitRepository.findById(unitTypeId)
        if(result.isPresent)
            return CreateUnitConverter.convertModelToDTO(result.get())

        return UnitDto()
    }

}