import { ChangeEvent, useState } from "react";
import { Checkbox, FormControlLabel, Paper, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";
import Button from "@mui/material/Button";
import { tss } from "tss-react";
import { Order, OrderChecked, PersonalInformationProps } from "../domain";
import { postMessage, TasklistEventType, UserTaskFormProps } from "../tasklist.ts";
import ItemList from "./ItemList.tsx";

interface OrderOverviewProps extends UserTaskFormProps<Order> {
    orderId?: string;
    checkable?: boolean;
    className?: string;
}

const useStyles = tss.create({
    root: {
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        "&>div": {
            margin: "10px",
        },
    },
    h1: {
        fontSize: "2em",
        margin: "10px",
    },
    h2: {
        fontSize: "1.5em",
        margin: 0,
    },
    buttonContainer: {
        display: "flex",
        justifyContent: "center",
    },
});

const OrderOverview = (props: OrderOverviewProps) => {
    const { formData, orderId, checkable, className } = props;

    const { classes, cx } = useStyles();

    const [checked, setChecked] = useState(false);

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setChecked(event.target.checked);
    };

    const handleSubmit = () => {
        const orderChecked = new OrderChecked({
            isOrderValid: checked,
            personalInformation: formData.personalInformation,
            items: formData.items,
        });

        postMessage({
            type: TasklistEventType.SUBMIT_EVENT,
            data: orderChecked,
        });
    };

    const PersonalInformation = (info: PersonalInformationProps) => {
        const name = `${info.firstname} ${info.lastname}`;
        const rows = [
            { label: "Name", value: name },
            { label: "Email", value: info.email },
            { label: "Street", value: info.street },
            { label: "City", value: info.city },
            { label: "Zip", value: info.zip },
        ];

        return (
            <TableContainer component={Paper}>
                <TableBody>
                    {rows.map((row) => (
                        <TableRow
                            key={row.label}
                            sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                        >
                            <TableCell component="th" scope="row">
                                {row.label}
                            </TableCell>
                            <TableCell align="left">{row.value}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </TableContainer>
        );

    };

    return (
        <div className={cx(classes.root, className)}>
            <h1 className={classes.h1}>Order Overview</h1>
            {orderId && (
                <div>
                    <h2 className={classes.h2}>Order ID</h2>
                    <p>{orderId}</p>
                </div>
            )}
            <div>
                <h2 className={classes.h2}>Personal Information</h2>
                <PersonalInformation {...formData.personalInformation.destruct()} />
            </div>
            <div>
                <h2 className={classes.h2}>Items</h2>
                <ItemList items={formData.items} />
            </div>
            {checkable && (
                <FormControlLabel
                    required
                    control={
                        <Checkbox checked={checked} onChange={handleChange} color="success" />
                    }
                    label="Is order valid?"
                />
            )}
            <div className={classes.buttonContainer}>
                {checkable && (
                    <Button onClick={handleSubmit}>Complete</Button>
                )}
            </div>
        </div>
    );
};

export default OrderOverview;