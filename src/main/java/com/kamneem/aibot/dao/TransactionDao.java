package com.kamneem.aibot.dao;

import com.kamneem.aibot.BotConfiguration;
import com.kamneem.aibot.model.QueryRequest;
import com.kamneem.aibot.model.TransactionResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    private final BotConfiguration configuration;

    public TransactionDao(BotConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<TransactionResponse> searchTransactions(QueryRequest request) {

        StringBuilder query = new StringBuilder("SELECT * FROM transactions WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (request.getTransactionId() != null) {
            query.append(" AND txn_id = ?");
            params.add(request.getTransactionId());
        }

        if (request.getUserId() != null) {
            query.append(" AND user_id = ?");
            params.add(request.getUserId());
        }

        if (request.getPaymentMode() != null) {
            query.append(" AND payment_mode = ?");
            params.add(request.getPaymentMode());
        }

        if (request.getStatus() != null) {
            query.append(" AND status = ?");
            params.add(request.getStatus());
        }

        try (
                Connection connection = DriverManager.getConnection(configuration.getDatabase().getUrl(),
                        configuration.getDatabase().getUser(), configuration.getDatabase().getPassword());
                PreparedStatement stmt = connection.prepareStatement(query.toString())
        ) {

            // Dynamically set params
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            List<TransactionResponse> responses = new ArrayList<>();
            while (rs.next()) {
                responses.add(
                        TransactionResponse.builder()
                                .txnId(rs.getString("txn_id"))
                                .userId(rs.getString("user_id"))
                                .status(rs.getString("status"))
                                .paymentMode(rs.getString("payment_mode"))
                                .amount(rs.getInt("amount"))
                                .build()
                );
            }
            return responses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}