package com.elitsoft.servicampo.handler;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoTipoComponenteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Custom MyBatis TypeHandler to convert between PostgreSQL JSON arrays and Java Lists
 */
@MappedTypes(TipoProductoTipoComponenteDTO.class)
public class TipoProductoTipoComponenteListTypeHandler extends BaseTypeHandler<List<TipoProductoTipoComponenteDTO>> {

    private static final Logger logger = LoggerFactory.getLogger(TipoProductoTipoComponenteListTypeHandler.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final JavaType javaType = objectMapper.getTypeFactory()
            .constructCollectionType(List.class, TipoProductoTipoComponenteDTO.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<TipoProductoTipoComponenteDTO> parameter, JdbcType jdbcType)
            throws SQLException {
        try {
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i, json);
        } catch (JsonProcessingException e) {
            logger.error("Error converting List to JSON", e);
            throw new SQLException("Error converting List to JSON", e);
        }
    }

    @Override
    public List<TipoProductoTipoComponenteDTO> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonString = rs.getString(columnName);
        logger.debug("getNullableResult by column name: {}, value: {}", columnName, jsonString);
        return parseJson(jsonString);
    }

    @Override
    public List<TipoProductoTipoComponenteDTO> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonString = rs.getString(columnIndex);
        logger.debug("getNullableResult by index: {}, value: {}", columnIndex, jsonString);
        return parseJson(jsonString);
    }

    @Override
    public List<TipoProductoTipoComponenteDTO> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonString = cs.getString(columnIndex);
        logger.debug("getNullableResult (CallableStatement): {}, value: {}", columnIndex, jsonString);
        return parseJson(jsonString);
    }

    private List<TipoProductoTipoComponenteDTO> parseJson(String json) {
        if (json == null || json.isEmpty() || json.equals("null")) {
            logger.debug("Empty JSON string, returning empty list");
            return Collections.emptyList();
        }

        if (json.equals("[]")) {
            logger.debug("Empty JSON array, returning empty list");
            return Collections.emptyList();
        }

        try {
            logger.debug("Parsing JSON: {}", json);
            List<TipoProductoTipoComponenteDTO> result = objectMapper.readValue(json, javaType);
            logger.debug("Parsed JSON to {} items", result.size());
            return result;
        } catch (JsonProcessingException e) {
            logger.error("Error parsing JSON: {}", json, e);
            return Collections.emptyList();
        }
    }
} 