import { CheckOrderDto } from "./generated/model/checkOrderDto.ts";
import { LoadOrderDto } from "./generated/model/loadOrderDto.ts";
import { LoadItemsDto } from "./generated/model/loadItemsDto.ts";
import { PlaceOrderDto } from "./generated/model/placeOrderDto.ts";

// Process Start Event
export * from "./generated/model/loadItemsDto.ts";
export * from "./generated/model/itemDto.ts";
export * from "./generated/model/placeOrderDto.ts";
export * from "./generated/model/placeOrderItemDto.ts";

// Check Order Task
export * from "./generated/model/userTaskDto.ts";
export * from "./generated/model/loadOrderDto.ts";
export * from "./generated/model/checkOrderItemDto.ts";
export * from "./generated/model/checkOrderDto.ts";

export type FormData = LoadOrderDto
    | CheckOrderDto
    | LoadItemsDto
    | PlaceOrderDto
