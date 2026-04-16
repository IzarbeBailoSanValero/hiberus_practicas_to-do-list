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

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 * Clase utilitaria para trabajar con archivos Excel (.xlsx) usando Apache POI.
 *
 * Apache POI permite crear, leer y modificar Excel desde Java.
 *
 * Diferencias importantes:
 * - XLS  (antiguo) usa HSSF
 * - XLSX (moderno) usa XSSF (el que se usa aquí)
 *
 * Estructura de un Excel en POI:
 *
 * XSSFWorkbook  -> representa el archivo completo Excel
 * XSSFSheet     -> representa una hoja (pestaña)
 * XSSFRow       -> representa una fila
 * XSSFCell      -> representa una celda
 */
public class ExcelUtil {

    private static final Log _log = LogFactoryUtil.getLog(ExcelUtil.class);

    private ExcelUtil() {
    }

    /**
     * Genera un Excel plantilla con cabeceras y lo envía al navegador.
     */
    public static void exportTemplate(ResourceRequest resourceRequest, ResourceResponse resourceResponse, String templateName, List<String> headers) {
        try {
            XSSFWorkbook excel = excelHeader(resourceRequest, headers);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Convierte el Excel a bytes para enviarlo
            excel.write(baos);

            baos.flush();
            baos.close();

            ServletResponseUtil.sendFile(
                PortalUtil.getHttpServletRequest(resourceRequest),
                PortalUtil.getHttpServletResponse(resourceResponse),
                templateName,
                baos.toByteArray(),
                "application/download"
            );

        } catch (Exception e) {
            _log.error(e, e);
        }
    }

    /**
     * Traduce cabeceras usando los ficheros de idioma de Liferay.
     */
    public static List<String> getColumnNamesMap(PortletRequest portletRequest, List<String> headers) {
        List<String> list = new ArrayList();

        for (String header : headers) {
            list.add(LanguageUtil.get(
                ResourceBundle.getBundle("content/Language", portletRequest.getLocale()),
                header
            ));
        }

        return list;
    }

    /**
     * Crea un Excel con solo la fila de cabeceras.
     */
    public static XSSFWorkbook excelHeader(ResourceRequest actionRequest, List<String> headers) {

        // Representa el archivo Excel completo en memoria
        XSSFWorkbook excel = new XSSFWorkbook();

        // Representa una hoja dentro del Excel
        XSSFSheet excelSheet = excel.createSheet();
        excel.setSheetName(0, "Datos");

        // Fuente de las cabeceras
        XSSFFont font = excel.createFont();
        font.setBold(true);
        font.setFontHeight((double) 10.0F);
        font.setFontName("Asap");

        // Estilo de las celdas de cabecera
        XSSFCellStyle cellStyle = excel.createCellStyle();
        cellStyle.setFont(font);

        // Permite múltiples líneas en la celda
        cellStyle.setWrapText(true);

        // Alineaciones
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        // Añade bordes
        setStyleAllBorders(cellStyle);

        // Color de fondo gris
        XSSFColor color = new XSSFColor(Color.decode("#C0C0C0"), new DefaultIndexedColorMap());
        cellStyle.setFillForegroundColor(color);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Fila de cabecera
        XSSFRow row = excelSheet.createRow(0);

        // Altura mayor para cabeceras
        row.setHeightInPoints(44.25F);

        // Crear cada celda de cabecera
        for (int column = 0; column < headers.size(); ++column) {

            // Ancho fijo de columna
            excelSheet.setColumnWidth(column, 5000);

            createStringCell(
                row,
                cellStyle,
                column,
                LanguageUtil.get(
                    ResourceBundle.getBundle("content/Language", actionRequest.getLocale()),
                    headers.get(column)
                )
            );
        }

        // Formato de datos (texto)
        DataFormat fmt = excel.createDataFormat();

        CellStyle textStyle = excel.createCellStyle();

        // "@" indica formato texto en Excel
        textStyle.setDataFormat(fmt.getFormat("@"));

        // Aplicar formato texto a todas las columnas
        for (int column = 0; column < headers.size(); ++column) {
            excelSheet.setDefaultColumnStyle(column, textStyle);
        }

        return excel;
    }

    /**
     * Lee las cabeceras de la primera fila.
     */
    public static List<String> getExcelHeaders(Sheet sheet) {
        List<String> headersList = new ArrayList();

        Row row = sheet.getRow(0);

        if (row != null) {
            boolean blankCell = false;
            int index = 0;

            while (!blankCell) {
                Cell cell = row.getCell(index);

                if (cell != null) {
                    try {
                        headersList.add(cell.getStringCellValue());
                    } catch (NumberFormatException e) {
                        headersList.add(String.valueOf(cell.getNumericCellValue()));
                    }
                    index++;
                } else {
                    blankCell = true;
                }
            }
        }

        return headersList;
    }

    /**
     * Valida que las cabeceras coincidan con las esperadas.
     */
    public static boolean validateExcelHeaders(ActionRequest actionRequest, List<String> excelHeaders, List<String> columnNames, List<String> msgs) {

        if (excelHeaders == null) return false;

        boolean isValid = true;

        List<String> structureHeaders = new ArrayList();
        structureHeaders.addAll(columnNames);

        if (excelHeaders.size() != columnNames.size()) {
            msgs.add("La plantilla no está actualizada");
            return false;
        }

        for (int i = 0; i < excelHeaders.size(); ++i) {

            String header = excelHeaders.get(i);

            if (header.equals(structureHeaders.get(i))) {

                if (excelHeaders.lastIndexOf(header) > i) {
                    msgs.add("Cabecera duplicada: " + header);
                    isValid = false;
                } else if (header.length() == 0) {
                    msgs.add("Cabecera vacía en columna " + (i + 1));
                    isValid = false;
                }

            } else {
                msgs.add("Cabecera incorrecta en columna " + (i + 1));
                isValid = false;
            }
        }

        return isValid;
    }

    /**
     * Elimina filas vacías al final del Excel.
     */
    public static Sheet removeTrailingEmptyRows(Sheet sheet) {

        while (true) {

            Row lastRow = sheet.getRow(sheet.getLastRowNum());

            boolean nonBlankRowFound = false;

            if (lastRow != null && lastRow.getFirstCellNum() >= 0) {
                for (int c = lastRow.getFirstCellNum(); c <= lastRow.getLastCellNum(); ++c) {
                    Cell cell = lastRow.getCell(c);

                    if (cell != null && cell.getCellType() != CellType.BLANK) {
                        nonBlankRowFound = true;
                        break;
                    }
                }
            }

            if (nonBlankRowFound) break;

            sheet.removeRow(lastRow);
        }

        return sheet;
    }

    /**
     * Exporta un Excel con datos.
     */
    public static XSSFWorkbook exportexcel(ResourceRequest actionRequest, List<String> headers, Map<String, Object[]> content) {

        XSSFWorkbook excel = excelHeader(actionRequest, headers);

        XSSFSheet sheet = excel.getSheetAt(0);

        int rowid = 1;

        for (String key : content.keySet()) {

            XSSFRow row = sheet.createRow(rowid++);
            Object[] objectArr = content.get(key);

            int cellid = 0;

            for (Object obj : objectArr) {

                Cell cell = row.createCell(cellid++);

                // Se asume que todos los valores son String
                cell.setCellValue((String) obj);
            }
        }

        return excel;
    }

    /**
     * Obtiene los valores de una fila.
     */
    public static List<String> getExcelRowValues(Row row, List<String> headers) {

        ArrayList<String> values = new ArrayList();

        for (int i = 0; i < headers.size(); ++i) {

            String value = "";

            try {
                Cell cell = row.getCell(i);

                if (cell != null) {

                    if (cell.getCellType() == CellType.NUMERIC) {
                        BigDecimal bd = BigDecimal.valueOf(cell.getNumericCellValue());
                        value = String.valueOf(bd);
                    } else {
                        value = cell.getStringCellValue().trim();
                    }
                }

            } catch (Exception e) {
                _log.error("Error en fila " + (row.getRowNum() + 1) + ", columna " + (i + 1));
            }

            values.add(value);
        }

        return values;
    }

    /**
     * Convierte una fila en JSON.
     */
    public static JSONObject getExcelValues(List<String> fieldNames, List<String> excelRowValues) {

        JSONObject excelValues = JSONFactoryUtil.createJSONObject();

        for (int i = 0; i < excelRowValues.size(); ++i) {
            excelValues.put(fieldNames.get(i), excelRowValues.get(i));
        }

        return excelValues;
    }

    /**
     * Aplica bordes a una celda.
     */
    private static void setStyleAllBorders(XSSFCellStyle cellStyle) {
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
    }

    /**
     * Crea una celda tipo String con estilo.
     */
    public static void createStringCell(XSSFRow row, XSSFCellStyle cellStyle, int column, String value) {
        XSSFCell cell = row.createCell(column, CellType.STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }
}