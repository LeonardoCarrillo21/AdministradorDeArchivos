package AdminArc;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class VentanaEA extends JInternalFrame {
  private JPanel p1;
  private JButton btnCarp, btnArc, btnElUb, btnAc;
  private JLabel jlViejo, jlCar, jlArc;
  private JTextArea jtaUbic;
  private JScrollPane jsp1;
  private int ind;
  private Color col;
  private TextPrompt txtTempArc;
  private String nomAr, ubicacion, tipog, accion;
//  private TextPrompt txtTempArc;

  public void setTipo(String tipo){
    this.tipog = tipo;
  }
  public String getTipo(){
    return tipog;
  }

  public void setInd(int a){
    this.ind = a;
  }
  public int getInd(){
    return ind;
  }

  public void setAccion(String ac){
    this.accion = ac;
  }
  public void setColores(Color cl){
    this.col = cl;
  }
  public String getAccion(){
    return accion;
  }
  public Color getColores(){
    return col;
  }

  public VentanaEA(String ac, Color cl){
    setAccion(ac);
    setColores(cl);
    setTitle(getAccion());
    setSize(500, 300);
    setLayout(new BorderLayout());
    initComponents();
    setVisible(true);
    //setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
  }

  public void initComponents(){
    p1 = new JPanel();
    p1.setBackground(getColores());
    p1.setLayout(null);

    //Botones
    btnCarp = new JButton("Carpeta");
    btnArc = new JButton("Archivo");
    btnArc.setEnabled(false);
    btnElUb = new JButton("Elegir");
    btnAc = new JButton(getAccion());
    btnCarp.setBackground(getColores());
    btnCarp.setIcon(new ImageIcon("iconoCarpeta.PNG"));
    btnArc.setBackground(getColores());
    btnArc.setIcon(new ImageIcon("iconoArchivo.png"));

    //etiquetas
    jlViejo = new JLabel("<html><center>Nombre y ubicacion<P>del archivo");
    jlCar = new JLabel("Carpeta");
    jlArc = new JLabel("Archivo");


    jtaUbic = new JTextArea();
    jsp1 = new JScrollPane();

    txtTempArc = new TextPrompt("Ej: C:/Users/Pedro/Carpeta/archivo.java", jtaUbic);
    txtTempArc.changeStyle(Font.ITALIC);

    //poscionar elementos
    btnCarp.setBounds(85, 50, 80, 80);
    btnArc.setBounds(335, 50, 80,80);
    btnElUb.setBounds(400, 180, 80,25);
    btnAc.setBounds(200 , 230, 100, 25);
    jlViejo.setBounds(20, 180, 150, 40);

    jtaUbic.setBounds(135,180,245, 35);
    jsp1.setBounds(135,180,245, 35);
    jsp1.setViewportView(jtaUbic);
    jlCar.setBounds(95, 130, 80, 20);
    jlArc.setBounds(345, 130, 80, 20);
    btnCarp.setBorder(null);
    btnArc.setBorder(null);


    EventoBotones in = new EventoBotones();
    btnCarp.addActionListener(in);
    btnArc.addActionListener(in);
    btnElUb.addActionListener(in);
    btnAc.addActionListener(in);
    setTipo("el archivo");
    setInd(1);

    p1.add(btnCarp);
    p1.add(btnArc);
    p1.add(btnAc);
    p1.add(btnElUb);
    p1.add(jlViejo);
    p1.add(jsp1);
    p1.add(jlCar);
    p1.add(jlArc);
    this.setClosable(true);
    setIconifiable(true);
    setMaximizable(true);
    setResizable(true);
    add(p1);

  }
  public class EventoBotones implements ActionListener{
    public void actionPerformed(ActionEvent ev){
      String boton = ev.getActionCommand();
      switch(boton){
        case "Carpeta":
          btnArc.setEnabled(true);
          btnCarp.setEnabled(false);
          jlViejo.setText("<html><center>Nombre y ubicacion<P>de la carpeta");
          setTipo("la carpeta");
          setInd(2);
          break;
        case "Archivo":
          btnArc.setEnabled(false);
          btnCarp.setEnabled(true);
          jlViejo.setText("<html><center>Nombre y ubicacion<P>del archivo");
          setTipo("el archivo");
          setInd(1);
          break;
        case "Elegir":
          System.out.println("elegir");
          JFileChooser jf= new JFileChooser();
          jf.setDialogTitle("Seleccione carpeta para guardar "+getTipo());
          jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          jf.setAcceptAllFileFilterUsed(false);
          int seleccion = jf.showOpenDialog(p1);

          if(seleccion == JFileChooser.APPROVE_OPTION){
            File carpetaSeleccionada = jf.getCurrentDirectory();
            jtaUbic.setText(carpetaSeleccionada.getAbsolutePath());
          }
          break;
      }
    }
  }
}
