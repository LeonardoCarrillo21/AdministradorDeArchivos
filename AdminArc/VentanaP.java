package AdminArc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class VentanaP extends JFrame {
  private JDesktopPane p1;
  private JLabel jlcrear, jlrenombrar, jlcopiar, jlabrir, jlcuo,  jleliminar;
  private JButton btnCrear, btnRenombrar, btncopiar, btnAbrir, btnCuo, btnElim;
  

  public VentanaP(){
    setTitle("Administrador de archivos");
    setSize(600, 550);
    setLayout(new BorderLayout());
    initComponents();
    this.setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setResizable(false);
  }

  public void initComponents(){
    p1 = new JDesktopPane();
    p1.setBackground(new Color(2, 159, 255));
    p1.setLayout(null);

    //Botones
    btnCrear = new JButton("crear");
    btnCrear.setIcon(new ImageIcon("iconoCrear.png"));
    btnRenombrar = new JButton("renombrar");
    btnRenombrar.setIcon(new ImageIcon("iconoRen.png"));
    btncopiar = new JButton("copiar");
    btncopiar.setIcon(new ImageIcon("iconoCopiar.png"));
    btnAbrir = new JButton("abrir");
    btnAbrir.setIcon(new ImageIcon("iconoAbrir.png"));
    btnCuo = new JButton("Mover");
    btnCuo.setIcon(new ImageIcon("iconoCUO.png"));
    btnElim = new JButton("Eliminar");
    btnElim.setIcon(new ImageIcon("iconoEli.png"));

    //dar posicion y quitar borde a los botones
    btnCrear.setBounds(50, 100, 100, 100);
    btnRenombrar.setBounds(250, 100, 100,100);
    btncopiar.setBounds(450,100, 100, 100);
    btnAbrir.setBounds(50, 300, 100, 100);
    btnCuo.setBounds(250, 300, 100, 100);
    btnElim.setBounds(450, 300, 100, 100);
    btnCrear.setBorder(null);
    btnRenombrar.setBorder(null);
    btncopiar.setBorder(null);
    btnAbrir.setBorder(null);
    btnCuo.setBorder(null);
    btnElim.setBorder(null);

    btnCrear.setBackground(new Color(2, 159, 255));
    btnRenombrar.setBackground(new Color(2, 159, 255));
    btncopiar.setBackground(new Color(2, 159, 255));
    btnAbrir.setBackground(new Color(2, 159, 255));
    btnCuo.setBackground(new Color(2, 159, 255));
    btnElim.setBackground(new Color(2, 159, 255));

    //JLabel
    jlcrear = new JLabel("Crear");
    jlrenombrar = new JLabel("Renombrar");
    jlcopiar = new JLabel("Copiar");
    jlabrir = new JLabel("Abrir");
    jlcuo = new JLabel("<html><center>Mover/<P>Cortar-Pegar");
    jleliminar = new JLabel("Eliminar");

    jlcrear.setFont(new Font("Man Extended", Font.PLAIN, 15));
    jlrenombrar.setFont(new Font("Man Extended", Font.PLAIN, 15));
    jlcopiar.setFont(new Font("Man Extended", Font.PLAIN, 15));
    jlabrir.setFont(new Font("Man Extended", Font.PLAIN, 15));
    jlcuo.setFont(new Font("Man Extended", Font.PLAIN, 15));
    jleliminar.setFont(new Font("Man Extended", Font.PLAIN, 15));

    jlcrear.setBounds(80, 210, 50, 20);
    jlrenombrar.setBounds(265, 210, 100,20);
    jlcopiar.setBounds(480,210, 50, 20);
    jlabrir.setBounds(80, 410, 50, 20);
    jlcuo.setBounds(255, 410, 150, 40);
    jleliminar.setBounds(480, 410, 100, 20);

    EventoBotones ev1 = new EventoBotones();
    btnCrear.addActionListener(ev1);
    btnRenombrar.addActionListener(ev1);
    btncopiar.addActionListener(ev1);
    btnAbrir.addActionListener(ev1);
    btnCuo.addActionListener(ev1);
    btnElim.addActionListener(ev1);

    p1.add(btnCrear);
    p1.add(btnRenombrar);
    p1.add(btncopiar);
    p1.add(btnAbrir);
    p1.add(btnCuo);
    p1.add(btnElim);
    p1.add(jlcrear);
    p1.add(jlrenombrar);
    p1.add(jlcopiar);
    p1.add(jlabrir);
    p1.add(jlcuo);
    p1.add(jleliminar);
    add(p1);
  }
  public class EventoBotones implements ActionListener{
    public void actionPerformed(ActionEvent ev){
      String boton = ev.getActionCommand();
      switch (boton){
        case "crear":
          Ventana1 v1 = new Ventana1();
          p1.add(v1);
          v1.show();
          break;
        case "renombrar":
          System.out.println("renombrar");
          VentanaR vr = new VentanaR();
          p1.add(vr);
          vr.show();
          break;
        case "abrir":
          Color ab = new Color(245, 25, 92);
          VentanaEA va = new VentanaEA("Abrir", ab);
          p1.add(va);
          va.show();
          break;
        case "Eliminar":
          VentanaEA ve= new VentanaEA("Eliminar", new Color(252, 23, 0));
          p1.add(ve);
          ve.show();
          break;
        case "copiar":
          VentanaCC vc = new VentanaCC("Copiar", new Color(138, 79, 218 ));
          p1.add(vc);
          vc.show();
        break;
        case "Mover":
          VentanaCC vcc = new VentanaCC("Mover", new Color(22, 160, 133));
          p1.add(vcc);
          vcc.show();
        break;
      }
    }
  }
}
