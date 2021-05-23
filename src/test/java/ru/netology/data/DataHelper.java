package ru.netology.data;

import lombok.Value;
import lombok.val;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode() {
        val selectCode = "SELECT code FROM auth_codes";
        try (
                val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "user", "pass");
                val codeState = conn.createStatement();
        ) {
            try (val rs = codeState.executeQuery(selectCode)) {
                if (rs.next()) {
                    val code = rs.getString(1);
                    return new VerificationCode(code);
                }
                return null;
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void clearAllInAuthCodes() {
        val code = "DELETE FROM auth_codes";
        val transactions = "DELETE FROM card_transactions";
        val cards = "DELETE FROM cards";
        val users = "DELETE FROM users";

        try (
                val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "user", "pass");
                val stateCode = conn.prepareStatement(code);
                val stateTransactions = conn.prepareStatement(transactions);
                val stateCards = conn.prepareStatement(cards);
                val stateUsers = conn.prepareStatement(users);
        ) {
            stateCode.executeUpdate(code);
            stateTransactions.executeUpdate(transactions);
            stateCards.executeUpdate(cards);
            stateUsers.executeUpdate(users);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static AuthInfo getIncorrectAuthInfo(int index) {
        return new AuthInfo("vasya", "qwerty123" + index);
    }


}

