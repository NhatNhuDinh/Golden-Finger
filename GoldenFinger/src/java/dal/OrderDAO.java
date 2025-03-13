package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Order;
import utils.DBConnect;


public class OrderDAO extends DBConnect {

    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private final OrderStatusDAO osd = new OrderStatusDAO();

    public int createOrder(int userId, String fullName, String phone, String address, String comment, Cart cart) {
        if (connection == null) {
            LOGGER.severe("Database connection is null");
            return -1;
        }

        String sql = "INSERT INTO Orders(UserID, FullName, Phone, Address, Comment, TotalAmount) "
                + " VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, userId);
            stm.setString(2, fullName);
            stm.setString(3, phone);
            stm.setString(4, address);
            stm.setString(5, comment);
            stm.setFloat(6, cart.getTotalAmount());
            
            int affectedRows = stm.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet res = stm.getGeneratedKeys()) {
                    if (res.next()) {
                        return res.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating order", e);
        }
        
        return -1;
    }
    

    public List<Order> getAllOrderByUserId(int userId) {
        if (connection == null) {
            LOGGER.severe("Database connection is null");
            return new ArrayList<>();
        }

        List<Order> orderList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Orders WHERE UserId = ?";
        
        try (PreparedStatement stm = connection.prepareStatement(sqlQuery)) {
            stm.setInt(1, userId);
            
            try (ResultSet res = stm.executeQuery()) {
                while (res.next()) {
                    Order order = mapResultSetToOrder(res);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving orders for user", e);
        }
        
        return orderList;
    }

    public Order getOrderById(int orderId) {
        if (connection == null) {
            LOGGER.severe("Database connection is null");
            return null;
        }

        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, orderId);
            
            try (ResultSet res = stm.executeQuery()) {
                if (res.next()) {
                    return mapResultSetToOrder(res);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving order", e);
        }
        
        return null;
    }

    /**
     * Cancels an order by setting its status to 0
     * 
     * @param orderId The order ID to cancel
     * @return true if the order was successfully canceled, false otherwise
     */
    public boolean cancelOrder(int orderId) {
        if (connection == null) {
            LOGGER.severe("Database connection is null");
            return false;
        }

        String sql = "UPDATE Orders SET StatusID = 0 WHERE OrderID = ?";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, orderId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error canceling order with ID: " + orderId, e);
            return false;
        }
    }
    
    private Order mapResultSetToOrder(ResultSet res) throws SQLException {
        Order order = new Order();
        order.setId(res.getInt("OrderID"));
        order.setDate(res.getString("Date"));
        order.setUserId(res.getInt("UserID"));
        order.setStatus(osd.getOrderStatusByOrderId(res.getInt("OrderID")));
        order.setFullName(res.getString("FullName"));
        order.setPhone(res.getString("Phone"));
        order.setAddress(res.getString("Address"));
        order.setComment(res.getString("Comment"));
        order.setTotalAmount(res.getFloat("TotalAmount"));
        return order;
    }
    
    public List<Order> getAllOrders() {
        if (connection == null) {
            LOGGER.severe("Database connection is null");
            return new ArrayList<>();
        }

        List<Order> orderList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Orders ORDER BY Date DESC";
        
        try (PreparedStatement stm = connection.prepareStatement(sqlQuery);
             ResultSet res = stm.executeQuery()) {
            
            while (res.next()) {
                Order order = mapResultSetToOrder(res);
                orderList.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all orders", e);
        }
        
        return orderList;
    }
    
    public boolean updateOrderStatus(int orderId, int statusId) {
        if (connection == null) {
            LOGGER.severe("Database connection is null");
            return false;
        }

        String sql = "UPDATE Orders SET StatusID = ? WHERE OrderID = ?";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, statusId);
            stm.setInt(2, orderId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating status for order ID: " + orderId, e);
            return false;
        }
    }
    
    public int getTotalOrders() {
        if (connection == null) {
            LOGGER.severe("Database connection is null");
            return 0;
        }

        String sql = "SELECT COUNT(*) FROM Orders";
        
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error counting total orders", e);
        }
        
        return 0;
    }
    
    public float getTotalRevenue() {
        if (connection == null) {
            LOGGER.severe("Database connection is null");
            return 0;
        }

        String sql = "SELECT SUM(TotalAmount) FROM Orders WHERE StatusID != 0";
        
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error calculating total revenue", e);
        }
        
        return 0;
    }
}
