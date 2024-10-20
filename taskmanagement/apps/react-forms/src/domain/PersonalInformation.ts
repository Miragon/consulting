export interface PersonalInformationProps {
    firstname: string;
    lastname: string;
    email: string;
    street: string;
    city: string;
    zip: string;
}

export class PersonalInformation {
    private readonly _firstname: string;
    private readonly _lastname: string;
    private readonly _email: string;
    private readonly _street: string;
    private readonly _city: string;
    private readonly _zip: string;

    constructor({ firstname, lastname, email, street, city, zip }: PersonalInformationProps) {
        this._firstname = firstname;
        this._lastname = lastname;
        this._email = email;
        this._street = street;
        this._city = city;
        this._zip = zip;
    }

    get firstname(): string {
        return this._firstname;
    }

    get lastname(): string {
        return this._lastname;
    }

    get email(): string {
        return this._email;
    }

    get street(): string {
        return this._street;
    }

    get city(): string {
        return this._city;
    }

    get zip(): string {
        return this._zip;
    }

    destruct(): PersonalInformationProps {
        return {
            firstname: this._firstname,
            lastname: this._lastname,
            email: this._email,
            street: this._street,
            city: this._city,
            zip: this._zip,
        };
    }

    validate(): boolean {
        return (
            this._firstname.length > 0
            && this._lastname.length > 0
            && this._email.length > 0
            && this._street.length > 0
            && this._city.length > 0
            && this._zip.length > 0
        );
    }
}