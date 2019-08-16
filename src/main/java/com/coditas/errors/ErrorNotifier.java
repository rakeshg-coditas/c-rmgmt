package com.coditas.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorNotifier {

    private List<String> errors = new ArrayList<>();

    public void addErrors(String message) {
        this.errors.add(message);
    }

    public boolean hasError() {
        return !this.errors.isEmpty();
    }

    public String getAllErrors(){
        return errors.stream().collect(Collectors.joining(","));
    }

}
