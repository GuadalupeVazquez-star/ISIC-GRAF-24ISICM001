import numpy as np
import matplotlib.pyplot as plt

# Figura original: un poligono de cinco vertices
figura = np.array([
    [0, 0],
    [2, 0],
    [2.5, 1.5],
    [1, 2.5],
    [0, 1.5]
])

# 1. Funcion de traslacion
def trasladar(puntos, tx, ty):
    return puntos + np.array([tx, ty])

# 2. Funcion de escalamiento
def escalar(puntos, sx, sy):
    return puntos * np.array([sx, sy])

# 3. Funcion de rotacion
def rotar(puntos, grados):
    angulo = np.radians(grados)
    matriz = np.array([
        [np.cos(angulo), -np.sin(angulo)],
        [np.sin(angulo), np.cos(angulo)]
    ])
    return puntos @ matriz.T

# 4. Funcion de sesgado
def sesgar(puntos, shx, shy):
    matriz = np.array([
        [1, shx],
        [shy, 1]
    ])
    return puntos @ matriz.T

# Aplicar las transformaciones
resultados = [
    ("Original", figura),
    ("Traslacion", trasladar(figura, 3, 2)),
    ("Escalamiento", escalar(figura, 1.5, 1.2)),
    ("Rotacion 35 grados", rotar(figura, 35)),
    ("Sesgado", sesgar(figura, 0.6, 0))
]

# Crear las graficas
fig, ejes = plt.subplots(2, 3, figsize=(14, 8))
ejes = ejes.flatten()

for ax, (nombre, puntos) in zip(ejes, resultados):
    cerrado = np.vstack([puntos, puntos[0]])

    ax.plot(cerrado[:, 0], cerrado[:, 1], marker="o")
    ax.fill(puntos[:, 0], puntos[:, 1], alpha=0.18)

    ax.axhline(0, linewidth=0.8)
    ax.axvline(0, linewidth=0.8)

    ax.grid(True)
    ax.set_aspect("equal", adjustable="box")
    ax.set_title(nombre)
    ax.set_xlabel("Eje X")
    ax.set_ylabel("Eje Y")

# Ocultar el espacio sobrante
ejes[-1].axis("off")

plt.tight_layout()
plt.show()