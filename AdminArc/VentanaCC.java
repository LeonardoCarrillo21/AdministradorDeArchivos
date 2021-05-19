package AdminArc;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class VentanaCC extends JInternalFrame{
  private JPanel p1;
  private JButton btnCarp, btnArc, btnElUb, btnElUb2, btnAc;
  private JLabel jlViejo,  jlNuevo, jlCar, jlArc;
  private JTextField jtfNombNuevo;
  private JTextArea jtaUbic, jtaUbic2;
  private JScrollPane jsp1, jsp2;
  private int ind;
  private Color col;
  private String nomAr, ubicacion, tipog, accion;
  private TextPrompt txtTempArcO, txtTempArcD ;

  public void setTipo(String tipo){
    this.tipog = tipo;
  }
  public String getTipo(){
    return tipog;
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

  public void setInd(int a){
    this.ind = a;
  }
  public int getInd(){
    return ind;
  }

  public VentanaCC(String ac, Color cl){
    setAccion(ac);
    setColores(cl);
    setTitle(getAccion());
    setSize(500, 400);
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
    btnElUb2 = new JButton("Elegir");
    btnAc = new JButton(getAccion());
    btnCarp.setBackground(getColores());
    btnCarp.setIcon(new ImageIcon("iconoCarpeta.PNG"));
    btnArc.setBackground(getColores());
    btnArc.setIcon(new ImageIcon("iconoArchivo.png"));

    //etiquetas
    jlViejo = new JLabel("<html><center>Nombre y ubicacion<P>del archivo origen");
    jlNuevo = new JLabel("<html><center>Nombre y ubicacion<P>del archivo destino");
    jlCar = new JLabel("Carpeta");
    jlArc = new JLabel("Archivo");


    //jta con JScrollPane
    jtaUbic = new JTextArea();
    jsp1 = new JScrollPane();
    jtaUbic2 = new JTextArea();
    jsp2 = new JScrollPane();
    txtTempArcO = new TextPrompt("Ej:C:/Users/Pedro/Archivo1.txt)", jtaUbic);
    txtTempArcO.changeStyle(Font.ITALIC);
    txtTempArcD = new TextPrompt("Ej:C:/Users/Pedro/Quinto Semestre/Archivo1.txt", jtaUbic2);
    txtTempArcD.changeStyle(Font.ITALIC);

    //poscionar elementos
    btnCarp.setBounds(85, 50, 80, 80);
    btnArc.setBounds(335, 50, 80,80);
    btnElUb.setBounds(400, 180, 80,35);
    btnElUb2.setBounds(400, 240, 80, 35);
    btnAc.setBounds(200 , 300, 100, 25);
    jlViejo.setBounds(20, 180, 150, 40);
    jlNuevo.setBounds(20, 240, 150, 40);
    //jtfNombNuevo.setBounds(110, 240, 180, 25);
    jtaUbic.setBounds(135,180,255, 40);
    jsp1.setBounds(135,180,255, 40);
    jsp1.setViewportView(jtaUbic);
    jtaUbic2.setBounds(135,240, 255, 35);
    jsp2.setBounds(135,240, 255, 35);
    jsp2.setViewportView(jtaUbic2);


    jlCar.setBounds(95, 130, 80, 20);
    jlArc.setBounds(345, 130, 80, 20);
    btnCarp.setBorder(null);
    btnArc.setBorder(null);
    EventoBotones in = new EventoBotones();
    btnElUb2.addActionListener(in);
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
    p1.add(btnElUb2);
    p1.add(jlViejo);
    p1.add(jlNuevo);
    p1.add(jsp1);
    p1.add(jlCar);
    p1.add(jlArc);
    p1.add(jsp2);
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
        //si elige carpeta
        case "Carpeta":
          btnArc.setEnabled(true);
          btnCarp.setEnabled(false);
          jlViejo.setText("<html><center>Nombre y ubicacion<P>de la carpeta origen");
          jlNuevo.setText("<html><center>Nombre y ubicacion<P>de carpeta destino");
          setTipo("la carpeta");
          setInd(2);
          break;
          //si elige el archivo
        case "Archivo":
          btnArc.setEnabled(false);
          btnCarp.setEnabled(true);
          jlViejo.setText("<html><center>Nombre y ubicacion<P>del archivo origen");
          jlNuevo = new JLabel("<html><center>Nombre y ubicacion<P>del archivo destino");
          setTipo("el archivo");
          setInd(1);
          break;

        case "Elegir":
          if(getInd()==1){
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(p1);
            File archivo = jf.getSelectedFile();
            File aux = jf.getCurrentDirectory();
            if(archivo!=null){
              jtaUbic.setText(archivo.getPath());
              System.out.println(aux.getAbsolutePath());
            }
          }else if(getInd()==2){
            JFileChooser jf= new JFileChooser();
            jf.setDialogTitle("Seleccione carpeta para guardar "+getTipo());
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jf.setAcceptAllFileFilterUsed(true);
            int seleccion = jf.showOpenDialog(p1);
            File carpetaSeleccionada = jf.getSelectedFile();
            File aux = jf.getCurrentDirectory();
            jtaUbic.setText(carpetaSeleccionada.getPath());
          }
          break;
      }//switch
    } //evento
  }//EventoBotones
}//clase
