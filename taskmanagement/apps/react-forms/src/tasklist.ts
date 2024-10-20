import { FormData, UserTaskDto } from "./api";

/**
 * Every component that represents a form for a user task should extend this interface when using custom props.
 */
export interface UserTaskFormProps<T> {
    formData: T;
    updatable: boolean;
}

/**
 * Interface for serializable objects.
 * Only objects that implement this interface can be sent to the tasklist.
 */
export interface Serializable<T extends FormData> {
    serialize: () => T;
}

export enum TasklistEventType {
    FORM_DATA_EVENT = "FormDataEvent",
    UPDATE_EVENT = "UpdateEvent",
    SUBMIT_EVENT = "SubmitEvent",
}

export interface MessageReceiveEvent {
    /**
     * The type of the event.
     */
    type: TasklistEventType;

    /**
     * The BPMN element.
     * Is used to determine the form to display and also holds the process variables if any.
     * @param elementId The ID of the BPMN element.
     * If it is a start event, it will be "StartEvent".
     * @param variables The process variables that are available in the process instance.
     */
    bpmnElement: { elementId: string; variables?: Record<string, any> } | UserTaskDto;

    /**
     * A flag that indicates if the form is updatable or not.
     */
    updatable?: boolean;

    /**
     * The form data that comes from the process application.
     */
    formData?: FormData;
}

/**
 * The message that is sent to the parent window.
 * `formData` is sent to the process application without further processing.
 */
export interface MessagePostEvent {
    type: TasklistEventType;
    data?: Serializable<FormData>;
}

export function postMessage(message: MessagePostEvent) {
    if (import.meta.env.DEV) {
        mockPostMessage(message);
        return;
    }

    window.parent.postMessage(
        {
            type: message.type,
            formData: message.data?.serialize(),
        },
        "*",
    );
}

export function validateReceivedMessage(message: MessageReceiveEvent): boolean {
    const { type, bpmnElement } = message;

    let errorMsg = "";
    if (!type) {
        errorMsg += "Type is missing";
    }
    if (!bpmnElement) {
        errorMsg += "BPMN element is missing";
    }

    if (errorMsg) {
        throw new Error(errorMsg);
    } else {
        return true;
    }
}

function mockPostMessage(message: MessagePostEvent) {
    switch (message.type) {
        case TasklistEventType.FORM_DATA_EVENT:
            console.debug("Ask for data");
            window.dispatchEvent(new MessageEvent("message", {
                data: {
                    type: TasklistEventType.FORM_DATA_EVENT,
                    bpmnElement: {
                        elementId: "StartEvent",
                    },
                    formData: {
                        items: [
                            {
                                id: 1,
                                name: "Item 1",
                                price: 100,
                                image: "https://via.placeholder.com/300",
                            },
                            {
                                id: 2,
                                name: "Item 2",
                                price: 200,
                                image: "https://via.placeholder.com/150",
                            },
                        ],
                    },
                    updatable: false,
                },
            }));
            break;
        case TasklistEventType.UPDATE_EVENT:
            console.debug("Received update event", message.data?.serialize());
            break;
        case TasklistEventType.SUBMIT_EVENT:
            console.debug("Received submit event", message.data?.serialize());
            break;
    }
}