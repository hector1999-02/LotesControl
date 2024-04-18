
window.onpopstate = function () {
    // Redirigir al índice cuando se retrocede en el historial
    window.location.href = "index.jsp";
}


function exportarExcel() {
    var formulario = document.getElementById("miFormulario");
    var nombreFarmacia = formulario.elements["nombre_farmacia"].value;
    var fecha = formulario.elements["fecha"].value;

    var tabla = document.getElementById("miTabla");
    var libro = XLSX.utils.table_to_book(tabla, {sheet: "Hoja1"});
    var nombreArchivo = nombreFarmacia + "_" + fecha + ".xlsx";
    XLSX.writeFile(libro, nombreArchivo);
}

