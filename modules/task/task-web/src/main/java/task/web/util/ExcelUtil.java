package task.web.util;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import task.web.util.ExcelUtilConstants;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    private static final Log _log = LogFactoryUtil.getLog(ExcelUtil.class);

    private ExcelUtil() {
    }

    public static void exportTemplate(ResourceRequest resourceRequest, ResourceResponse resourceResponse, String templateName, List<String> headers) {
        try {
            XSSFWorkbook excel = excelHeader(resourceRequest, headers);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            excel.write(baos);
            baos.flush();
            baos.close();
            ServletResponseUtil.sendFile(PortalUtil.getHttpServletRequest(resourceRequest), PortalUtil.getHttpServletResponse(resourceResponse), templateName, baos.toByteArray(), "application/download");
        } catch (Exception e) {
            _log.error(e, e);
        }

    }

    public static List<String> getColumnNamesMap(PortletRequest portletRequest, List<String> headers) {
        List<String> list = new ArrayList();

        for(String header : headers) {
            list.add(LanguageUtil.get(ResourceBundle.getBundle("content/Language", portletRequest.getLocale()), header));
        }

        return list;
    }

    

    public static XSSFWorkbook excelHeader(ResourceRequest actionRequest, List<String> headers) {
        XSSFWorkbook excel = new XSSFWorkbook();
        XSSFSheet excelSheet = excel.createSheet();
        excel.setSheetName(0, "Datos");
        XSSFFont font = excel.createFont();
        font.setBold(true);
        font.setFontHeight((double)10.0F);
        font.setFontName("Asap");
        XSSFCellStyle cellStyle = excel.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        setStyleAllBorders(cellStyle);
        XSSFColor color = new XSSFColor(Color.decode("#C0C0C0"), new DefaultIndexedColorMap());
        cellStyle.setFillForegroundColor(color);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFRow row = excelSheet.createRow(0);
        row.setHeightInPoints(44.25F);

        for(int column = 0; column < headers.size(); ++column) {
            excelSheet.setColumnWidth(column, 5000);
            createStringCell(row, cellStyle, column, LanguageUtil.get(ResourceBundle.getBundle("content/Language", actionRequest.getLocale()), (String)headers.get(column)));
        }

        DataFormat fmt = excel.createDataFormat();
        CellStyle textStyle = excel.createCellStyle();
        textStyle.setDataFormat(fmt.getFormat("@"));

        for(int column = 0; column < headers.size(); ++column) {
            excelSheet.setDefaultColumnStyle(column, textStyle);
        }

        return excel;
    }

    public static List<String> getExcelHeaders(Sheet sheet) {
        List<String> headersList = new ArrayList();
        Row row = sheet.getRow(0);
        if (row != null) {
            boolean blankCell = false;
            int index = 0;

            while(!blankCell) {
                Cell cell = row.getCell(index);
                if (cell != null) {
                    try {
                        headersList.add(cell.getStringCellValue());
                    } catch (NumberFormatException var7) {
                        headersList.add(String.valueOf(cell.getNumericCellValue()));
                    }

                    ++index;
                } else {
                    blankCell = true;
                }
            }
        }

        return headersList;
    }

    public static boolean validateExcelHeaders(ActionRequest actionRequest, List<String> excelHeaders, List<String> columnNames, List<String> msgs) {
        if (excelHeaders == null) {
            return false;
        } else {
            String invalidHeader = "self.registration.web.admin.import-organisms-header-invalid";
            String repeatedHeader = "self.registration.web.admin.import-organisms.header-repeated";
            boolean isValid = true;
            List<String> structureHeaders = new ArrayList();
            structureHeaders.addAll(columnNames);
            if (excelHeaders.size() != columnNames.size()) {
                msgs.add(LanguageUtil.get(ResourceBundle.getBundle("content/Language", actionRequest.getLocale()), "self.registration.web.admin.import-template-not-up-to-date"));
                isValid = false;
            } else {
                for(int i = 0; i < excelHeaders.size(); ++i) {
                    String header = (String)excelHeaders.get(i);
                    if (((String)excelHeaders.get(i)).equals(structureHeaders.get(i)) && structureHeaders.contains(header)) {
                        if (excelHeaders.lastIndexOf(header) > i) {
                            msgs.add(LanguageUtil.format(ResourceBundle.getBundle("content/Language", actionRequest.getLocale()), repeatedHeader, new String[]{header, String.valueOf(i + 1), String.valueOf(excelHeaders.lastIndexOf(header) + 1)}));
                            isValid = false;
                        } else if (header.length() == 0) {
                            msgs.add(LanguageUtil.format(actionRequest.getLocale(), invalidHeader, new String[]{String.valueOf(i + 1)}));
                            isValid = false;
                        }
                    } else {
                        msgs.add(LanguageUtil.format(ResourceBundle.getBundle("content/Language", actionRequest.getLocale()), invalidHeader, new String[]{header, String.valueOf(i + 1), (String)structureHeaders.get(i)}));
                        isValid = false;
                    }
                }
            }

            return isValid;
        }
    }

    public static Sheet removeTrailingEmptyRows(Sheet sheet) {
        Sheet hsheet = sheet;
        boolean stop = false;
        Row lastRow = null;
        Cell cell = null;

        while(!stop) {
            boolean nonBlankRowFound = false;
            lastRow = hsheet.getRow(hsheet.getLastRowNum());
            if (lastRow.getFirstCellNum() >= 0) {
                for(int c = lastRow.getFirstCellNum(); c <= lastRow.getLastCellNum(); ++c) {
                    cell = lastRow.getCell(c);
                    if (cell != null && lastRow.getCell(c).getCellType() != CellType.BLANK) {
                        nonBlankRowFound = true;
                    }
                }
            }

            if (nonBlankRowFound) {
                stop = true;
            } else {
                hsheet.removeRow(lastRow);
            }
        }

        return hsheet;
    }

    public static XSSFWorkbook exportexcel(ResourceRequest actionRequest, List<String> headers, Map<String, Object[]> content) {
        XSSFWorkbook excel = new XSSFWorkbook();
        XSSFSheet excelSheet = excel.createSheet();
        excel.setSheetName(0, "Datos");
        XSSFFont font = excel.createFont();
        font.setBold(true);
        font.setFontHeight((double)10.0F);
        font.setFontName("Asap");
        XSSFCellStyle cellStyle = excel.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        setStyleAllBorders(cellStyle);
        XSSFColor color = new XSSFColor(Color.decode("#C0C0C0"), new DefaultIndexedColorMap());
        cellStyle.setFillForegroundColor(color);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFRow row = excelSheet.createRow(0);
        row.setHeightInPoints(44.25F);

        for(int column = 0; column < headers.size(); ++column) {
            excelSheet.setColumnWidth(column, 5000);
            createStringCell(row, cellStyle, column, LanguageUtil.get(ResourceBundle.getBundle("content/Language", actionRequest.getLocale()), (String)headers.get(column)));
        }

        DataFormat fmt = excel.createDataFormat();
        CellStyle textStyle = excel.createCellStyle();
        textStyle.setDataFormat(fmt.getFormat("@"));

        for(int column = 0; column < headers.size(); ++column) {
            excelSheet.setDefaultColumnStyle(column, textStyle);
        }

        Set<String> keyid = content.keySet();
        int rowid = 1;

        for(String key : keyid) {
            row = excelSheet.createRow(rowid++);
            Object[] objectArr = content.get(key);
            int cellid = 0;

            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        return excel;
    }

    public static List<String> getExcelRowValues(Row row, List<String> organismsFormFieldHeaders) {
        ArrayList<String> excelRowValues = new ArrayList();

        for(int i = 0; i < organismsFormFieldHeaders.size(); ++i) {
            String value = "";

            try {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        BigDecimal bd = BigDecimal.valueOf(cell.getNumericCellValue());
                        value = String.valueOf(bd);
                    } else {
                        value = cell.getStringCellValue();
                        value = cell.getStringCellValue().trim();
                    }
                }
            } catch (Exception var7) {
                _log.error("Error getting value of row " + (row.getRowNum() + 1) + ", column " + (i + 1));
            }

            excelRowValues.add(value);
        }

        return excelRowValues;
    }

    public static JSONObject getExcelValues(List<String> fieldNames, List<String> excelRowValues) {
        JSONObject excelValues = JSONFactoryUtil.createJSONObject();

        for(int i = 0; i < excelRowValues.size(); ++i) {
            String value = (String)excelRowValues.get(i);
            String key = (String)fieldNames.get(i);
            excelValues.put(key, value);
        }

        return excelValues;
    }

    private static void setStyleAllBorders(XSSFCellStyle cellStyle) {
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
    }

    public static void createStringCell(XSSFRow row, XSSFCellStyle cellStyle, int column, String value) {
        XSSFCell cell = row.createCell(column, CellType.STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }
}
