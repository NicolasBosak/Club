package ClubSocial.Interfaz;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import ClubSocial.Metodos.Club;
import ClubSocial.Metodos.Socio.Tipo;
@SuppressWarnings("serial")
public class InterfazClub extends JFrame{

    private Autorizados autorizadosSocio;
    private ListaSocios listaSocios;
    private ISocio iSocio;
    private IFacturas iFacturas;
    private Opciones opciones;
    private Club club;

    public InterfazClub( )
    {
        setTitle( "Club" );
        setSize( 960, 550 );
        setDefaultCloseOperation( javax.swing.JFrame.EXIT_ON_CLOSE );

        club = new Club( );

        setLayout( new BorderLayout( ) );

        JPanel panelCentro = new JPanel( );
        panelCentro.setLayout( new BorderLayout( ) );
        add( panelCentro, BorderLayout.CENTER );

        listaSocios = new ListaSocios( this );
        panelCentro.add(listaSocios, BorderLayout.WEST );

        iSocio = new ISocio( this );
        panelCentro.add(iSocio, BorderLayout.CENTER );

        JPanel panelDerecha = new JPanel( );
        panelDerecha.setLayout( new GridLayout( 2, 1 ) );
        add( panelDerecha, BorderLayout.EAST );

        iFacturas = new IFacturas( this );
        panelDerecha.add(iFacturas);

        autorizadosSocio = new Autorizados( this );
        panelDerecha.add(autorizadosSocio);

        opciones = new Opciones( this );
        add(opciones, BorderLayout.SOUTH );

        setLocationRelativeTo( null );
        setResizable( false );
    }
    public void afiliarSocio( String pCedula, String pNombre, Tipo pTipo )
    {
        try
        {
            club.afiliarSocio( pCedula, pNombre, pTipo );
            listaSocios.refrescar( club.darSocios( ) );

            actualizar( );
            JOptionPane.showMessageDialog( this, "El usuario ha sido ingresado", "Afiliar socio", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Afiliar socio", JOptionPane.ERROR_MESSAGE );
        }
    }

    public void agregarAutorizado( )
    {
        String nombreAutorizado = JOptionPane.showInputDialog( this, "Ingrese el nombre del autorizado:", "Agregar autorizado", JOptionPane.QUESTION_MESSAGE );
        String cedulaSocio = listaSocios.darCedulaSocioSeleccionado( );
        if( nombreAutorizado != null && !nombreAutorizado.isEmpty( ) )
        {
            try
            {
                club.agregarAutorizadoSocio( cedulaSocio, nombreAutorizado );
                ArrayList<String> autorizados = new ArrayList<String>( club.darAutorizadosSocio( cedulaSocio ) );
                autorizadosSocio.cambiarAutorizados( autorizados );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }
    public void pagarFactura( )
    {
        try
        {
            String cedula = listaSocios.darCedulaSocioSeleccionado( );
            int facturaIndice = iFacturas.darPosicionFacturaSeleccionada( );

            club.pagarFacturaSocio( cedula, facturaIndice );
            actualizar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }
    public void aumentarFondos( )
    {
        String cedula = listaSocios.darCedulaSocioSeleccionado( );
        String respuesta = JOptionPane.showInputDialog( this, "Ingrese el valor a aumentar:", "Aumentar fondos", JOptionPane.QUESTION_MESSAGE );
        double valor = 0;
        try
        {
            valor = Double.parseDouble( respuesta );
            try
            {
                if( valor > 0 )
                {
                    club.aumentarFondosSocio( cedula, valor );
                    actualizar( );
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "El valor a aumentar debe ser mayor a cero", "Aumentar fondos", JOptionPane.ERROR_MESSAGE );

                }
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Aumentar fondos", JOptionPane.ERROR_MESSAGE );
            }

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "El valor a aumentar debe ser un valor numerico", "Aumentar fondos", JOptionPane.ERROR_MESSAGE );
        }
    }
    public void actualizar( )
    {
        String cedula = listaSocios.darCedulaSocioSeleccionado( );
        iSocio.actualizar( club.buscarSocio( cedula ) );
        iFacturas.cambiarFacturas( club.darFacturasSocio( cedula ) );
        autorizadosSocio.cambiarAutorizados( club.darAutorizadosSocio( cedula ) );
    }
    public void registrarConsumo( String pNombre, String pConcepto, double pValor )
    {
        try
        {
            String cedula = listaSocios.darCedulaSocioSeleccionado( );
            club.registrarConsumo( cedula, pNombre, pConcepto, pValor );
            actualizar( );
            JOptionPane.showMessageDialog( this, "Consumo Registrado", "Registrar consumo", JOptionPane.INFORMATION_MESSAGE );

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Registrar consumo", JOptionPane.ERROR_MESSAGE );
        }
    }
    public ArrayList<String> darAutorizadosSocio( )
    {
        return club.darAutorizadosSocio( listaSocios.darCedulaSocioSeleccionado( ) );
    }
    private boolean verificarNumero( String pCedula )
    {
        boolean resultado = false;
        try
        {
            Integer.parseInt( pCedula );
            resultado = true;
        }
        catch( Exception e )
        {
            resultado = false;
        }

        return resultado;
    }
    public void reqFuncOpcion1( )
    {
        String resultado = club.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }
    public void reqFuncOpcion2( )
    {
        String resultado = club.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }
    public static void main( String[] pArgs )
    {
        try
        {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

            InterfazClub interfaz = new InterfazClub( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
