
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tablero extends JPanel implements ActionListener {

    Image pacmanDerechaImg = new ImageIcon(this.getClass().getResource("pacmanDerecha.gif")).getImage();
    Image pacmanIzquierdaImg = new ImageIcon(this.getClass().getResource("pacmanIzquierda.gif")).getImage();
    Image pacmanArribaImg = new ImageIcon(this.getClass().getResource("pacmanArriba.gif")).getImage();
    Image pacmanAbajoImg = new ImageIcon(this.getClass().getResource("pacmanAbajo.gif")).getImage();
    Image pacmanRojoDerechaImg = new ImageIcon(this.getClass().getResource("pacmanRojoDerecha.gif")).getImage();
    Image pacmanRojoIzquierdaImg = new ImageIcon(this.getClass().getResource("pacmanRojoIzquierda.gif")).getImage();
    Image pacmanRojoArribaImg = new ImageIcon(this.getClass().getResource("pacmanRojoArriba.gif")).getImage();
    Image pacmanRojoAbajoImg = new ImageIcon(this.getClass().getResource("pacmanRojoAbajo.gif")).getImage();

    Image fantasma1Img = new ImageIcon(this.getClass().getResource("fantasma1.gif")).getImage();
    Image fantasma2Img = new ImageIcon(this.getClass().getResource("fantasma2.gif")).getImage();

    Pacman pacman1, pacman2;
    Fantasma fantasma1;
    Fantasma fantasma2;
    int noObstaculos;
    ArrayList<Rectangle> obstaculos = new ArrayList<Rectangle>();

    Timer timer;

    public Tablero(int maximo, int minimo) {
        int noObstaculos;
        Scanner ingreso = new Scanner(System.in);
        setBackground(Color.BLACK);

//        snake1.push(new Punto(30, 30));
//        snake1.push(new Punto(40, 30));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter());

        pacman1 = new Pacman(30, 30, pacmanDerechaImg);
        pacman2 = new Pacman(50, 50, pacmanRojoDerechaImg);

        fantasma1 = new Fantasma(50, 250, fantasma1Img);
        fantasma2 = new Fantasma(150, 450, fantasma2Img);

        timer = new Timer(50, this);
        timer.start();

        System.out.print("Ingrese el número de obstáculos: ");
        noObstaculos = ingreso.nextInt();
        for (int i = 0; i < noObstaculos; i++) {
            int[] ini = new int[2];
            int[] fin = new int[2];
            System.out.print("Ingrese las coordenadas de inicio: ");
            int x1 = ingreso.nextInt() * 30;
            int y1 = ingreso.nextInt() * 30;

            System.out.print("ingrese las corrdenadas de fin: ");
            int alto = ingreso.nextInt() * 30 + 30;
            int ancho = ingreso.nextInt() * 30 + 30;

            obstaculos.add(new Rectangle(x1, y1, ancho, alto));
        }

        int A[] = new int[50];
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(pacman1.imagen, pacman1.x, pacman1.y, this);
        g.drawImage(pacman2.imagen, pacman2.x, pacman2.y, this);

        g.drawImage(fantasma1.imagen, fantasma1.x, fantasma1.y, this);
        g.drawImage(fantasma2.imagen, fantasma2.x, fantasma2.y, this);

        g.setColor(Color.green);
        for (int i = 0; i < obstaculos.size(); i++) {
            g.drawRect(obstaculos.get(i).x, obstaculos.get(i).y, obstaculos.get(i).width, obstaculos.get(i).height);

        }
    }

    public void mover(Pacman pacman) {

        boolean intersecta = false;
        if (pacman.moverArriba) {

            for (int i = 0; i < obstaculos.size(); i++) {
                if (obstaculos.get(i).intersects(new Rectangle(pacman.x, pacman.y, 15, 15))) {
                    intersecta = true;
                }
            }
            if (!intersecta) {
                pacman.y -= 4;

            } else {
                pacman.moverArriba = false;
                pacman.y += 6;
            }
        }
        if (pacman.moverAbajo) {
            for (int i = 0; i < obstaculos.size(); i++) {
                if (obstaculos.get(i).intersects(new Rectangle(pacman.x, pacman.y, 15, 15))) {
                    intersecta = true;
                }
            }
            if (!intersecta) {
                pacman.y += 4;
            } else {
                pacman.moverAbajo = false;
                pacman.y -= 6;
            }
        }
        if (pacman.moverDerecha) {
            for (int i = 0; i < obstaculos.size(); i++) {
                if (obstaculos.get(i).intersects(new Rectangle(pacman.x, pacman.y, 15, 15))) {
                    intersecta = true;
                }
            }
            if (!intersecta) {
                pacman.x += 4;

            } else {
                pacman.moverDerecha = false;
                pacman.x -= 6;
            }
        }
        if (pacman.moverIzquierda) {
            for (int i = 0; i < obstaculos.size(); i++) {
                if (obstaculos.get(i).intersects(new Rectangle(pacman.x, pacman.y, 15, 15))) {
                    intersecta = true;
                }
            }
            if (!intersecta) {
                pacman.x -= 4;

            } else {
                pacman.moverIzquierda = false;
                pacman.x += 6;
                
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mover(pacman1);
        mover(pacman2);
        repaint();

    }

    private class KeyAdapter extends java.awt.event.KeyAdapter {

        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            System.out.println((int) 'W');
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    pacman1.moverArriba = true;
                    pacman1.moverAbajo = false;
                    pacman1.moverDerecha = false;
                    pacman1.moverIzquierda = false;
                    pacman1.imagen = pacmanArribaImg;
                    break;
                case KeyEvent.VK_DOWN:
                    pacman1.moverArriba = false;
                    pacman1.moverAbajo = true;
                    pacman1.moverDerecha = false;
                    pacman1.moverIzquierda = false;
                    pacman1.imagen = pacmanAbajoImg;
                    break;
                case KeyEvent.VK_RIGHT:
                    pacman1.moverArriba = false;
                    pacman1.moverAbajo = false;
                    pacman1.moverDerecha = true;
                    pacman1.moverIzquierda = false;
                    pacman1.imagen = pacmanDerechaImg;
                    break;
                case KeyEvent.VK_LEFT:
                    pacman1.moverArriba = false;
                    pacman1.moverAbajo = false;
                    pacman1.moverDerecha = false;
                    pacman1.moverIzquierda = true;
                    pacman1.imagen = pacmanIzquierdaImg;
                    break;
                case (int) 'W':
                    pacman2.moverArriba = true;
                    pacman2.moverAbajo = false;
                    pacman2.moverDerecha = false;
                    pacman2.moverIzquierda = false;
                    pacman2.imagen = pacmanRojoArribaImg;
                    break;

                case (int) 'D':
                    pacman2.moverArriba = false;
                    pacman2.moverAbajo = false;
                    pacman2.moverDerecha = true;
                    pacman2.moverIzquierda = false;
                    pacman2.imagen = pacmanRojoDerechaImg;
                    break;

                case (int) 'A':
                    pacman2.moverArriba = false;
                    pacman2.moverAbajo = false;
                    pacman2.moverDerecha = false;
                    pacman2.moverIzquierda = true;
                    pacman2.imagen = pacmanRojoIzquierdaImg;
                    break;

                case (int) 'S':
                    pacman2.moverArriba = false;
                    pacman2.moverAbajo = true;
                    pacman2.moverDerecha = false;
                    pacman2.moverIzquierda = false;
                    pacman2.imagen = pacmanRojoAbajoImg;
                    break;

                default:
                    break;
            }

        }
    }
}

class Fantasma {

    int x;
    int y;
    Image imagen;

    public Fantasma(int x, int y, Image imagen) {
        this.x = x;
        this.y = y;
        this.imagen = imagen;
    }

}

class Pacman {

    int x;
    int y;
    Image imagen;
    boolean moverArriba = false;
    boolean moverAbajo = false;
    boolean moverDerecha = false;
    boolean moverIzquierda = false;

    public Pacman(int x, int y, Image imagen) {
        this.x = x;
        this.y = y;
        this.imagen = imagen;
    }
}

class Obstaculo {

    int[] ini = new int[2];
    int[] fin = new int[2];
    int altura;
    int ancho;

    public Obstaculo(int[] ini, int[] fin) {
        this.ini = ini;
        this.altura = Math.abs(fin[1] - ini[1]);
        this.ancho = Math.abs(fin[0] - ini[0]);
    }
}
