package com.jaydeep.datecac;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class FormInput implements Serializable {
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fromDate;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate toDate;

    @AssertTrue
    public boolean isValidRange() {
        if(null != toDate && null != fromDate) {
            return toDate.compareTo(fromDate) >= 0;
        }
        return true;
    }
}
