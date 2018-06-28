package com.neuedu.model.dao;

import com.neuedu.model.po.Invoice;
import com.neuedu.model.po.NewOrder;
import com.neuedu.model.po.ReturnOrder;
import com.neuedu.model.po.WorkOrder;

public interface InvoiceDAO {
	public Invoice selectInvoice(String invoiceId);
	public void getSubstationInvoice(int invoiceId,String employeeName);
	public Invoice recordInvoice(Invoice invoice,String emloyeeName);
	public WorkOrder selectWorkOrder(String workId);
	public NewOrder selectNewOrder(int orderId);
	public void registerInvoice(int invoiceId, String employeeName);
	public void getClientInvoice(int invoiceId, String employeeName);
	public void abandonInvoice(int workId, String employeeName);
	public Invoice selectInvoiceByWorkId(int workId);
	public void abandonByNewOrderId(int newOrderId);
	public ReturnOrder selectReturnOrder(int orderId);
}
