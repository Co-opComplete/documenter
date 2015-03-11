package com.scuilion.documenter;

import java.util.Map;

public interface Writer {
    void write(Map<String, Note> documents);
}
