package com.example.mybatisplus.utils.upload;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Excel导出工具
 *
 * @author oyp
 * @date 2019-09-19
 */
public class ExportExcelUtil {

    private final Logger logger = LoggerFactory.getLogger(ExportExcelUtil.class);
    private OutputStream outputStream;
    private Workbook workbook;
    private HSSFSheet sheet;
    private int index;


    public ExportExcelUtil(String fileName) {
        try {
            this.init(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            logger.error("导出excel出错,Message:{}", e.getMessage());
        }
    }

    public ExportExcelUtil(OutputStream outputStream) {
        try {
            this.init(outputStream);
        } catch (Exception e) {
            logger.error("导出excel出错,Message:{}", e.getMessage());
        }
    }

    private void init(OutputStream fileOutputStream) {
        this.outputStream = fileOutputStream;
        this.workbook = new HSSFWorkbook();
        this.index = 0;
    }

    //标题
    public ExportExcelUtil writeHead(Class<?> tClass) {
        if (tClass == null) {
            return this;
        }
        List<String> titlelist = new ArrayList<>();  //标题list
        List<Integer> colueWidthlist = new ArrayList<>();  //列宽list
        for (Field filed : getFields(tClass)) {
            ExcelExport excelExport = filed.getAnnotation(ExcelExport.class);
            titlelist.add(excelExport.columnName());
            colueWidthlist.add(excelExport.cloumnWidth());
        }
        // 标题样式
        CellStyle headerStyle = workbook.createCellStyle();
     /*   headerStyle.setBorderBottom(BorderStyle.valueOf((short)5));// 下边框
        headerStyle.setBorderLeft(BorderStyle.valueOf((short)5));// 左边框
        headerStyle.setBorderRight(BorderStyle.valueOf((short)5));// 右边框
        headerStyle.setBorderTop(BorderStyle.valueOf((short)5));// 上边框 */
        headerStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
        headerStyle.setVerticalAlignment( VerticalAlignment.CENTER);// 上下居中
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 12);  //字体大小
        titleFont.setBold(true);                    //字体粗细
        headerStyle.setFont(titleFont);
        createData(titlelist,colueWidthlist,headerStyle);
        return this;
    }

    /**
     * 获取带有ExcelExport注解的属性
     *
     * @param tClass 类
     * @return 属性
     */
    private List<Field> getFields(Class<?> tClass) {
        Field[] fields = tClass.getDeclaredFields();
        List<Field> list = new ArrayList<>(fields.length);
        for (Field f : fields) {
            //遍历tClass中的属性
            //有ExcelExport注解的信息属性保留
            if (f.isAnnotationPresent(ExcelExport.class)) {
                list.add(f);
            }
        }
        return list;
    }

    /**
     * 行列填充数据
     *
     * @param list 行
     */
    private void createData(List<String> list,List<Integer> colueWidthlist,CellStyle headerStyle) {
        if (sheet == null) {
            sheet = (HSSFSheet) workbook.createSheet();
        }
        HSSFRow row = sheet.createRow(index++);
        HSSFCell[] cells = new HSSFCell[list.size()];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = row.createCell(i);
            if (list.get(i) == null) {
                cells[i].setCellValue("");
            } else {
                if(colueWidthlist !=null )
                sheet.setColumnWidth(i,colueWidthlist.get(i) * 255);

                cells[i].setCellValue(list.get(i));
                if(headerStyle != null)
                cells[i].setCellStyle(headerStyle);
            }
        }
    }

    public <T> ExportExcelUtil writeList(List<T> list) {
        if (Objects.nonNull(list) && !list.isEmpty()) {
            for (Object obj : list) {
                if (obj != null) {
                    writeObject(obj);
                }
            }
        }
        return this;
    }

    //写入数据
    private void writeObject(Object obj) {
        Class clazz = obj.getClass();
        List<String> list = new ArrayList<>();
        for (Field field : getFields(clazz)) {
            field.setAccessible(true);
            try {
                Object o = field.get(obj);
                if (o instanceof Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    list.add(sdf.format(o));
                } else if (o instanceof BigDecimal) {
                    list.add(String.valueOf(((BigDecimal) o).setScale(2, BigDecimal.ROUND_CEILING)));
                } else {
                    list.add(String.valueOf(o));
                }
            } catch (IllegalAccessException e) {
                logger.error("格式化obj失败,Message:{}", e.getMessage());
            }
        }
        if (!list.isEmpty()) {
            createData(list,null,null);
        }
    }

    public void exportData() {
        try {
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            logger.error("创建excel失败,Message:{}", e.getMessage());
        }
    }
}
