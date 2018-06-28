package com.neuedu.model.dao;

import java.util.Date;
import java.util.List;

import com.neuedu.model.po.ProductTransferOrder;

public interface ProductTransferOrderDAO {

	List<ProductTransferOrder> selectProductTransferOrder(Date inputDate);

}
