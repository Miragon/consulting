import { Configuration, ProcessApplication } from "./generated/taskmanager";
import { BASE_URL } from "./common.ts";

export const taskManagerConfig = new Configuration({
    basePath: `${BASE_URL}/taskmanager`,
});

export function setProcessApplications(applications: ProcessApplication[]) {
    processApplications = applications;
}

export function getAllProcessApplications(): ProcessApplication[] {
    return processApplications;
}

export function getMetadataByProcessId(processId: string): ProcessApplication {
    const processApplication = processApplications.find((application) => application.processId === processId);

    if (!processApplication) {
        throw new Error(`Process application with ID ${processId} not found`);
    }

    return processApplication;
}

export enum UrlType {
    PROCESS_START,
    PROCESS_START_FORM,
    LOAD_TASK,
    UPDATE_TASK,
    COMPLETE_TASK,
}

let processApplications: ProcessApplication[] = [];
