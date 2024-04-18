/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.getElementById('miFormulario2').addEventListener('submit', function (event) {
    var fechaCaducidad = document.getElementById('fechaCaducidad').value;
    var fechaActual = new Date().toISOString().split('T')[0]; // Obtén la fecha actual en formato ISO
    console.log(fechaCaducidad);
    if (fechaCaducidad < fechaActual) {
        alert('La fecha de caducidad no puede ser menor que la fecha actual.');
        event.preventDefault(); // Evita que el formulario se envíe si la validación falla
    }
});


// Obtener la fecha actual
var fechaActual = new Date();

// Formatear la fecha como una cadena
var options = {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'};
var fechaFormateada = fechaActual.toLocaleDateString('es-ES', options);

// Mostrar la fecha en el elemento con id "fechaActual"
document.getElementById('fechaActual').innerHTML = fechaFormateada;



$(function () {
    var actualizarHora = function () {
        var fecha = new Date(),
                hora = fecha.getHours(),
                minutos = fecha.getMinutes(),
                segundos = fecha.getSeconds(),
                diaSemana = fecha.getDay(),
                dia = fecha.getDate(),
                mes = fecha.getMonth(),
                anio = fecha.getFullYear(),
                ampm;

        var $pHoras = $("#horas"),
                $pSegundos = $("#segundos"),
                $pMinutos = $("#minutos"),
                $pAMPM = $("#ampm"),
                $pDiaSemana = $("#diaSemana"),
                $pDia = $("#dia"),
                $pMes = $("#mes"),
                $pAnio = $("#anio");
        var semana = ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'];
        var meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

        $pDiaSemana.text(semana[diaSemana]);
        $pDia.text(dia);
        $pMes.text(meses[mes]);
        $pAnio.text(anio);
        if (hora >= 12) {
            hora = hora - 12;
            ampm = "PM";
        } else {
            ampm = "AM";
        }
        if (hora == 0) {
            hora = 12;
        }
        if (hora < 10) {
            $pHoras.text("0" + hora)
        } else {
            $pHoras.text(hora)
        }
        ;
        if (minutos < 10) {
            $pMinutos.text("0" + minutos)
        } else {
            $pMinutos.text(minutos)
        }
        ;
        if (segundos < 10) {
            $pSegundos.text("0" + segundos)
        } else {
            $pSegundos.text(segundos)
        }
        ;
        $pAMPM.text(ampm);

    };


    actualizarHora();
    var intervalo = setInterval(actualizarHora, 1000);
});

document.getElementById('precio_etiquetado').oninput = function () {
    var input = this.value.trim();
    var regex = /^\d+(\.\d{1,2})?$/;

    if (!regex.test(input) || parseFloat(input) <= 0) {
        document.getElementById('precio_etiquetado_error').style.display = 'inline';
        this.setCustomValidity('Ingrese un número decimal positivo con hasta dos decimales.');
    } else {
        document.getElementById('precio_etiquetado_error').style.display = 'none';
        this.setCustomValidity('');
    }
};

function exportarExcelArticulosLotes(idTabla) {
    var Reportes = {
        'miTabla0': "articulosVencidos",
        'miTabla1': "Vencimiento_1_mes",
        'miTabla2': "Vencimiento_3_mes",
        'miTabla3': "Vencimiento_6_mes",
        'miTabla4': "Vencimiento_8_mes"
    };

    var Reporte = Reportes[idTabla] || "Reporte_desconocido"; // Si el ID de la tabla no coincide con ninguno en la lista, se usa un valor predeterminado

    var tabla = document.getElementById(idTabla);

    // Copiar la tabla excluyendo la columna de acciones
    var tablaSinAcciones = tabla.cloneNode(true);
    tablaSinAcciones.querySelectorAll("th, td").forEach((cell) => {
        if (cell.classList.contains("acciones")) { // Reemplaza "acciones" por la clase de la columna que quieres excluir
            cell.parentNode.removeChild(cell);
        }
    });

    var libro = XLSX.utils.table_to_book(tablaSinAcciones, {sheet: "Hoja1", raw:true});
    
    // Obtener la fecha actual
    var fechaActual = new Date().toISOString().slice(0, 10); // Formato: YYYY-MM-DD
    
    var nombreArchivo = Reporte + "_" + fechaActual + ".xlsx"; // Nombre del archivo con la fecha actual
    XLSX.writeFile(libro, nombreArchivo);
}


