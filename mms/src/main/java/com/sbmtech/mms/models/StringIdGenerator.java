package com.sbmtech.mms.models;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StringIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "INV";
        int paddingLength = 3;

        try {
            Connection connection = session.connection();

            // Lock the row to avoid race conditions
            PreparedStatement selectStmt = connection.prepareStatement(
                "SELECT last_number FROM id_sequence WHERE prefix = ? FOR UPDATE"
            );
            selectStmt.setString(1, prefix);
            ResultSet rs = selectStmt.executeQuery();

            int lastNumber = 0;
            if (rs.next()) {
                lastNumber = rs.getInt("last_number");
            }

            int newNumber = lastNumber + 1;

            // Update the last number
            PreparedStatement updateStmt = connection.prepareStatement(
                "UPDATE id_sequence SET last_number = ? WHERE prefix = ?"
            );
            updateStmt.setInt(1, newNumber);
            updateStmt.setString(2, prefix);
            updateStmt.executeUpdate();

            // Format the new ID
            return prefix + String.format("%0" + paddingLength + "d", newNumber);

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate ID", e);
        }
    }
}