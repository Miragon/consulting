import { PersonalInformation } from "./PersonalInformation.ts";
import { Item } from "./Item.ts";
import { Serializable } from "../tasklist.ts";
import { CheckOrderDto, PlaceOrderDto } from "../api";

interface OrderProps {
    personalInformation: PersonalInformation;
    items: Item[];
}

type ReturnValues = PlaceOrderDto | CheckOrderDto;

abstract class OrderBase<T extends ReturnValues> implements Serializable<T> {
    private readonly _personalInformation: PersonalInformation;

    protected constructor({ personalInformation, items }: OrderProps) {
        this._personalInformation = personalInformation;
        this._items = items;
    }

    private _items: Item[];

    get items(): Item[] {
        return this._items;
    }

    set items(items: Item[]) {
        this._items = items;
    }

    get personalInformation(): PersonalInformation {
        return this._personalInformation;
    }

    abstract serialize(): T
}

export class Order extends OrderBase<PlaceOrderDto> {

    constructor({ personalInformation, items }: OrderProps) {
        super({ personalInformation, items });
    }

    override serialize(): PlaceOrderDto {
        return {
            firstname: super.personalInformation.firstname,
            lastname: super.personalInformation.lastname,
            email: super.personalInformation.email,
            street: super.personalInformation.street,
            city: super.personalInformation.city,
            zip: super.personalInformation.zip,
            items: super.items.map(item => ({
                id: item.id,
                quantity: item.quantity ?? (() => {
                    throw new Error("Quantity is not set");
                })(),
            })),
        };
    }
}

export class OrderChecked extends OrderBase<CheckOrderDto> {
    private readonly _isOrderValid: boolean;

    constructor({ isOrderValid, personalInformation, items }: OrderProps & { isOrderValid: boolean }) {
        super({ personalInformation, items });
        this._isOrderValid = isOrderValid;
    }

    get isOrderValid(): boolean {
        return this._isOrderValid;
    }

    override serialize(): CheckOrderDto {
        return {
            isOrderValid: this.isOrderValid,
        };
    }
}