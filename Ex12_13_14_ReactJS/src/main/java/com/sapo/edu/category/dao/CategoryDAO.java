package com.sapo.edu.category.dao;

import com.sapo.edu.category.Interface.CategoryDAOInterface;
import com.sapo.edu.category.model.Category;
import com.sapo.edu.category.rowMapper.CategoryMapper;
import com.sapo.edu.product.interfaceProduct.ProductDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("CategoryDAO")

public class CategoryDAO implements CategoryDAOInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductDAOInterface productDAOInterface;

    /**
     * Tìm tất cả các Category và Product thuộc Category đó
     *
     * @return
     */
    @Override
    public List<Category> fillAllCategory() {
        List<Category> categoryList = jdbcTemplate.query("select * from category", new CategoryMapper());
        for (Category i : categoryList
        ) {
            i.setProductList(productDAOInterface.searchByCategory(i.getId()));
        }
        return categoryList;
    }

    @Override
    public List<Category> fillAllCategoryPaging(int page, String query, int pageSize) throws Exception {
        //String like = " '%" + query + "%' ";
        String sql;
        List<Category> categoryList=new ArrayList<Category>();
        if(query!=""&&query!=null){

            sql= "select * from category " +
                    "where name like ?"+
                    //+ like +
                    " order by fix_date desc " +
                    "Limit ?,?";
            categoryList = jdbcTemplate.query(sql, new CategoryMapper(),
                    "%"+query+"%",
                    page * pageSize, pageSize);
        }
        else  {
            sql="select * from category "+//+ like +
                    " order by fix_date desc " +
                    "Limit ?,?";
            categoryList = jdbcTemplate.query(sql, new CategoryMapper(),
                    page * pageSize, pageSize);
        }

        for (Category i : categoryList
        ) {
            i.setProductList(productDAOInterface.searchByCategory(i.getId()));
        }
        return categoryList;
    }

    /**
     * Tìm tất cả các Category và Product thuộc Category đó theo tên
     *
     * @return List<Category>
     */
    @Override
    public List<Category> searchByName(String name) {
        String newQuery="%"+name+"%";
        String sql = "select * from category where name like ?";
        List<Category> categoryList = jdbcTemplate.query(sql, new CategoryMapper(),newQuery);
        for (Category i : categoryList
        ) {
            i.setProductList(productDAOInterface.searchByCategory(i.getId()));
        }
        return categoryList;
    }

    /**
     * Tìm tất cả các Category và Product thuộc Category đó theo id
     *
     * @return Category
     */
    @Override
    public Category getById(int id) {
        Category category = jdbcTemplate.queryForObject("select * from category where id=?", new CategoryMapper(), id)  ;
        category.setProductList(productDAOInterface.searchByCategory(id));
        return category;
    }

    /**
     * Tổng tất cả các Category
     *
     * @return
     */
    //TODO https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/jdbc/core/RowCountCallbackHandler.html
    @Override
    public int getTotalCategory(String query) {
        String sql;
        //RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
        if (!query.equals(null) && !query.equals("")) {
            String like = " '%" + query + "%'";
            sql = "select count(*) total from category where name like " + like;
        } else {
            sql = "select count(*) total from category";
        }
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    /**
     * Tìm 1 category ( check tồn tại )
     *
     * @return số cột
     * Trả về : 0 là không tồn tại
     * 1 là tồn tại 1
     */
    @Override
    public int getTotalCategoryById(int id) {
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
        jdbcTemplate.query("select * from category where id=?", countCallback, id);
        return countCallback.getRowCount();
    }

    /**
     * Thêm 1 Category
     *
     * @return số cột ảnh hưởng
     */
    @Override
    public int insertCategory(Category category) throws Exception {
        return jdbcTemplate.update("insert into Category(name,create_date,fix_date,description) " +
                "VALUES (?,?,?,?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, category.getName());
                preparedStatement.setString(2, category.getCreate_date());
                preparedStatement.setString(3, category.getFix_date());
                preparedStatement.setString(4, category.getDescription());

            }
        });
    }

    /**
     * Update Category theo nhập vào
     *
     * @return số cột ảnh hưởng
     */
    @Override
    public int updateCategory(Category category) throws Exception {
        checkInputCategory(category);
        if (getTotalCategoryById(category.getId()) == 0) {
            return insertCategory(category);
        } else
            return jdbcTemplate.update("Update category set name=? , create_date=?, fix_date=?,description=?  " +
                    " where id=?", new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setString(1, category.getName());
                    preparedStatement.setString(2, category.getCreate_date());
                    preparedStatement.setString(3, category.getFix_date());
                    preparedStatement.setString(4, category.getDescription());
                    preparedStatement.setInt(5, category.getId());

                }
            });
    }

    /**
     * Xóa tất cả các Category với id
     *
     * @return
     */
    @Override
    public int deleteCategory(int id) {
        return jdbcTemplate.update("Delete from Category where id=?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        });

    }

    /**
     * check nhập vào và báo lỗi
     *
     * @return
     */
    private void checkInputCategory(Category category) throws Exception {
        if (category.getCreate_date().compareTo(category.getFix_date()) > 0)
            throw new Exception("Create date cannot be late than Fix date");
    }
}
