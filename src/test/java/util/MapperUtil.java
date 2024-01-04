package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.transform.Result;

public class MapperUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Result deserializeResponse(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Result.class);
    }

}