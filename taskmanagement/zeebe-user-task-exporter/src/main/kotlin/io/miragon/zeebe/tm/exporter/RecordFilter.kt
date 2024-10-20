package io.miragon.zeebe.tm.exporter

import io.camunda.zeebe.exporter.api.context.Context
import io.camunda.zeebe.protocol.record.RecordType
import io.camunda.zeebe.protocol.record.ValueType

class RecordFilter : Context.RecordFilter
{
    override fun acceptType(recordType: RecordType?): Boolean
    {
        return recordType == RecordType.EVENT
    }

    override fun acceptValue(valueType: ValueType?): Boolean
    {
        return valueType == ValueType.USER_TASK || valueType == ValueType.VARIABLE
    }
}