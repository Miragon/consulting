import { Fragment, useEffect, useRef, useState } from "react";
import { Snackbar, SnackbarProps } from "@mui/material";
import { tss } from "tss-react/mui";
import { AxiosRequestConfig } from "axios";
import { ProcessApplication } from "../../client/generated/taskmanager";
import { HtmlForm as HtmlFormDto } from "../../client/generated/api/model/htmlForm.ts";
import { JsonForm as JsonFormDto } from "../../client/generated/api/model/jsonForm.ts";
import { StartProcessControllerApi } from "../../client/process";
import { FormProps, FormType, getFormType, HtmlForm, JsonForm } from "../../model";
import { getAllProcessApplications, UrlType } from "../../client/taskmanager.ts";
import { getUrlByType } from "../../client/process/common.ts";
import ProcessStartButton from "./ProcessStartButton.tsx";
import JsonFormRenderer from "../form/JsonFormRenderer.tsx";
import HtmlFormRenderer from "../form/HtmlFormRenderer.tsx";

const useStyles = tss.create({
    processContainer: {
        display: "grid",
        gridTemplateColumns: "1fr 1fr",
        gap: "1rem",
    },
    processDefinitionList: {
        margin: "0",
        padding: "0",
        listStyle: "none",
    },
});

function ProcessList() {
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [processId, setProcessId] = useState<string>("");
    const [processApplications, setProcessApplications] = useState<ProcessApplication[]>([]);
    const [form, setForm] = useState<FormProps | null>(null);
    const [snackbarProps, setSnackbarProps] = useState<SnackbarProps>({
        open: false,
        message: "",
    });

    const initialized = useRef(false);

    const { classes } = useStyles();

    useEffect(() => {
        if (!initialized.current) {
            initialized.current = true;
            setProcessApplications(getAllProcessApplications);
            setIsLoading(false);
        }
    }, [initialized]);

    const getForm = async (id: string) => {
        setProcessId(id);

        const api = new StartProcessControllerApi();
        const config: AxiosRequestConfig = {
            url: getUrlByType(UrlType.PROCESS_START_FORM, id),
        };

        try {
            const response = await api.loadForm(undefined, config);
            const form = response.data;

            switch (getFormType(form)) {
                case FormType.JSON_FROM: {
                    const jsonForm = form as JsonFormDto;
                    setForm({
                        type: FormType.JSON_FROM,
                        content: new JsonForm({ ...jsonForm }),
                    });
                    return;
                }
                case FormType.HTML: {
                    const htmlForm = form as HtmlFormDto;
                    setForm({
                        type: FormType.HTML,
                        content: new HtmlForm({ ...htmlForm }),
                    });
                    return;
                }
            }
        } catch (error) {
            console.info("No form available. Trying to start process without form.");
            submit({});
        }
    };

    const submit = async (data: any) => {
        console.debug("Submitting form data:", data);
        const api = new StartProcessControllerApi();
        const config: AxiosRequestConfig = {
            url: getUrlByType(UrlType.PROCESS_START, processId),
        };

        try {
            const response = await api.startProcess(data, config);
            const message = response.data.message;
            setSnackbarProps({
                open: true,
                message: message.length > 0 ? message : "Process started successfully",
            });

        } catch (error) {
            console.error("Failed to start process:", error);
        }
    };

    function ProcessList() {
        if (isLoading) {
            return <p>Loading...</p>;
        } else {
            const buttons = processApplications.map((process) => {
                if (process.startable) {
                    return (
                        <ProcessStartButton
                            key={process.processId}
                            id={process.processId}
                            label={process.label}
                            onClick={getForm}
                        />
                    );
                } else {
                    return null;
                }
            });

            return <ul className={classes.processDefinitionList}>{buttons}</ul>;
        }
    }

    return (
        <Fragment>
            <div className={classes.processContainer}>
                <ProcessList />
                {form?.type === "jsonForm" && (
                    <JsonFormRenderer
                        form={form.content as JsonForm}
                        submitEvent={submit}
                    />
                )}
                {form?.type === "htmlForm" && (
                    <HtmlFormRenderer
                        form={form.content as HtmlForm}
                        bpmnElement={{ elementId: "StartEvent" }}
                        submitEvent={submit}
                    />
                )}
            </div>
            <Snackbar
                anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
                message={snackbarProps.message}
                open={snackbarProps.open}
            />
        </Fragment>
    );
}

export default ProcessList;
