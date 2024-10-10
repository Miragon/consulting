import { MouseEvent, useState } from "react";
import { Accordion, AccordionDetails, AccordionSummary, Typography } from "@mui/material";
import { ExpandMore } from "@mui/icons-material";
import ReactJson from "react-json-view";
import { tss } from "tss-react/mui";
import { UserTaskDto } from "../../client/generated/taskmanager";

const useStyles = tss.create({
    task: {
        margin: "1rem",
        cursor: "pointer",
    },
});

interface Props {
    key: number;
    task: UserTaskDto;
    event: (task: UserTaskDto) => void;
}

function Task(props: Props) {
    const [expand, setExpand] = useState(false);

    const { task, event } = props;
    const { bpmnProcessId, elementId, key, processInstanceKey, variables } = task;

    const { classes } = useStyles();

    const toggleAccordion = (event: MouseEvent<SVGSVGElement>) => {
        setExpand(!expand);
        event.stopPropagation();
    };

    return (
        <li className={classes.task}>
            <Accordion expanded={expand}>
                <AccordionSummary
                    expandIcon={<ExpandMore onClick={(e) => toggleAccordion(e)} />}
                    aria-controls={`${key}-content`}
                    onClick={() => event(task)}
                >
                    <Typography style={{ wordWrap: "break-word" }}>
                        {elementId} ({key})<br />
                        {bpmnProcessId} ({processInstanceKey})
                    </Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <ReactJson src={variables} name={false} displayDataTypes={false} sortKeys={true} />
                </AccordionDetails>
            </Accordion>
        </li>
    );
}

export default Task;
