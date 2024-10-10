import { ChangeEvent, useState } from "react";
import { Divider, TextField } from "@mui/material";
import Button from "@mui/material/Button";
import { tss } from "tss-react/mui";
import { Item } from "../domain";


const useStyles = tss.create({
    list: {
        listStyleType: "none",
        padding: 0,
        margin: 0,
    },
    item: {
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
        padding: "10px",
        "&>:first-of-type": {
            paddingRight: "10px",
        },
    },
    info: {
        display: "flex",
        flexDirection: "column",
        alignItems: "flex-start",
        "&>p": {
            margin: 0,
        },
    },
    buttonContainer: {
        display: "flex",
        margin: "10px",
        padding: "10px",
    },
    quantityButton: {
        width: "115px",
    },
    imageContainer: {
        width: "150px",
        height: "150px",
    },
    image: {
        width: "100%",
        height: "100%",
        objectFit: "contain",
    },
});

interface ItemListProps {
    items: Item[];
    addButtonClicked?: (item: Item) => void;
    className?: string;
}

const ItemList = (props: ItemListProps) => {
    const { items, className, addButtonClicked } = props;

    const [quantity, setQuantity] = useState(1);

    const { classes, cx } = useStyles();

    const handleClick = (item: Item) => {
        if (addButtonClicked) {
            item.addQuantity(quantity);
            addButtonClicked(item);
        }
    };

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setQuantity(parseInt(event.target.value));
    };

    return (
        <ul className={cx(classes.list, className)}>
            {items.map((item, index) => (
                <li className={classes.item} key={item.id}>
                    <div className={classes.imageContainer}>
                        <img className={classes.image} src={item.image} alt={item.image} />
                    </div>
                    <div className={classes.info}>
                        <p>Name: {item.name}</p>
                        <p>Price: {item.price}</p>
                        {item.quantity && <p>Quantity: {item.quantity}</p>}
                    </div>
                    {addButtonClicked && (
                        <div className={classes.buttonContainer}>
                            <TextField className={classes.quantityButton} label="Quantity" type="number"
                                       onChange={handleChange} />
                            <Button onClick={() => handleClick(item)}>Add</Button>
                        </div>
                    )}
                    {index < items.length - 1 && <Divider />}
                </li>
            ))}
        </ul>
    );
};

export default ItemList;