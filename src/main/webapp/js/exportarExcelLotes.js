function exportarExcelArticulosLotes() {
    var formulario = document.getElementById("miFormulario1");
    var Reporte = "Vencimiento_1_mes";
    var fecha = formulario.elements["fecha"].value;

    var tabla = document.getElementById("miTabla1");
    var libro = XLSX.utils.table_to_book(tabla, {sheet: "Hoja1"});
    var nombreArchivo = Reporte + ".xlsx";
    XLSX.writeFile(libro, nombreArchivo);
};
