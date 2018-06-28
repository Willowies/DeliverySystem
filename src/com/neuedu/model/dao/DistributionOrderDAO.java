package com.neuedu.model.dao;

import java.util.Date;
import java.util.List;

import com.neuedu.model.po.DistributionOrder;

public interface DistributionOrderDAO {

	DistributionOrder selectDistributionOrder(int distributionId);

	List<DistributionOrder> selectDistribution(Date outDate, String productName, String warehouseName);

	List<DistributionOrder> selectDistribution(Date outDate, String warehouseName);

}
