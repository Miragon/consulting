import { JsonSchema, UISchemaElement } from "@jsonforms/core";

interface JsonFormParameters {
    schema: string;
    uiSchema: string;
    updatable: boolean;
    formData?: any;
}

interface HtmlFormParameters {
    html: string;
    updatable: boolean;
    formData?: any;
}

export interface FormProps {
    type: FormType;
    content: Forms;
}

export enum FormType {
    JSON_FROM = "jsonForm",
    HTML = "htmlForm",
}

export interface Forms {
    getUpdatable(): boolean;

    getFormData(): any;
}

export class JsonForm implements Forms {
    private readonly schema: JsonSchema;

    private readonly uiSchema: UISchemaElement;

    private readonly updatable: boolean;

    private formData: any;

    constructor({ schema, uiSchema, updatable, formData }: JsonFormParameters) {
        this.schema = JSON.parse(schema);
        this.uiSchema = JSON.parse(uiSchema);
        this.updatable = updatable;
        this.formData = formData ?? {};
    }

    getSchema() {
        return this.schema;
    }

    getUiSchema() {
        return this.uiSchema;
    }

    getUpdatable() {
        return this.updatable;
    }

    getFormData() {
        return this.formData;
    }

    setFormData(formData: any) {
        this.formData = formData;
    }
}

export class HtmlForm implements Forms {
    private readonly html: string;

    private readonly updatable: boolean;

    private readonly formData: any;

    constructor({ html, updatable, formData }: HtmlFormParameters) {
        this.html = html;
        this.updatable = updatable;
        this.formData = formData;
    }

    getHtml() {
        return this.html;
    }

    getUpdatable() {
        return this.updatable;
    }

    getFormData() {
        return this.formData;
    }
}

export function getFormType(form: any): FormType {
    if (isJsonForm(form)) {
        return FormType.JSON_FROM;
    } else if (isHtmlForm(form)) {
        return FormType.HTML;
    } else {
        throw new Error("Unknown form type");
    }
}

function isJsonForm(form: any): form is JsonForm {
    return "schema" in form && "uiSchema" in form;
}

function isHtmlForm(form: any): form is HtmlForm {
    return "html" in form;
}
