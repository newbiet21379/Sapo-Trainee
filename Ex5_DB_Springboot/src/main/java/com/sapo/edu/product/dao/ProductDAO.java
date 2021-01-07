package com.sapo.edu.product.dao;


import com.sapo.edu.product.interfaceProduct.ProductDAOInterface;
import com.sapo.edu.product.validate.exception.ProductNotFoundException;
import com.sapo.edu.product.rowMapper.ProductMapper;
import com.sapo.edu.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

//TODO đánh dấu là 1 been có tên là ProductDAOImpl
@Repository("ProductDAO")
public class ProductDAO implements ProductDAOInterface {
    //TODO inject JdbcTemplate trong JDBC.

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * Tìm tất cả các sản phẩm
     *
     * @return List<Product>
     */
    @Override
    public List<Product> fillAllProduct() {
        //TODO viết câu lệnh sql lấy danh sách product;
        String sql = "SELECT * FROM product p ";

        return this.jdbcTemplate.query(sql, new ProductMapper.ProductRowMapper());

    }

    /**
     * Tìm tất cả các sản phẩm
     *
     * @return List<Product>
     */
    @Override
    public Product getLastProduct() {
        //TODO viết câu lệnh sql lấy danh sách product;
        String sql = "SELECT * FROM demo.product order by id desc limit 0,1";

        return this.jdbcTemplate.queryForObject(sql, new ProductMapper.ProductRowMapper());

    }

    @Override
    public List<Product> fillAllProductPaging(int page, String query, int pageSize) {
        //TODO viết câu lệnh sql lấy danh sách product theo name và có paging;
        String like = " '%" + query + "%' ";
        String sql = "SELECT * FROM product p where name like " + like + " Limit ?,?";
        System.out.println(sql);
        return this.jdbcTemplate.query(sql, new ProductMapper.ProductRowMapper(), (page - 1) * pageSize, pageSize);

    }

    /**
     * Tìm sản phẩm theo hạng mục
     *
     * @param category_id
     * @return List<Product>
     */
    @Override
    public List<Product> searchByCategory(int category_id) {
        String sql = "select * from product p where p.category_id=?";
        return jdbcTemplate.query(sql, new ProductMapper.ProductRowMapper(), category_id);
    }

    /**
     * Tìm theo id của sản phẩm
     *
     * @param id
     * @return Product
     */
    @Override
    public Product getById(int id) {
        Product product;
        try {
            product = jdbcTemplate.queryForObject("select * from product p where p.id=?", new ProductMapper.ProductRowMapper(), id);
        } catch (DataAccessException e) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    /**
     * Đếm tổng số sản phẩm
     *
     * @return số sản phẩm
     */
    @Override
    public int getTotalProductCount() {
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
        jdbcTemplate.query("select * from product", countCallback);
        return countCallback.getRowCount();
    }

    /**
     * Thêm sản phẩm
     *
     * @param product
     * @return số bảng ghi bị ảnh hưởng
     * @throws Exception in ra màn hình
     */
    @Override
    public int insertProduct(Product product) throws Exception {


        return jdbcTemplate.update("insert into Product(id,name,img_link,price,quantity,sale_number,description,category_id) " +
                "VALUES (?,?,?,?,?,?,?,?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, getLastProduct().getId() + 1);
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getImg_link());
                preparedStatement.setBigDecimal(4, product.getPrice());
                preparedStatement.setInt(5, product.getQuantity());
                preparedStatement.setInt(6, product.getSale_number());
                preparedStatement.setString(7, product.getDescription());
                preparedStatement.setInt(8, product.getCategory_id());
            }
        });
    }

    @Override
    public int insertListProduct(List<Product> products) throws Exception {
        for (Product i : products) {

            jdbcTemplate.update("insert into Product(id,name,img_link,price,quantity,sale_number,description,category_id) " +
                    "VALUES (?,?,?,?,?,?,?,?)", new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setInt(1, getLastProduct().getId() + 1);
                    preparedStatement.setString(2, i.getName());
                    preparedStatement.setString(3, i.getImg_link());
                    preparedStatement.setBigDecimal(4, i.getPrice());
                    preparedStatement.setInt(5, i.getQuantity());
                    preparedStatement.setInt(6, i.getSale_number());
                    preparedStatement.setString(7, i.getDescription());
                    preparedStatement.setInt(8, i.getCategory_id());
                }
            });
        }
        return products.size();
    }

    /**
     * Cập nhật Product
     *
     * @param product
     * @return Số cột bị ảnh hưởng
     * @throws Exception
     */
    @Override
    public int updateProduct(Product product) throws Exception {


        if (getById(product.getId()) == null)
            return insertProduct(product);
        else
            return jdbcTemplate.update("Update product set name=?,img_link=?,price=?,sale_number=?,quantity=?,description=?,category_id=?" +
                    " where id=?", new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setString(1, product.getName());
                    preparedStatement.setString(2, product.getImg_link());
                    preparedStatement.setBigDecimal(3, product.getPrice());
                    preparedStatement.setInt(4, product.getSale_number());
                    preparedStatement.setInt(5, product.getQuantity());
                    preparedStatement.setString(6, product.getDescription());
                    preparedStatement.setInt(7, product.getCategory_id());
                    preparedStatement.setInt(8, product.getId());
                }
            });
    }

    /**
     * Xóa Product theo id
     *
     * @param id
     * @return
     */
    @Override
    public int deleteProductById(int id) {
        return jdbcTemplate.update("Delete from Product where id=?", id);
    }

    /**
     * Xóa Product theo Category
     *
     * @param id
     * @return
     */
    @Override
    public int deleteProductByCategory(int id) throws Exception {

        return jdbcTemplate.update("Delete from Product where category_id=?", id);
    }


}
