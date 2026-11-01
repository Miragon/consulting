package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareDeliveryTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareDeliveryTaskUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.TaskManagerPort
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class CompletePrepareDeliveryTaskService(
    private val orderPersistencePort: OrderPersistencePort,
    private val taskManagerPort: TaskManagerPort,
) : CompletePrepareDeliveryTaskUseCase
{
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(java.util.Locale.GERMANY)

    override fun complete(command: Command): Long
    {
        val (taskId, orderId, deliveryDate, modeOfDispatch, items) = command

        items.forEach { item ->
            if (item.ready != true)
            {
                throw IllegalStateException("Item ${item.id} is not ready for delivery!")
            }
        }

        // Update the order in the database
        val order = orderPersistencePort.findById(orderId)
        order.deliveryDate = LocalDate.parse(deliveryDate, formatter)
        order.modeOfDispatch = modeOfDispatch
        order.state = Order.State.COMPLETE
        order.items = items
        orderPersistencePort.update(orderId, order)

        // Complete the task in the task manager
        taskManagerPort.completePrepareDeliveryTask(taskId)

        return taskId
    }

    override fun update(command: Command): Long
    {
        val (taskId, orderId, deliveryDate, modeOfDispatch, items) = command

        val order = orderPersistencePort.findById(orderId)
        order.state = Order.State.PREPARING_DELIVERY
        order.deliveryDate = LocalDate.parse(deliveryDate, formatter)
        order.modeOfDispatch = modeOfDispatch
        order.items = items
        orderPersistencePort.update(orderId, order)
        return taskId
    }
}
