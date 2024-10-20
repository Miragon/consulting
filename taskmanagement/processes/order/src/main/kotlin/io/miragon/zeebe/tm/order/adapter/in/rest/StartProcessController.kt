package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.libs.shared.utils.Form
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.ItemDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.LoadItemsDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.PlaceOrderDto
import io.miragon.zeebe.tm.order.application.port.`in`.LoadStartEventUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.tasklist.FormDto
import io.miragon.zeebe.tm.tasklist.MessageDto
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/process")
class StartProcessController(
    private val loadFormUseCase: LoadStartEventUseCase,
    private val startProcessUseCase: StartProcessUseCase
)
{
    private val log = KotlinLogging.logger {}

    private val formPath = "/forms/index.html"

    @PostMapping("/start/form")
    fun loadItems(): ResponseEntity<FormDto.HtmlForm<LoadItemsDto>>
    {
        log.info { "Loading form for starting a new process instance." }

        val command = LoadStartEventUseCase.Command(
            filePath = formPath
        )
        val response = loadFormUseCase.load(command)

        val form = Form.createHtmlForm(response.htmlString)

        val formData = LoadItemsDto(
            items = response.formData.map { item ->
                ItemDto(
                    id = item.id,
                    name = item.name ?: throw IllegalArgumentException("Item name must not be null"),
                    price = item.price ?: throw IllegalArgumentException("Item price must not be null"),
                    image = item.image ?: throw IllegalArgumentException("Item image must not be null"),
                )
            }
        )

        return ResponseEntity.ok(
            FormDto.HtmlForm(
                html = form.html,
                updatable = false,
                formData = formData
            )
        )
    }

    @PostMapping("/start")
    fun placeOrder(@RequestBody formData: PlaceOrderDto): ResponseEntity<MessageDto>
    {
        log.info { "Starting new process instance." }

        val command = formData.toCommand()
        val orderId = startProcessUseCase.startProcess(command)
        val response = MessageDto("Order with id $orderId created!")

        return ResponseEntity.ok(response)
    }
}