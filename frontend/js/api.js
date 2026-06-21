const API_URL = "https://devweb-3lsj.onrender.com";

async function cargarProyectos() {
    const response = await fetch(API_URL + "/projects");
    const proyectos = await response.json();
    
    const contenedor = document.getElementById("feedContainer");
    contenedor.innerHTML = "";

    proyectos.forEach(proyecto => {
        contenedor.innerHTML += `
            <div class="project-card">
                <h3>${proyecto.title}</h3>
                <p>Por: ${proyecto.freelancerName}</p>
            </div>
        `;
    });
}

function logout() {
    localStorage.removeItem("user");
    window.location.href = "users/login.html";
}

// Mostrar el banner correcto según el rol
if (user.role === "FREELANCER") {
    document.getElementById("freelancerBanner").style.display = "flex";
} else if (user.role === "CLIENT") {
    document.getElementById("clientBanner").style.display = "flex";
}

// Abrir y cerrar modales
function abrirModal(id) {
    document.getElementById(id).style.display = "flex";
}

function cerrarModal(id) {
    document.getElementById(id).style.display = "none";
}

// Crear proyecto
document.getElementById("formProyecto").addEventListener("submit", async function(e) {
    e.preventDefault();

    const proyecto = {
        title: document.getElementById("projTitle").value,
        description: document.getElementById("projDescription").value,
        category: document.getElementById("projCategory").value,
        imageUrl: document.getElementById("projImage").value,
        freelancer: { id: user.id }
    };

    const response = await fetch(API_URL + "/projects", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(proyecto)
    });

    if (response.ok) {
        cerrarModal("modalProyecto");
        cargarProyectos();
    } else {
        alert("Error al publicar el proyecto");
    }
});

// Convertirse en freelancer
async function confirmarFreelancer() {
    const response = await fetch(API_URL + "/users/" + user.id + "/become-freelancer", {
        method: "PUT"
    });

    if (response.ok) {
        const updatedUser = await response.json();
        localStorage.setItem("user", JSON.stringify(updatedUser));
        alert("¡Ahora eres freelancer!");
        window.location.reload();
    } else {
        alert("Error al cambiar de rol");
    }
}


cargarProyectos();