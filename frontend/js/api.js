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

cargarProyectos();