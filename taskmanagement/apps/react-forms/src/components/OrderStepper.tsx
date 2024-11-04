import * as React from "react";
import Box from "@mui/material/Box";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import { postMessage, TasklistEventType } from "../tasklist.ts";
import { Item, Order } from "../domain";

import ItemSelection, { ItemSelectionRef } from "./ItemSelection.tsx";
import PersonalInfo, { PersonalInfoRef } from "./PersonalInfo.tsx";
import OrderOverview from "./OrderOverview.tsx";
import { Typography } from "@mui/material";

const steps = ["Select items", "Enter address", "Finish"];

interface OrderStepperProps {
    items: Item[];
}

export default function OrderStepper(props: OrderStepperProps) {
    const [activeStep, setActiveStep] = React.useState(0);
    const [items, setItems] = React.useState<Item[]>([]);
    const [order, setOrder] = React.useState<Order | null>(null);

    const itemsRef = React.useRef<ItemSelectionRef>(null);
    const personalInfoRef = React.useRef<PersonalInfoRef>(null);

    const handleNext = () => {
        switch (activeStep) {
            case 0: {
                const selectedItems = itemsRef.current?.getSelectedItems();

                if (!(selectedItems && selectedItems.length > 0)) {
                    return;
                }

                setItems(selectedItems);
                break;
            }
            case 1: {
                const personalInfo = personalInfoRef.current?.getPersonalInfo();

                if (!(personalInfo?.validate())) {
                    return;
                }

                const order = new Order({
                    personalInformation: personalInfo,
                    items: items,
                });

                setOrder(order);

                break;
            }
            case steps.length - 1: {
                if (!order) {
                    console.error("Order is not set");
                    return;
                }

                postMessage({
                    type: TasklistEventType.SUBMIT_EVENT,
                    data: order,
                });
            }
        }

        setActiveStep((prevActiveStep) => prevActiveStep + 1);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    return (
        <Box sx={{ width: "100%" }}>
            <Stepper activeStep={activeStep}>
                {steps.map((label) => {
                    const stepProps: { completed?: boolean } = {};
                    const labelProps: {
                        optional?: React.ReactNode;
                    } = {};
                    return (
                        <Step key={label} {...stepProps}>
                            <StepLabel {...labelProps}>{label}</StepLabel>
                        </Step>
                    );
                })}
            </Stepper>
            {activeStep === steps.length ? (
                <React.Fragment>
                    <Typography sx={{ mt: 2, mb: 1 }}>
                        Thank you for your order.
                    </Typography>
                </React.Fragment>
            ) : (
                <React.Fragment>
                    {activeStep === 0 && (
                        <ItemSelection ref={itemsRef} itemsProp={props.items} />
                    )}
                    {activeStep === 1 && (
                        <PersonalInfo ref={personalInfoRef} />
                    )}
                    {activeStep === steps.length - 1 && order && (
                        <OrderOverview formData={order} updatable={false} />
                    )}
                    <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
                        <Button
                            color="inherit"
                            disabled={activeStep === 0}
                            onClick={handleBack}
                            sx={{ mr: 1 }}
                        >
                            Back
                        </Button>
                        <Box sx={{ flex: "1 1 auto" }} />
                        <Button onClick={handleNext}>
                            {activeStep === steps.length - 1 ? "Finish" : "Next"}
                        </Button>
                    </Box>
                </React.Fragment>
            )}
        </Box>
    );
}