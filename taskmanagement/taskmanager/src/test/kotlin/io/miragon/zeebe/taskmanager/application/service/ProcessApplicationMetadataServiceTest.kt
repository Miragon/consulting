package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.domain.ProcessApplicationMetadata
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [MetadataService::class])
@EnableConfigurationProperties(value = [ProcessApplicationMetadata::class])
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
class ProcessApplicationMetadataServiceTest
{
    @Autowired
    private lateinit var metadataService: MetadataService

    @Test
    fun getMetadata()
    {
        val processApplications = metadataService.load().processApplications

        // order-process
        val orderProcess = processApplications.find { it.processId == "order-process" }
        assert(orderProcess?.label == "Order Process")
        assert(orderProcess?.startable == false)
        assert(orderProcess?.startProcessUrl == "http://localhost:9101/rest/placeOrder")
        assert(orderProcess?.startProcessFormUrl == "http://localhost:9101/rest/orderForm")
        assert(orderProcess?.loadTaskUrl == "http://localhost:9101/rest/task/load")
        assert(orderProcess?.completeTaskUrl == "http://localhost:9101/rest/task/complete")
        assert(orderProcess?.updateTaskUrl == "http://localhost:9101/rest/task/update")

        // payment-process
        val paymentProcess = processApplications.find { it.processId == "payment-process" }
        assert(paymentProcess?.label == "Payment Process")
        assert(paymentProcess?.startable == true)
        assert(paymentProcess?.startProcessUrl == "http://localhost:9102/rest/process/start")
        assert(paymentProcess?.startProcessFormUrl == "http://localhost:9102/rest/process/start/form")
        assert(paymentProcess?.loadTaskUrl == "http://localhost:9102/rest/loadPayment")
        assert(paymentProcess?.completeTaskUrl == "http://localhost:9102/rest/completePayment")
        assert(paymentProcess?.updateTaskUrl == "http://localhost:9102/rest/updatePayment")
    }
}