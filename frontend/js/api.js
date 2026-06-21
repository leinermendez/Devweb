const API_URL = "https://devweb-3lsj.onrender.com";

function logout() {
    if (confirm("¿Seguro que querés cerrar sesión?")) {
        localStorage.removeItem("user");
        window.location.href = "users/login.html";
    }
}

function abrirModal(id) {
    document.getElementById(id).style.display = "flex";
}

function cerrarModal(id) {
    document.getElementById(id).style.display = "none";
}