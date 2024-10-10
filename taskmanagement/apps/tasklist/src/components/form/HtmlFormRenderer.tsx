import { useEffect, useRef } from "react";
import { tss } from "tss-react/mui";
import { HtmlForm } from "../../model";
import { UserTaskDto } from "../../client/generated/taskmanager";

const useStyles = tss.create({
    container: {
        position: "relative",
        width: "100%",
        height: "100vh",
        overflow: "hidden",
    },
    iframe: {
        width: "100%",
        height: "100%",
        border: "none",
    },
    fullScreen: {
        position: "absolute",
        top: "0",
        right: "0",
    },
});

interface Props {
    form: HtmlForm;
    bpmnElement: { elementId: string; variables?: { [key: string]: any } } | UserTaskDto | undefined;
    submitEvent: (formData: any) => void;
    updateEvent?: (formData: any) => void;
}

interface IFrameEvent {
    type: IFrameEventType;
    formData: any;
}

enum IFrameEventType {
    FORM_DATA_EVENT = "FormDataEvent",
    UPDATE_EVENT = "UpdateEvent",
    SUBMIT_EVENT = "SubmitEvent",
}

function HtmlFormRenderer(props: Props) {
    const { form, bpmnElement, submitEvent, updateEvent } = props;

    const iframeRef = useRef<HTMLIFrameElement>(null);

    const { classes } = useStyles();

    useEffect(() => {
        const handleMessageEvent = (event: MessageEvent<IFrameEvent>) => {
            const { type, formData } = event.data;

            switch (type) {
                case IFrameEventType.FORM_DATA_EVENT: {
                    iframeRef.current?.contentWindow?.postMessage(
                        {
                            type: IFrameEventType.FORM_DATA_EVENT,
                            bpmnElement,
                            formData: form.getFormData(),
                            updatable: form.getUpdatable(),
                        },
                        "*",
                    );
                    break;
                }
                case IFrameEventType.UPDATE_EVENT: {
                    updateEvent?.(formData);
                    break;
                }
                case IFrameEventType.SUBMIT_EVENT: {
                    submitEvent(formData);
                    break;
                }
            }
        };

        window.addEventListener("message", handleMessageEvent);

        if (!bpmnElement) {
            console.error("BpmnElement is undefined. This may cause problems with your form.");
        }

        return () => {
            window.removeEventListener("message", handleMessageEvent);
        };
    }, []);

    return (
        <div className={classes.container}>
            <iframe className={classes.iframe} srcDoc={form.getHtml()}
                    ref={iframeRef} />
        </div>
    );
}

export default HtmlFormRenderer;
