interface ItemProps {
    id: string;
    name?: string;
    price?: number;
    image?: string;
    quantity?: number;
}

export class Item {
    private readonly _id: string;
    private readonly _name?: string;
    private readonly _price?: number;
    private readonly _image?: string;

    constructor({ id, name, price, image, quantity }: ItemProps) {
        this._id = id;
        this._name = name;
        this._price = price;
        this._image = image;
        this._quantity = quantity ?? 0;
    }

    private _quantity: number;

    get quantity(): number {
        return this._quantity;
    }

    get id(): string {
        return this._id;
    }

    get name(): string {
        if (!this._name) {
            throw new Error("Name is not set");
        }
        return this._name;
    }

    get price(): string {
        if (!this._price) {
            throw new Error("Price is not set");
        }
        return `${this._price} €`;
    }

    get image(): string {
        if (!this._image) {
            throw new Error("Image is not set");
        }
        return this._image;
    }

    addQuantity(quantity: number): void {
        this._quantity += quantity;
    }
}