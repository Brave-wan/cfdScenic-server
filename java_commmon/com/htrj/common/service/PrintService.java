package com.htrj.common.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

import org.springframework.stereotype.Service;

import com.htrj.common.utils.ListDataSource;


@Service
public class PrintService {
	public void printSendCheck(Map<String, Object> para, List<Map<String, Object>> listData, HttpServletResponse response, HttpServletRequest request) throws JRException, IOException {
		response.getWriter().write("<a href='javascript:void(0);' onclick='window.print()'>打印</a>");

		String path = request.getServletContext().getRealPath("");
		JasperPrint jasperPrint;
		jasperPrint = JasperFillManager.fillReport(path + "/ireport/sendcheck.jasper", para, new ListDataSource(listData));
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());

		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		exporter.exportReport(); // 输入html报表
	}

	public void printReimbursement(Map<String, Object> para, List<Map<String, Object>> listData, HttpServletResponse response, HttpServletRequest request) throws JRException, IOException {
		response.getWriter().write("<a href='javascript:void(0);' onclick='window.print()'>打印</a>");

		String path = request.getServletContext().getRealPath("");
		JasperPrint jasperPrint;
		jasperPrint = JasperFillManager.fillReport(path + "/ireport/reimbursement.jasper", para, new ListDataSource(listData));
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());

		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		exporter.exportReport(); // 输入html报表
	}
}
