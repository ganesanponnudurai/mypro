package com.client.util;

import java.util.List;

public class ColumnSpecification {

    private List<String> ref;
    private List<String> fieldnames;
    private List<String> fieldlength;
    private List<String> description;
    private List<String> fixedColPos;

    public void setRef(List<String> ref) {
        this.ref = ref;
    }

    public void setFieldlength(List<String> fieldlength) {
        this.fieldlength = fieldlength;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }



    public List<String> getRef() {
        return ref;
    }

    public List<String> getFieldlength() {
        return fieldlength;
    }

    public List<String> getDescription() {
        return description;
    }



    public List<String> getFieldnames() {
        return fieldnames;
    }

    public void setFieldnames(List<String> fieldnames) {
        this.fieldnames = fieldnames;
    }

    public List<String> getFixedColPos() {
        return fixedColPos;
    }

    public void setFixedColPos(List<String> fixedColPos) {
        this.fixedColPos = fixedColPos;
    }

}