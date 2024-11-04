package io.miragon.zeebe.tm.payment.adapter.out.database

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface InvoiceRepository : JpaRepository<InvoiceEntity, UUID>
