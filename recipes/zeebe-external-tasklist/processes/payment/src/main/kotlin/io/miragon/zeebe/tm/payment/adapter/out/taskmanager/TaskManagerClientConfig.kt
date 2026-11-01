package io.miragon.zeebe.tm.payment.adapter.out.taskmanager

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestClient

@Configuration
class TaskManagerClientConfig
{
    @Value("\${payment.tm.base-url}")
    lateinit var baseUrl: String

    @Bean
    fun restClient(): RestClient
    {
        return RestClient.builder()
            .baseUrl(baseUrl)
            .messageConverters { it.add(MappingJackson2HttpMessageConverter()) }
            .build()
    }
}
