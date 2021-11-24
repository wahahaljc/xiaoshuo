package com.example.mybatisplus.utils.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExportExcel {

    private static Logger logger = LoggerFactory.getLogger(ExportExcel.class);

    public static void uploadExportExcel(String fileNames,Class<?> tClass, List<?> list, HttpServletResponse response) {
        //导出excel
        String fileName =fileNames+".xls";
        try {
            response.setContentType("application/octet-stream;");
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));

            ExportExcelUtil excelExportUtil = new ExportExcelUtil(response.getOutputStream());
            //excel第一列表头信息
            excelExportUtil.writeHead(tClass);
            //写入每一行信息
            excelExportUtil.writeList(list);
            excelExportUtil.exportData();
        } catch (IOException e) {
            logger.info("InventoryManageController export excel error,error:{}", e.getMessage());
        }
    }

}
