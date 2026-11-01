import { ChangeEvent, forwardRef, useImperativeHandle, useState } from "react";
import { TextField } from "@mui/material";
import { tss } from "tss-react";
import { PersonalInformation, PersonalInformationProps } from "../domain";

interface PersonalInfoProps {
    className?: string;
}

export interface PersonalInfoRef {
    getPersonalInfo(): PersonalInformation;
}

const useStyles = tss.create({
    root: {
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        margin: "10px",
        "*": {
            marginBottom: "3px",
            width: "100%",
        },
    },
});

const PersonalInfo = forwardRef<PersonalInfoRef, PersonalInfoProps>((props, ref) => {
    const { className } = props;

    const [state, setState] = useState<PersonalInformationProps>({
        firstname: "",
        lastname: "",
        email: "",
        street: "",
        city: "",
        zip: "",
    });

    const { classes, cx } = useStyles();

    const getPersonalInfo = (): PersonalInformation => {
        return new PersonalInformation({
            firstname: state.firstname,
            lastname: state.lastname,
            email: state.email,
            street: state.street,
            city: state.city,
            zip: state.zip,
        });
    };

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setState({
            ...state,
            [event.target.name]: event.target.value,
        });
    };

    useImperativeHandle(ref, () => ({
        getPersonalInfo,
    }));

    return (
        <div className={cx(classes.root, className)}>
            <TextField type="text" name="firstname" onChange={handleChange} label="Firstname" />
            <TextField type="text" name="lastname" onChange={handleChange} label="Lastname" />
            <TextField type="email" name="email" onChange={handleChange} label="E-Mail" />
            <TextField type="text" name="street" onChange={handleChange} label="Street" />
            <TextField type="text" name="city" onChange={handleChange} label="City" />
            <TextField type="text" name="zip" onChange={handleChange} label="ZIP" />
        </div>
    );
});

export default PersonalInfo;