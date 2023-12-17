package com.example.demo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {
    public List<Wineries> readWineryData(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Wineries> wineries = objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<Wineries>>() {});
        return wineries;
    }
}
