package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.TaskManagerPort
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Service

@Service
class CompleteCheckOrderTaskService(
    private val orderPersistencePort: OrderPersistencePort,
    private val taskManagerPort: TaskManagerPort,
) : CompleteCheckOrderTaskUseCase
{
    override fun complete(command: Command): Long
    {
        val (taskId, orderId, isAccepted) = command

        val order = orderPersistencePort.findById(orderId)
        order.state = if (isAccepted) Order.State.CHECKED else Order.State.CANCELLED

        // Update the order in the database
        orderPersistencePort.update(orderId, order)

        // Complete the task in the task manager
        taskManagerPort.completeCheckOrderTask(taskId, isAccepted)

        return taskId
    }
}