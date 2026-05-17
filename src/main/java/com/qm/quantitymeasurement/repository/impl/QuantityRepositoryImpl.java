package com.qm.quantitymeasurement.repository.impl;

import com.qm.quantitymeasurement.config.DatabaseConfig;
import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;
import com.qm.quantitymeasurement.repository.QuantityRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuantityRepositoryImpl implements QuantityRepository {
    private static final String INSERT_QUERY =
            """
            INSERT INTO quantity_operations
            (
                first_quantity_value,
                first_unit,
                second_quantity_value,
                second_unit,
                operation_type,
                result_quantity_value,
                result_unit
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    @Override
    public void saveOperation(
            QuantityRequestDto first,
            QuantityRequestDto second,
            String operationType,
            QuantityResponseDto result
    ) {

        try (
                Connection connection =
                        DatabaseConfig
                                .getDataSource()
                                .getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                INSERT_QUERY
                        )
        ) {

            statement.setDouble(
                    1,
                    first.getValue()
            );

            statement.setString(
                    2,
                    first.getUnit()
            );

            statement.setDouble(
                    3,
                    second.getValue()
            );

            statement.setString(
                    4,
                    second.getUnit()
            );

            statement.setString(
                    5,
                    operationType
            );

            statement.setDouble(
                    6,
                    result.getValue()
            );

            statement.setString(
                    7,
                    result.getUnit()
            );

            statement.executeUpdate();

        } catch (Exception exception) {

            throw new RuntimeException(
                    "Failed to save operation",
                    exception
            );
        }
    }
}
