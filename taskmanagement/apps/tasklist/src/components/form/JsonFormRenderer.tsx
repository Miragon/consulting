import { Fragment, useState } from "react";
import { Button, Grid } from "@mui/material";
import SendIcon from "@mui/icons-material/Save";
import SaveIcon from "@mui/icons-material/Save";
import { JsonForms } from "@jsonforms/react";
import { tss } from "tss-react/mui";
import { materialCells, materialRenderers } from "@jsonforms/material-renderers";
import { JsonForm } from "../../model";

interface Props {
    form: JsonForm;
    submitEvent: (formData: any) => void;
    updateEvent?: (formData: any) => void;
}

const useStyles = tss.create({
    container: {
        width: "100%",
    },
    title: {
        textAlign: "center",
        padding: "0.25em",
    },
    demoform: {
        margin: "auto",
        padding: "1rem",
    },
    buttonContainer: {
        padding: "1rem",
        display: "flex",
        justifyContent: "center",
        gap: "1rem",
    },
    btn: {
        width: "100%",
        margin: "auto",
        marginTop: "1rem",
    },
});


function JsonFormRenderer(props: Props) {
    const [jsonForm] = useState<JsonForm>(props.form);
    const [formError, setFormError] = useState<boolean>(true);

    const { classes } = useStyles();
    const renderers = [...materialRenderers];

    function handleFormChange({ errors, data }: any) {
        setFormError(errors.length > 0);
        jsonForm.setFormData(data);
    }

    async function update() {
        props.updateEvent?.(jsonForm.getFormData());
    }

    async function submit() {
        props.submitEvent(jsonForm.getFormData());
    }

    return (
        <Fragment>
            <Grid
                container
                justifyContent={"center"}
                spacing={1}
                className={classes.container}
            >
                <Grid item>
                    <div className={classes.demoform}>
                        <JsonForms
                            schema={jsonForm.getSchema()}
                            uischema={jsonForm.getUiSchema()}
                            data={jsonForm.getFormData()}
                            renderers={renderers}
                            cells={materialCells}
                            onChange={({ errors, data }) =>
                                handleFormChange({ errors, data })
                            }
                        />
                    </div>
                    <div className={classes.buttonContainer}>
                        {jsonForm.getUpdatable() && (
                            <Button
                                className={classes.btn}
                                variant="contained"
                                color="secondary"
                                size="large"
                                endIcon={<SaveIcon />}
                                disabled={formError}
                                onClick={update}
                            >
                                Update
                            </Button>
                        )}
                        <Button
                            className={classes.btn}
                            variant="contained"
                            color="primary"
                            size="large"
                            endIcon={<SendIcon />}
                            disabled={formError}
                            onClick={submit}
                        >
                            Submit
                        </Button>
                    </div>
                </Grid>
            </Grid>
        </Fragment>
    );
}

export default JsonFormRenderer;
