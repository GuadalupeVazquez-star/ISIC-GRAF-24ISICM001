import tkinter as tk
from tkinter import colorchooser
import math

# Proyecto: PixelLab - Maria Guadalupe, Roberto Yahir - 2415ICM001 - 24ISICM002
# Algoritmo Bresenham
def bresenham(x0, y0, x1, y1, canvas):
    dx = abs(x1 - x0)
    dy = abs(y1 - y0)
    sx = 1 if x0 < x1 else -1
    sy = 1 if y0 < y1 else -1
    err = dx - dy
    while True:
        canvas.create_rectangle(x0*4, y0*4, x0*4+4, y0*4+4, fill="black", outline="")
        if x0 == x1 and y0 == y1:
            break
        e2 = 2 * err
        if e2 > -dy:
            err -= dy
            x0 += sx
        if e2 < dx:
            err += dx
            y0 += sy

# Conversión RGB a HSV
def rgb_to_hsv(r, g, b):
    r, g, b = r/255.0, g/255.0, b/255.0
    mx = max(r, g, b)
    mn = min(r, g, b)
    df = mx-mn
    if mx == mn: h = 0
    elif mx == r: h = (60 * ((g-b)/df) + 360) % 360
    elif mx == g: h = (60 * ((b-r)/df) + 120) % 360
    else: h = (60 * ((r-g)/df) + 240) % 360
    s = 0 if mx==0 else (df/mx)*100
    v = mx*100
    return h, s, v

# Ventana
root = tk.Tk()
root.title("PixelLab - Graficación - 2415ICM001 Y 24ISICM002")
root.configure(bg="black")
canvas = tk.Canvas(root, width=400, height=400, bg="white")
canvas.pack()
canvas.bind("<B1-Motion>", lambda e: bresenham(e.x//4, e.y//4, e.x//4+1, e.y//4+1, canvas))
tk.Label(root, text="Dibuja arrastrando el mouse - Algoritmo Bresenham", bg="black", fg="white").pack()
root.mainloop()