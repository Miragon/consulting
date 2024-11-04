import "./App.css";
import { useEffect, useState } from "react";
import { Alert } from "@mui/material";
import { tss } from "tss-react/mui";
import { FormData, LoadItemsDto, LoadOrderDto } from "./api";
import { MessageReceiveEvent, postMessage, TasklistEventType, validateReceivedMessage } from "./tasklist.ts";
import { Item, Order, PersonalInformation } from "./domain";

import OrderStepper from "./components/OrderStepper.tsx";
import OrderOverview from "./components/OrderOverview.tsx";

interface FormProps {
    elementId: string;
    formData?: FormData;
    variables?: Map<string, any>;
    updatable?: boolean;
}

const useStyles = tss.create({
    form: {
        height: "100%",
        display: "flex",
        flexDirection: "column",
        alignItems: "flex-start",
    },
});

function App() {
    const [formProps, setFormProps] = useState<FormProps>();
    const [error, setError] = useState<string | undefined>(undefined);

    const { classes } = useStyles();

    useEffect(() => {
        const handleMessageEvent = (event: MessageEvent<MessageReceiveEvent>) => {
            try {
                validateReceivedMessage(event.data);
            } catch (error) {
                setError((error as Error).message);
                return;
            }

            const { type, bpmnElement, formData, updatable } = event.data;
            const { elementId, variables } = bpmnElement;

            switch (type) {
                case TasklistEventType.FORM_DATA_EVENT: {
                    setFormProps({
                        formData,
                        elementId,
                        variables: variables ? new Map(Object.entries(variables)) : undefined,
                        updatable,
                    });
                    break;
                }
            }
        };

        window.addEventListener("message", handleMessageEvent);
        postMessage({
            type: TasklistEventType.FORM_DATA_EVENT,
        });

        return () => {
            window.removeEventListener("message", handleMessageEvent);
        };
    }, []);

    const Form = (props: FormProps) => {
        const { elementId, variables, formData, updatable } = props;
        switch (elementId) {
            case "StartEvent": {
                const data = formData as LoadItemsDto;
                const items = data.items.map((item) => {
                    return new Item({
                        id: item.id,
                        name: item.name,
                        price: item.price,
                        image: item.image,
                    });
                });
                return (
                    <div className={classes.form}>
                        <OrderStepper items={items} />
                    </div>
                );
            }
            case "CheckOrder": {
                const data = formData as LoadOrderDto;
                const orderId = variables?.get("orderId") as string;
                const order = new Order({
                    personalInformation: new PersonalInformation({
                        firstname: data.firstname,
                        lastname: data.lastname,
                        email: data.email,
                        street: data.street,
                        city: data.city,
                        zip: data.zip,
                    }),
                    items: data.items.map((item) => {
                        return new Item({
                            id: item.id,
                            name: item.name,
                            price: item.price,
                            image: item.image,
                            quantity: item.quantity,
                        });
                    }),
                });
                return (
                    <div className={classes.form}>
                        <OrderOverview
                            orderId={orderId}
                            formData={order}
                            updatable={updatable ?? false}
                            checkable={true}
                        />
                    </div>
                );
            }
            default: {
                return <div>Form not found</div>;
            }
        }
    };

    return (
        <>
            {formProps
                ? <Form elementId={formProps.elementId} variables={formProps.variables} formData={formProps.formData} />
                : { error } ? <Alert severity="error">{error}</Alert> : <div>Loading...</div>
            }
        </>
    );
}

export default App;
