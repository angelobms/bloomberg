package com.bloomberg.mathexpression.web.errors;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("messageErrorApi")
@Data
public class ErrorMessages {

    private List<ErrorMessage> errors = new ArrayList<>();
}
