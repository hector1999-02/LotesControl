
function validarCampos() {
    var loginValue = document.getElementById("login").value;
    var passwordValue = document.getElementById("password").value;

    console.log("Validar campos ejecutado");

    if (loginValue === "" || passwordValue === "") {
        alert("Por favor, completa todos los campos.");
        return false;
    }
    return true;
}

window.onload = function() {
    var error = sessionStorage.getItem("error");
    console.log(error);
    console.log("Window.onload ejecutado");

    if (error) {
        alert(error);
        sessionStorage.removeItem("error");
    }
};
