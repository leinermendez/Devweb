const API_URL = "https://devweb-3lsj.onrender.com";

function logout() {
    localStorage.removeItem("user");
    window.location.href = "users/login.html";
}

function abrirModal(id) {
    document.getElementById(id).style.display = "flex";
}

function cerrarModal(id) {
    document.getElementById(id).style.display = "none";
}