package dao;

import dao.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Category;


public class CategoryDAO {

    private static final String GET_CATEGORIES = "SELECT cateId, cateName FROM Categories";
    private static final String UPDATE_CATEGORY_INFO = "UPDATE Categories SET cateName = ? WHERE cateId = ?";
    private static final String INSERT_NEW_CATEGORY = "INSERT INTO Categories (cateName) VALUES (?)";

    public boolean insertNewCategory(String cateName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(INSERT_NEW_CATEGORY);
                psm.setString(1, cateName);
                check = psm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateCategoryInfo(int cateId, String cateName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(UPDATE_CATEGORY_INFO);
                psm.setString(1, cateName);
                psm.setInt(2, cateId);
                check = psm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public Map<Integer, String> getCategories() throws SQLException {
        Map<Integer, String> list = new LinkedHashMap<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(GET_CATEGORIES);
                rs = psm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int cateId = rs.getInt("cateId");
                        String cateName = rs.getString("cateName");
                        list.put(cateId, cateName);
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public List<Category> getCategorySell() {
        List<Category> categories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(GET_SELL_CATEGORY);
                rs = psm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Category category = new Category();
                        int cateId = rs.getInt("cateId");
                        String cateName = rs.getString("cateName");
                        int total = rs.getInt("total");
                        category.setCateId(cateId);
                        category.setCateName(cateName);
                        category.setTotal(total);
                        categories.add(category);

                    }
                }
            }
        } catch (Exception e) {

        }
        return categories;
    }
    private static final String GET_SELL_CATEGORY = "SELECT TOP 3 cateName, SUM(quantity) as \"total\", c.cateId FROM OrderDetails od\n"
            + "INNER JOIN Plants p ON od.pid = p.pid \n"
            + "INNER JOIN Categories c on c.cateid= p.cateid\n"
            + "GROUP BY c.cateId, cateName\n"
            + "ORDER BY total DESC";
    
    public static void main(String[] args) {
        CategoryDAO list= new CategoryDAO();
        List<Category> a= list.getCategorySell();
        for (Category category : a) {
            System.out.println(category.getCateId()+","+category.getCateName()+":"+category.getTotal());
        }
        
                
    }
}
