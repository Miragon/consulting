import { forwardRef, useImperativeHandle, useState } from "react";
import { tss } from "tss-react/mui";
import { Item } from "../domain";

import ItemList from "./ItemList.tsx";

interface ItemSelectionProps {
    itemsProp: Item[];
    className?: string;
}

export interface ItemSelectionRef {
    getSelectedItems(): Item[];
}

const useStyles = tss.create({
    root: {
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
    },
});

const ItemSelection = forwardRef<ItemSelectionRef, ItemSelectionProps>((props, ref) => {
    const { itemsProp, className } = props;

    const [items] = useState<Item[]>(itemsProp); // itemsProp.map((item) => new Item(item)));
    const [cart, setCart] = useState<Item[]>([]);

    const { classes, cx } = useStyles();

    const getSelectedItems = (): Item[] => {
        return cart;
    };

    useImperativeHandle(ref, () => ({
        getSelectedItems: getSelectedItems,
    }));

    const addToCart = (item: Item) => {
        const cartItem = cart.find((cartItem) => cartItem.id === item.id);
        if (cartItem) {
            setCart([...cart]);
        } else {
            setCart([...cart, item]);
        }
    };

    return (
        <div className={cx(classes.root, className)}>
            <ItemList items={items} addButtonClicked={addToCart} />
        </div>
    );
});

export default ItemSelection;