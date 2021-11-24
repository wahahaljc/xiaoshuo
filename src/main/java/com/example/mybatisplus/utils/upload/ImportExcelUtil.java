package com.example.mybatisplus.utils.upload;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oyp
 */
@SuppressWarnings("unused")
public class ImportExcelUtil {

    private final Logger logger = LoggerFactory.getLogger(ImportExcelUtil.class);

    private static final int DEFAULT_COUNT = 20000;
    private static final int DEFAULT_START_LINE = 0;

    public static <T> List<T> convertSheetToList(InputStream in, Class<T> clazz, int startLine, int maxCount) throws Exception {
        List<T> list = new ArrayList<>();

        // 根据版本选择创建Workbook的方式
        HSSFWorkbook wb = new HSSFWorkbook(in);
        //获取第0个工作表格
        Sheet sheet = wb.getSheetAt(0);
        int count = sheet.getLastRowNum();
        if (maxCount == 0) {
            maxCount = DEFAULT_COUNT;
        }
        if (count < maxCount) {
            throw new Exception("导入失败，excel数据控制在" + count + "条之内！");
        }
        //遍历excel表格并将每一行中的数据转换成对象
        if (startLine < 0) {
            startLine = DEFAULT_START_LINE;
        }
        for (int i = startLine; i <= maxCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            T obj = convertLineToObj(clazz, row);
            if (obj == null) {
                continue;
            }
            list.add(obj);
        }
        return list;
    }
    public static <T> List<T> convertSheetToListTwo(MultipartFile file, Class<T> clazz, int startLine, int maxCount) throws Exception {
        List<T> list = new ArrayList<>();
        String fileName = file.getOriginalFilename();//源文件名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);//后缀名
        if(suffixName.equals("xlsx")){
            XSSFWorkbook xs = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = xs.getSheetAt(0);
            int count = sheet.getLastRowNum();
            if (maxCount == 0) {
                maxCount = DEFAULT_COUNT;
            }
            if (count > maxCount) {
                throw new Exception("导入失败，excel数据控制在" + maxCount + "条之内！");
            }
            //遍历excel表格并将每一行中的数据转换成对象
            if (startLine < 0) {
                startLine = DEFAULT_START_LINE;
            }
            for (int i = startLine; i <= count; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                T obj = convertLineToObj(clazz, row);
                if (obj == null) {
                    continue;
                }
                list.add(obj);
            }
            return list;
        }else{
            // 根据版本选择创建Workbook的方式
            HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
            //获取第0个工作表格
            Sheet sheet = wb.getSheetAt(0);
            int count = sheet.getLastRowNum();
            if (maxCount == 0) {
                maxCount = DEFAULT_COUNT;
            }
            if (count > maxCount) {
                throw new Exception("导入失败，excel数据控制在" + maxCount + "条之内！");
            }
            //遍历excel表格并将每一行中的数据转换成对象
            if (startLine < 0) {
                startLine = DEFAULT_START_LINE;
            }
            for (int i = startLine; i <= count; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                T obj = convertLineToObj(clazz, row);
                if (obj == null) {
                    continue;
                }
                list.add(obj);
            }
            return list;
        }
    }

    public static <T> String returncell(InputStream in, int startLine,int column, int maxCount) throws Exception {
        // 根据版本选择创建Workbook的方式
        HSSFWorkbook wb = new HSSFWorkbook(in);
        //获取第0个工作表格
        Sheet sheet = wb.getSheetAt(0);
        int count = sheet.getLastRowNum();
        if (maxCount == 0) {
            maxCount = DEFAULT_COUNT;
        }

        //遍历excel表格并将每一行中的数据转换成对象
        if (startLine < 0) {
            startLine = DEFAULT_START_LINE;
        }
            Row row = sheet.getRow(startLine);
            Cell cell=row.getCell(column);

        return cell.toString();
    }

    /**
     * 行遍历
     */
    private static <T> T convertLineToObj(Class<T> clazz, Row row) throws Exception {
        T obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ExcelImport annotation = field.getAnnotation(ExcelImport.class);
            if (annotation != null && row.getLastCellNum() >= annotation.columnIndex()) {
                //每行对应的单元格遍历
                Cell cell = row.getCell(annotation.columnIndex());
                if (cell == null) {
                    throw new Exception("请使用正确的excel模板");
                }

                field.setAccessible(true);
                field.set(obj, getCellValue(cell));
            }
        }
        return obj;
    }

    private static Object getCellValue(Cell cell) {
        Object value = null;
        String val = "";
        DecimalFormat df = new DecimalFormat("0.00"); // 格式化为整数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式化
        switch (cell.getCellType()) {
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case NUMERIC:
                String dataFormat = cell.getCellStyle().getDataFormatString();	// 单元格格式
                boolean isDate = HSSFDateUtil.isCellDateFormatted(cell);
                if ("General".equals(dataFormat)) {
                    value = df.format(cell.getNumericCellValue());
                } else if (isDate) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = cell.getNumericCellValue();
                }
                break;
            case BLANK:
                value = "";
                break;
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            default:
                break;
        }
        if(null != value){
            val = value.toString();
        }
        return val;
    }

}
