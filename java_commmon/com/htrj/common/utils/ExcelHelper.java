package com.htrj.common.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.htrj.common.excelTools.ExcelUtils;
import com.htrj.common.excelTools.JsGridReportBase;
import com.htrj.common.excelTools.TableData;


public class ExcelHelper {
    
    public void exportExcel(List<String> hearderslist,List<String> fieldslist, List listData,HttpServletRequest request,HttpServletResponse response) throws Exception{
        if(listData != null){
            String[] fields = fieldslist.toArray(new String[fieldslist.size()]);
            String[] hearders = hearderslist.toArray(new String[hearderslist.size()]);
            TableData td = ExcelUtils.createTableData(listData, ExcelUtils.createTableHeader(hearders), fields);
            JsGridReportBase report = new JsGridReportBase(request, response);
            report.exportToExcel("考勤记录", "", td);
        }
    }

}
