package com.axon.wickland.units.command

import com.axon.wickland.units.api.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class UnitCommands {

    @AggregateIdentifier
    var unitTypeId: String? =null
    var unitTypeName: String? =null
    var unitOccupation: String? =null

    constructor()

    //Create
    @CommandHandler
    constructor(commands: CreateUnitCommand){
        AggregateLifecycle.apply(UnitCreatedEvent(commands.unitTypeId,
                                                commands.unitTypeName,
                                                commands.unitOccupation))
    }

    @EventSourcingHandler
    fun handler(event: UnitCreatedEvent){
        this.unitTypeId=event.unitTypeId
        this.unitTypeName=event.unitTypeName
        this.unitOccupation=event.unitOccupation

    }

    //Update
    @CommandHandler
    fun handler(command: UpdateUnitCommand){
        AggregateLifecycle.apply(UnitUpdatedEvent(command.unitTypeId,
                                                command.unitTypeName,
                                                command.unitOccupation))
    }

    @EventSourcingHandler
    fun handler(updatedEvent: UnitUpdatedEvent){
        this.unitTypeId=updatedEvent.unitTypeId
        this.unitTypeName=updatedEvent.unitTypeName
        this.unitOccupation=updatedEvent.unitOccupation
    }


    //DELETED Unit
    @CommandHandler
    fun handler(command: DeleteUnitTypeCommand){
        AggregateLifecycle.apply(UnitTypeDeletedEvent(command.unitTypeId))
    }

    @EventSourcingHandler
    fun handler(event: UnitTypeDeletedEvent){

    }
}