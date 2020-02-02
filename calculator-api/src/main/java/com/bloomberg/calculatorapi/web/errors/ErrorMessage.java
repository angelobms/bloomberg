package com.bloomberg.calculatorapi.web.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeName("error")
@JsonPropertyOrder(value = {"type", "title", "detail", "status", "instance", "help" })
@Data
public class ErrorMessage {

    /** A URI identifier that categorizes the error. */
    private String type;

    /** A brief, human-readable message about the error. */
    private String title;

    /** The HTTP response coee (optional). */
    private String status;

    /** A human-readable explanation of the error. */
    private String detail;

    /** A URI that identifies the specific occurrence of the error. */
    private String instance;

    /** A URL that clients can follow to discover more information. */
    private String help;

}
